package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.refactor.PdfLineParser;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfLineParserTest {

    @Autowired
    PdfLineParser pdfLineParser;

    @Test
    public void whenSaveLines() throws Exception{

        List<BigDecimal> numbers = BigDecimalProvider.getBigDecimals("3456 ll 2.03 nn 1.04 vvv 1.0664 vvv 4646 vvv 777 vvv 2324 vvv 1.04 vvv 1.07874 vvv 1.04 vvv 1.06764");
        ParsedPdfLineDto in = pdfLineParser.createAndSaveLine(numbers);
        System.out.println(in.toString());

        assertThat(in).isEqualTo(pdfLineParser.createAndSaveLine(numbers));
    }
}
