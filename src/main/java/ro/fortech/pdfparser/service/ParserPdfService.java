package ro.fortech.pdfparser.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParserPdfService {


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
                pdfStripper.setSortByPosition(true);
                pdfStripper.setStartPage(0);
                pdfStripper.setEndPage(pdDoc.getNumberOfPages());

                String parsedText = pdfStripper.getText(pdDoc);

                String[] lines = parsedText.split(System.lineSeparator());
                BigDecimal spdTotal = BigDecimal.ZERO;

                Scanner scanner = new Scanner(parsedText);
                String date = "";

                String firma = lines[0];
                for (int i = 0; i < 3; i++) {
                    scanner.nextLine();
                    if (i == 2) {

                        date = scanner.nextLine();
                    }
                }
                System.out.println(date);
                String[] dates = date.split("\\D");
                System.out.println(firma);

                String numeFirma = firma.substring(0, firma.indexOf("c.f."));
                String cif = firma.substring(firma.indexOf("RO"), firma.indexOf("r.c."));

                LocalDate startDate = LocalDate.of(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), Integer.parseInt(dates[0]));
                LocalDate endDate = LocalDate.of(Integer.parseInt(dates[8]), Integer.parseInt(dates[7]), Integer.parseInt(dates[6]));

                System.out.println("Start date in LocalDate " + startDate);
                System.out.println("End date in LocalDate " + endDate);

                ParsedPdfDto dto = new ParsedPdfDto();

                dto.setCf(cif);
                dto.setNumeFirma(numeFirma);
                dto.setFrom(startDate);
                dto.setTo(endDate);

                for (String l : lines) {

                    if (NumberUtils.isDigits(l.substring(0, Math.min(3, l.length())))) {

                        List<BigDecimal> numbers = getBigDecimals(l);
                        if (numbers.size() < 9) {

                            System.out.println("Not parsed: " + l);
                            System.out.println("Not parsed: " + numbers);

                            continue;
                        }

                        String accountNumber = numbers.get(0).toPlainString().trim();

                        if (accountNumber.equals("121")) {
                            continue;
                        } else if (!accountNumber.startsWith("1"))
                            break;

                        ParsedPdfLineDto line = createAndSaveLine(numbers);
                        dto.getLines().add(line);

                        System.out.println("Line: " + l);
//                        System.out.printf(line.toString());
                        System.out.println(StringUtils.repeat("=", 100));
                        System.out.println();
                    }
                }

                System.out.println(dto.toString());


                ParsedPdfLineDto lineDto = new ParsedPdfLineDto();
                lineDto=dto.getLines().get(2);
                System.out.println("Total: " + spdTotal.toPlainString());
                System.out.println("SolduriInitialeD: " + lineDto.getSolduriInitialeD().toPlainString());
                System.out.println("SolduriInitialeC: " + lineDto.getSolduriInitialeC().toPlainString());

                System.out.println("RulajePerioadaD: " + lineDto.getRulajePerioadaD().toPlainString());
                System.out.println("RulajePerioadaC: " + lineDto.getRulajePerioadaC().toPlainString());

                System.out.println("TotalRulajeD: " + lineDto.getTotalRulajeD().toPlainString());
                System.out.println("TotalRulajeC: " + lineDto.getTotalRulajeC().toPlainString());

                System.out.println("SumeTotaleD: " + lineDto.getSumeTotaleD().toPlainString());
                System.out.println("SumeTotaleC: " + lineDto.getSumeTotaleC().toPlainString());

                System.out.println("SolduriFinaleD: " + lineDto.getSolduriFinaleD().toPlainString());
                System.out.println("SOlduriFinaleC: " + lineDto.getSolduriFinaleC().toPlainString());

                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
        System.out.println("col: " + StringUtils.join(numbers, "|"));

        return numbers;
    }
}
