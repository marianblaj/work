package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.refactor.DocumentDates;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentDatesTest {

    String lines = String.join("\n"
            , "It was the best of times, it was the worst of times,"
            , "it was the age of wisdom, it was the age of foolishness,"
            , "it was the epoch of belief, it was the epoch of incredulity,"
            , "it was the season of Light, it was the season of Darkness,"
            , "it was the spring of hope, it was the winter of despair,"
            , "we had everything before us, we had nothing before us"
    );

    @Test
    public void whenGetDates() throws Exception{

        String in = DocumentDates.getDate(lines);
        System.out.println(in.toString());

            assertThat(in).isEqualTo(DocumentDates.getDate(lines));

    }
}
