package ro.fortech.pdfparser.refactor;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@UtilityClass
public class BigDecimalProvider {

    public static List<BigDecimal> getBigDecimals(String l) {
        String l2 = l.replaceAll("(\\d)\\s(\\d)", "$1$2");
        Scanner sc = new Scanner(l2);

        List<BigDecimal> numbers = new ArrayList<>();
        while (sc.hasNext()) {
            if (sc.hasNextBigDecimal()) {
                numbers.add(sc.nextBigDecimal());
            } else {
                sc.next();
            }
        }
        return numbers;
    }
}