package ro.fortech.pdfparser;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.service.refactor.PdfLineParser;

import java.math.BigDecimal;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PdfLineParserTest {

    String[] lines;

    @Test
    public void whenLineParser() throws Exception{
        List<BigDecimal> list = BigDecimalProvider.getBigDecimals("112 , 124, 12.3 ccc 1.10012 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1  ");
        ParsedPdfLineDto parsedPdfDto = PdfLineParser.createAndSaveLine(list);

        String x = parsedPdfDto.getAccNr();

        System.out.println(parsedPdfDto.toString());

        assertThat(x).isEqualTo(parsedPdfDto.getAccNr());

    }
}
