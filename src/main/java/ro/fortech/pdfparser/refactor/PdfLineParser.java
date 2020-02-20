package ro.fortech.pdfparser.refactor;

import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PdfLineParser {

    public ParsedPdfLineDto createAndSaveLine(List<BigDecimal> numbers) {
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
}