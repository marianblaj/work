package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.ImportPdf;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class ImportPdfTest {

    @Autowired
    private ImportPdf service; // INIT

    @Test
    public void whenStringPathValid_thenReturnNewParsedPdfDto() throws Exception{

        ParsedPdfDto in = service.importPdf("/2017 SAS balanta 31122017.pdf"); // EXEC
        System.out.println(in.toString()); // VERIFIY
        assertThat(in).isEqualTo(new ParsedPdfDto());

        //asserthat ce intra in IN prin metoda is equal to ce facem manually (new parsedpdtfo...)
    }

}