package ro.fortech.pdfparser.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import javax.swing.text.html.parser.Parser;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))


public class ParserService {


    private BalanceSheetRepository balanceSheetRepository;
    private BalanceSheetLineRepository balanceSheetLineRepository;

    public ParserService(BalanceSheetRepository balanceSheetRepository,BalanceSheetLineRepository balanceSheetLineRepository) {
        this.balanceSheetRepository = balanceSheetRepository;
        this.balanceSheetLineRepository = balanceSheetLineRepository;
    }

    public ParsedPdfDto importPdf() throws Exception {
        // File file = FileUtils.getFile(BL_FILENAME);

        InputStream in = new ClassPathResource(
                "/2017 SAS balanta 31122017.pdf", ParserPdfService.class.getClassLoader()).getInputStream();

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
                pdfSettings(pdfStripper, pdDoc);

                String parsedText = pdfStripper.getText(pdDoc);

                String[] lines = parsedText.split(System.lineSeparator());

                ParsedPdfDto dto = getDetails(parsedText, lines[0]);

                setLinesToDto(lines, dto);

                BalanceSheetEntity updatedDto = update(dto);

                for (int i = 0; i < updatedDto.getLines().size(); i++) {
                    balanceSheetLineRepository.save(updatedDto.getLines().get(i));
                }

                balanceSheetRepository.save(updatedDto);

                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void pdfSettings(PDFTextStripper pdfStripper, PDDocument pdDoc) {
        pdfStripper.setSortByPosition(true);
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
    }

    private void setLinesToDto(String[] lines, ParsedPdfDto dto) {
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

    public ParsedPdfDto getDetails(String parsedText, String line1) {
        Scanner scanner = new Scanner(parsedText);
        String date = "";


        for (int i = 0; i < 3; i++) {
            scanner.nextLine();
            if (i == 2) {
                date = scanner.nextLine();
            }
        }


        String[] dates = date.split("\\D");


        LocalDate startDate = LocalDate.of(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), Integer.parseInt(dates[0]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(dates[8]), Integer.parseInt(dates[7]), Integer.parseInt(dates[6]));

        String firma = line1;
        String numeFirma = firma.substring(0, firma.indexOf("c.f."));
        String cif = firma.substring(firma.indexOf("RO"), firma.indexOf("r.c."));

        ParsedPdfDto dto = new ParsedPdfDto();
        dto.setCf(cif);
        dto.setNumeFirma(numeFirma);
        dto.setFrom(startDate);
        dto.setTo(endDate);

        return dto;
    }

    private ParsedPdfLineDto createAndSaveLine(List<BigDecimal> numbers) {
        String accountNumber = numbers.get(0).toPlainString().trim();

        ParsedPdfLineDto line = new ParsedPdfLineDto();

        setNumbersToLine(numbers, accountNumber, line);

        return line;
    }

    private void setNumbersToLine(List<BigDecimal> numbers, String accountNumber, ParsedPdfLineDto line) {
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

    public BalanceSheetEntity update(ParsedPdfDto pojo) {
        BalanceSheetEntity bal = new BalanceSheetEntity();
        bal.setNumeFirma(pojo.getNumeFirma());
        bal.setCf(pojo.getCf());
        bal.setFrom(pojo.getFrom());
        bal.setTo(pojo.getTo());
        bal.setLines(pojo.getLines()
                .stream()
                .map(ParsedPdfLineDto::update)
                .collect(Collectors.toList())) ;

        //System.out.println(bal.getLines().get(0));
        return bal;
    }

}
