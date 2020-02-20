package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.refactor.PdfParser;
import ro.fortech.pdfparser.refactor.PdfService;
import ro.fortech.pdfparser.service.ParsedPdfDto;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfServiceTest {

    @Autowired
    PdfService pdfService;

    @Test
    public void whenParserFile() throws Exception{

        String path = "/2017 SAS balanta 31122017.pdf";

        pdfService.add2Database(path);
    }
}
