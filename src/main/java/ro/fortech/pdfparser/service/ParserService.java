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
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service


public class ParserService {


    //path = "/2017 SAS balanta 31122017.pdf"


//    private ParsedPdfLineDto createAndSaveLine(List<BigDecimal> numbers) {
//        String accountNumber = numbers.get(0).toPlainString().trim();
//
//        ParsedPdfLineDto line = new ParsedPdfLineDto();
//
//        line.setAccNr(accountNumber);
//        line.setSolduriInitialeD(numbers.get(1));
//        line.setSolduriInitialeC(numbers.get(2));
//        line.setRulajePerioadaD(numbers.get(3));
//        line.setRulajePerioadaC(numbers.get(4));
//        line.setTotalRulajeD(numbers.get(5));
//        line.setTotalRulajeC(numbers.get(6));
//        line.setSumeTotaleD(numbers.get(7));
//        line.setSumeTotaleC(numbers.get(8));
//        line.setSolduriFinaleD(numbers.get(9));
//        line.setSolduriFinaleC(numbers.get(10));
//        return line;
//    }

//    private List<BigDecimal> getBigDecimals(String l) {
//        String l2 = l.replaceAll("(\\d)\\s(\\d)", "$1$2");
//        Scanner sc = new Scanner(l2);
//
//        List<BigDecimal> numbers = new ArrayList<>();
//        while (sc.hasNext()) {
//            if (sc.hasNextBigDecimal()) {
//                numbers.add(sc.nextBigDecimal());
//            } else {
//
//                sc.next();
//            }
//        }
//        return numbers;
//    }

//    public BalanceSheetEntity toBalanceSheetEntity(ParsedPdfDto pojo) {
//        BalanceSheetEntity bal = new BalanceSheetEntity();
//        bal.setNumeFirma(pojo.getNumeFirma());
//        bal.setCf(pojo.getCf());
//        bal.setFrom(pojo.getFrom());
//        bal.setTo(pojo.getTo());
//        bal.setLines(pojo.getLines()
//                .stream()
//                .map(ParsedPdfLineDto::update)
//                .collect(Collectors.toList()));
//        return bal;
//    }
}
