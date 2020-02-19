package ro.fortech.pdfparser.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@Service
public class ParserService {

    private BalanceSheetRepository balanceSheetRepository;
    private BalanceSheetLineRepository balanceSheetLineRepository;


    public ParserService(BalanceSheetRepository balanceSheetRepository, BalanceSheetLineRepository balanceSheetLineRepository) {
        this.balanceSheetRepository = balanceSheetRepository;
        this.balanceSheetLineRepository=balanceSheetLineRepository;
    }


    public ParsedPdfDto importPdf(String path) throws Exception {
        // File file = FileUtils.getFile(BL_FILENAME);

        InputStream in = new ClassPathResource(
                path, ParserService.class.getClassLoader()).getInputStream();

        return parse(in);
    }


    public ParsedPdfDto parse(InputStream file) {
        try {
            PDFTextStripper pdfStripper = null;
            PDDocument pdDoc = null;
            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
            parser.parse();
            try (COSDocument cosDoc = parser.getDocument()) {
                pdfStripper = new PDFTextStripper();
                pdDoc = new PDDocument(cosDoc);
                pdfStripper.setSortByPosition(true);
                pdfStripper.setStartPage(0);
                pdfStripper.setEndPage(pdDoc.getNumberOfPages());

                String parsedText = pdfStripper.getText(pdDoc);

                String[] lines = parsedText.split(System.lineSeparator());
                BigDecimal spdTotal = BigDecimal.ZERO;

                ParsedPdfDto dto = new ParsedPdfDto();

                setSheetDetails(parsedText,lines,dto);
                setLines(lines,dto);

                addToDatabase(dto);

//                ParsedPdfLineDto lineDto = new ParsedPdfLineDto();
//                lineDto=dto.getLines().get(2);

                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setSheetDetails(String parsedText, String[] lines, ParsedPdfDto dto)
    {
        Scanner scanner = new Scanner(parsedText);
        String date = "";

        String firma = lines[0];
        for (int i = 0; i < 3; i++) {
            scanner.nextLine();
            if (i == 2) {
                date = scanner.nextLine();
            }
        }

        String[] dates = date.split("\\D");

        String numeFirma = firma.substring(0, firma.indexOf("c.f."));
        String cif = firma.substring(firma.indexOf("RO"), firma.indexOf("r.c."));

        LocalDate startDate = LocalDate.of(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), Integer.parseInt(dates[0]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(dates[8]), Integer.parseInt(dates[7]), Integer.parseInt(dates[6]));

        dto.setCf(cif);
        dto.setNumeFirma(numeFirma);
        dto.setFrom(startDate);
        dto.setTo(endDate);
    }

    private void setLines(String[] lines, ParsedPdfDto dto)
    {
        for (String l : lines) {

            if (NumberUtils.isDigits(l.substring(0, Math.min(3, l.length())))) {

                List<BigDecimal> numbers = getBigDecimals(l);
                if (numbers.size() < 9) {
                    continue;
                }

                String accountNumber = numbers.get(0).toPlainString().trim();

                if (accountNumber.equals("121")) {
                    continue;
                } else if (!accountNumber.startsWith("1"))
                    break;

                ParsedPdfLineDto line = createAndSaveLine(numbers);
                dto.getLines().add(line);
                System.out.println();
            }

        }
    }

    private ParsedPdfLineDto createAndSaveLine(List<BigDecimal> numbers) {
        String accountNumber = numbers.get(0).toPlainString().trim();

        ParsedPdfLineDto line = new ParsedPdfLineDto();

        line.setAccNr(accountNumber);
        line.setSolduriInitialeD(numbers.get(1));
        line.setSolduriInitialeC(numbers.get(2));
        line.setRulajePerioadaD(numbers.get(3));
        line.setRulajePerioadaC(numbers.get(4));
        line.setTotalRulajeD(numbers.get(5));
        line.setTotalRulajeC(numbers.get(6));
        line.setSumeTotaleD(numbers.get(7));
        line.setSumeTotaleC(numbers.get(8));
        line.setSolduriFinaleD(numbers.get(9));
        line.setSolduriFinaleC(numbers.get(10));
        return line;
    }

    private void addToDatabase(ParsedPdfDto dto)
    {
        BalanceSheetEntity updatedDto = new BalanceSheetEntity();
        updatedDto = updatedDto.toBalanceSheetEntity(dto);

        balanceSheetRepository.save(updatedDto);

        for(int i=0;i<updatedDto.getLines().size();i++)
        {
            balanceSheetLineRepository.save(updatedDto.getLines().get(i));
        }
    }

    private List<BigDecimal> getBigDecimals(String l) {
        String l2 = l.replaceAll("(\\d)\\s(\\d)", "$1$2");
        Scanner sc = new Scanner(l2);

        List<BigDecimal> numbers = new ArrayList<>();
        while (sc.hasNext()) {
            if (sc.hasNextBigDecimal()) {
                numbers.add(sc.nextBigDecimal());
            } else {

                sc.next();
            }
        }
        return numbers;
    }

    public List<BalanceSheetEntity> getBalanceSheet(){
        return balanceSheetRepository.findAll();
    }
}
