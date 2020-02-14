package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.service.refactor.ImportPdf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class BigDecimalProviderTest {


    @Test
    public void whenStringPathValid_thenReturnListOfNumbers() throws Exception{

        List<BigDecimal> resultBigDecimals = BigDecimalProvider.getBigDecimals("qwerty 123");
        System.out.println("------------------------------------------");
        System.out.println(resultBigDecimals.toString());
        System.out.println("------------------------------------------");
        assertThat(BigDecimalProvider.getBigDecimals("qwerty 123")).containsExactly(BigDecimal.valueOf(123));
    }
}
