package ro.fortech.pdfparser.refactortests;


import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;

import java.math.BigDecimal;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BigDecimalProviderTest {

    @Test
    public void BigDecimal(){
        BigDecimal[] decimals = {BigDecimal.valueOf(112), BigDecimal.valueOf(0.31)};
        List<BigDecimal> numbersListActual = Arrays.asList(decimals);

        List<BigDecimal> numbersList = BigDecimalProvider.getBigDecimals("112 c cc m 0.31");
        System.out.println(numbersList);


        assertThat(decimals).isEqualTo(numbersList);
    }
}
