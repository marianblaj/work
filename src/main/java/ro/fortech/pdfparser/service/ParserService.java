package ro.fortech.pdfparser.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;
import ro.fortech.pdfparser.BalanceSheetEntity;
import ro.fortech.pdfparser.BalanceSheetLineEntity;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParserService {



    public boolean importPdf() throws Exception{
       // File file = FileUtils.getFile(BL_FILENAME);

        InputStream in = new ClassPathResource(
                "/2017 SAS balanta 31122017.pdf", ParserService.class.getClassLoader()).getInputStream();

        parse(in);
        return true;
    }

    public void parse(InputStream file) {
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

                String str = lines[3];
                String dateFrom = str.substring(0,10);
                String dateTo = str.substring(14,24);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");



                BalanceSheetEntity balanceSheetEntity = new BalanceSheetEntity();
                balanceSheetEntity.setFrom(LocalDate.parse(dateFrom, formatter));
                balanceSheetEntity.setTo(LocalDate.parse(dateTo, formatter));

                for (String l : lines) {


                    if (NumberUtils.isDigits(l.substring(0, Math.min(3, l.length())))) {

                        List<BigDecimal> numbers = getBigDecimals(l);
                        if (numbers.size() < 9) {
                            System.out.println("Not parsed: " + l);
                            System.out.println("Not parsed: " + numbers);

                            continue;
                        }

                        String accountNumber = numbers.get(0).toPlainString().trim();
                        if (!accountNumber.startsWith("1")){
                            break;
                        }


//                        spdTotal = spdTotal.add(numbers.get(1));

                        BalanceSheetLineEntity line = createLine(balanceSheetEntity, numbers);
                        balanceSheetEntity.getLines().add(line);

                        System.out.println("Date From " + dateFrom);
                        System.out.println("Date To " + dateTo);

                        System.out.println(str);
                        System.out.println("Line: " + l);
//                        System.out.printf(line.toString());
                        System.out.println(StringUtils.repeat("=", 100));
                        System.out.println();
                    }
                }

                System.out.println(balanceSheetEntity.toString());
//                System.out.println("Total: " + spdTotal.toPlainString());
//                System.out.println("Total SumePrecedenteD: " + balanceSheetEntity.getTotalSumePrecedenteD().toPlainString());
//                System.out.println("Total SumePrecedenteC: " + balanceSheetEntity.getTotalSumePrecedenteC().toPlainString());
//
//                System.out.println("Total RulajeD: " + balanceSheetEntity.getTotalRulajeD().toPlainString());
//                System.out.println("Total RulajeC: " + balanceSheetEntity.getTotalRulajeC().toPlainString());
            }
        } catch (Exception e) {
            e. printStackTrace();
//            log.error(e.getMessage(), e);
        }
    }

    private BalanceSheetLineEntity createLine(BalanceSheetEntity balanceSheetEntity, List<BigDecimal> numbers) {
        String accountNumber = numbers.get(0).toPlainString().trim();

        BalanceSheetLineEntity line = new BalanceSheetLineEntity();
        line.setAccNr(accountNumber);
        line.setBalanceSheet(balanceSheetEntity);
        line.setSumePrecedenteD(numbers.get(1));
        line.setSumePrecedenteC(numbers.get(2));
        line.setRulajeD(numbers.get(3));
        line.setRulajeC(numbers.get(4));
        line.setSumeTotaleD(numbers.get(5));
        line.setSumeTotaleC(numbers.get(6));
        line.setSolduriFinaleD(numbers.get(7));
        line.setSolduriFinaleC(numbers.get(8));

        return line;
    }

//    private BalanceSheetEntity create() {
//        BalanceSheetEntity balanceSheetEntity = new BalanceSheetEntity();
//        balanceSheetEntity.setFrom(LocalDate.of(2016, 9, 1));
//        balanceSheetEntity.setTo(LocalDate.of(2016, 9, 30));
//        return balanceSheetRepository.save(balanceSheetEntity);
//    }


    private List<BigDecimal> getBigDecimals(String l) {

        String l2 = l.replaceAll("(\\d)\\s(\\d)", "$1$2");
//        System.out.println("l2: " + l2);
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
