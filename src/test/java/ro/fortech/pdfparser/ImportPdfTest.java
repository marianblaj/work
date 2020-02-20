package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.refactor.ImportPdf;
import ro.fortech.pdfparser.service.ParsedPdfDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportPdfTest {

    @Autowired
    private ImportPdf service ;

    @Test
    public void whenStringPathValid_thenReturnNewParsedPdfDto() throws Exception{

        ParsedPdfDto in = service.importPdf("/2017 SAS balanta 31122017.pdf");
        System.out.println(in.toString());

            assertThat(in.equals(service.importPdf("/2017 SAS balanta 31122017.pdf")));

    }

}