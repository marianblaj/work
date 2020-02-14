package ro.fortech.pdfparser.service.refactor;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;

import java.math.BigDecimal;
import java.util.List;


@Component
public class AddPdfLine {

    //@Autowired
    //private  PdfLineParser pdfLineParser;

   // @Autowired
   // private  BigDecimalProvider bigDecimalProvider;


    public ParsedPdfDto addPdfLines(String[] lines, ParsedPdfDto dto){
        for (String l : lines) {

            if (NumberUtils.isDigits(l.substring(0, Math.min(3, l.length())))) {
                List<BigDecimal> numbers = BigDecimalProvider.getBigDecimals(l);
                if (numbers.size() < 9) {
                    continue;
                }
                String accountNumber = numbers.get(0).toPlainString().trim();
                System.out.println("---------111-----");
                if (accountNumber.equals("121")) {
                    continue;
                } else if (!accountNumber.startsWith("1"))
                    break;
                System.out.println("---------2-----");
                ParsedPdfLineDto line = PdfLineParser.createAndSaveLine(numbers);
                System.out.println("---------3-----");
                dto.getLines().add(line);
                System.out.println("---------4-----");

            }

        }

        return dto;

    }

}
