package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.refactor.PdfLines;
import ro.fortech.pdfparser.refactor.PdfParser;
import ro.fortech.pdfparser.refactor.PdfService;
import ro.fortech.pdfparser.service.ParsedPdfDto;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfParserTest {

    @Autowired
    PdfParser pdfParser;

    @Test
    public void whenParserFile() throws Exception{

        InputStream inputStream = new ClassPathResource("/2017 SAS balanta 31122017.pdf", PdfService.class.getClassLoader()).getInputStream();
        InputStream inputStreamForVerify = new ClassPathResource("/2017 SAS balanta 31122017.pdf", PdfService.class.getClassLoader()).getInputStream();

        ParsedPdfDto in = pdfParser.parse(inputStream);
        System.out.println(in.toString());

        assertThat(in.equals(pdfParser.parse(inputStreamForVerify)));
    }
}
