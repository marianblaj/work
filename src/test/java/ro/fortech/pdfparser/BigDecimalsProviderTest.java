package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigDecimalsProviderTest {

    private static BigDecimalProvider bigDecimalProvider;

    @Test
    public void whenStringLine_thenReturnListOfNumbers(){
        String textDecimals = "afagaw 90.5 v 60";
        List<BigDecimal> numbers = bigDecimalProvider.getBigDecimals(textDecimals);
        System.out.println(numbers.toString());
    }
}
