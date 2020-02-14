package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.ImportPdf;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class ImportPdfTest {

    @Autowired
    private ImportPdf importPdf ;

    @Test
    public void whenStringPathValid_thenReturnNewParsedPdfDto() throws Exception{

        ParsedPdfDto parsedPdfDto = importPdf.importPdf("/2017 SAS balanta 31122017.pdf");
        System.out.println(parsedPdfDto.toString());
        assertThat(parsedPdfDto).isEqualTo(importPdf.importPdf("/2017 SAS balanta 31122017.pdf"));

    }

}
