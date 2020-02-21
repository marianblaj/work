package ro.fortech.pdfparser.refactortests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.service.refactor.PdfCreateAndSaveLine;

import java.math.BigDecimal;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PdfCreateAndSaveLineTest {

    String[] lines;

    @Test
    public void whenLineParser() throws Exception{
        List<BigDecimal> list = BigDecimalProvider.getBigDecimals("112 , 124, 12.3 ccc 1.10012 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1  ");
        ParsedPdfLineDto parsedPdfDto = PdfCreateAndSaveLine.createAndSaveLine(list);

        String x = parsedPdfDto.getAccNr();

        System.out.println(parsedPdfDto.toString());

        assertThat(x).isEqualTo(parsedPdfDto.getAccNr());

    }
}
