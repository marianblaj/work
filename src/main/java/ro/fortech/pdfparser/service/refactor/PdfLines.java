package ro.fortech.pdfparser.service.refactor;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;

import java.math.BigDecimal;
import java.util.List;


@Component
public class PdfLines {

    @Autowired
    private  PdfLineParser pdfLineParser;

    @Autowired
    private  BigDecimalProvider bigDecimalProvider;

    public PdfLines() {
    }

    public ParsedPdfDto addPdfLines(String[] lines, ParsedPdfDto dto){
        for (String l : lines) {

            if (NumberUtils.isDigits(l.substring(0, Math.min(3, l.length())))) {
                List<BigDecimal> numbers = bigDecimalProvider.getBigDecimals(l);
                if (numbers.size() < 9) {
                    continue;
                }
                String accountNumber = numbers.get(0).toPlainString().trim();

                if (accountNumber.equals("121")) {
                    continue;
                } else if (!accountNumber.startsWith("1"))
                    break;
                ParsedPdfLineDto line = pdfLineParser.createAndSaveLine(numbers);
                dto.getLines().add(line);
            }
        }
        return dto;
    }
}
