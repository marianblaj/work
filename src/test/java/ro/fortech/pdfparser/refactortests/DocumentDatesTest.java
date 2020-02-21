package ro.fortech.pdfparser.refactortests;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.DocumentDates;
import ro.fortech.pdfparser.service.refactor.ImportPdf;

import javax.print.Doc;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DocumentDatesTest {

    String lines = String.join("\n"
            , "It was the best of times, it was the worst of times,"
            , "it was the age of wisdom, it was the age of foolishness,"
            , "01.12.2017 -- 31.12.2017"
            , "01.12.2017 -- 31.12.2017"
            , "it was the spring of hope, it was the winter of despair,"
            , "we had everything before us, we had nothing before us"
    );


    @Test
    public void whenGetDate() throws Exception {

        String date = "01.12.2017 -- 31.12.2017";
        String in = DocumentDates.getDate(lines);

        System.out.println(in.toString());

        assertThat(in).isEqualTo(date);


    }



}
