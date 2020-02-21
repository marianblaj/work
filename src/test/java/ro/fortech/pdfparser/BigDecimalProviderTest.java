package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.JsonPathAssertions;
import ro.fortech.pdfparser.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.refactor.ImportPdf;
import ro.fortech.pdfparser.service.ParsedPdfDto;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigDecimalProviderTest {

    @Test
    public void whenGetDecimal() throws Exception{

        List<BigDecimal> in = BigDecimalProvider.getBigDecimals("adada 103 1.03 vvv 1.04");
        System.out.println(in.toString());

            assertThat(in).isEqualTo(BigDecimalProvider.getBigDecimals("adada 103 1.03 vvv 1.04"));
    }
}
