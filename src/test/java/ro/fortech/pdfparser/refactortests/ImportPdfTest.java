package ro.fortech.pdfparser.refactortests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.ImportPdf;

import java.time.LocalDate;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class ImportPdfTest {

    @Autowired
    private ImportPdf service; // INIT

    @Test
    public void whenStringPathValid_thenReturnNewParsedPdfDto() throws Exception{

        String from = "2017-12-31";
        LocalDate localFrom = LocalDate.parse(from);


        String to = "2017-12-01";
        LocalDate localTo = LocalDate.parse(to);


        ParsedPdfDto dtoExpected = new ParsedPdfDto("SOFT APLICATIV SI SERVICII S.A.   ", "RO2577839   ",localTo, localFrom);


        ParsedPdfDto in = service.importPdf("/2017 SAS balanta 31122017.pdf"); // EXEC
        System.out.println(in.toString()); // VERIFY


        assertThat(in.getNumeFirma()).isEqualTo(dtoExpected.getNumeFirma()).isNotNull();
        assertThat(in.getCf()).isEqualTo(dtoExpected.getCf()).isNotNull();
        assertThat(in.getFrom()).isEqualTo(dtoExpected.getFrom()).isNotNull();


        assertThat(in.getLines()).isNotEqualTo(dtoExpected.getLines());


        //asserthat ce intra in IN prin metoda is equal to ce facem manually (new parsedpdtfo...)
    }

}