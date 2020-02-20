package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.PdfLineParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfLineParserTest {

    @Autowired
    private PdfLineParser lineParser ;


    @Test
    public void PdfLineParser(){

        List<BigDecimal> numbers = new ArrayList<BigDecimal>();
        int listSize = 11;
        for(int i=0;i<listSize;i++){
            numbers.add(new BigDecimal (i));
        }
        ParsedPdfLineDto line = lineParser.createAndSaveLine(numbers);
        System.out.println(line);
    }
}
