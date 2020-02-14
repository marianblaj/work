package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.DocumentDates;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)

public class DocumentDatesTest {



    @Test
    public void whenStringPathValid_thenReturnNDate() throws Exception{

        String date = DocumentDates.getDate("SOFT APLICATIV SI SERVICII S.A\n"+"c.f. RO2577839\n"+"Balanta de verificare\n"+"01.12.2017-- 31.12.2017");
        System.out.println("---------------------------- ");
        System.out.println(date);
        System.out.println("---------------------------- ");
        assertThat(date).isEqualTo("01.12.2017-- 31.12.2017");

    }

}
