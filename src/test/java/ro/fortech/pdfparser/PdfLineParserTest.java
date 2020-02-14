package ro.fortech.pdfparser;

import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.internal.BigDecimals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfLineParser;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfLineParserTest {


    @Test
    public void whenPathisListOfBigDecimals_thenReturnNewParsedPdfLineDto() throws Exception{


        List<BigDecimal> list = new ArrayList<BigDecimal>();

        for(int i = 0; i <11; ++i)
            list.add(new BigDecimal(i));


        ParsedPdfLineDto parsedPdfLineDto = PdfLineParser.createAndSaveLine(list);
        System.out.println("-------------------------");
        System.out.println(parsedPdfLineDto);
        System.out.println("-------------------------");
        assertThat(parsedPdfLineDto).isNotNull();

    }

}