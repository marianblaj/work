package ro.fortech.pdfparser.refactortests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.PdfParseMethod;
import ro.fortech.pdfparser.service.refactor.PdfService;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PdfParseMethodTest {

    @Autowired
    PdfParseMethod pdfParseMethod;

    @Test
    public void testParseMethod() throws Exception {
        try {
            InputStream in = new ClassPathResource("/2017 SAS balanta 31122017.pdf", PdfService.class.getClassLoader()).getInputStream();

            ParsedPdfDto parsedPdfDto = pdfParseMethod.parse(in);

            assertThat(parsedPdfDto).isEqualTo(pdfParseMethod.parse(new ClassPathResource("/2017 SAS balanta 31122017.pdf", PdfService.class.getClassLoader()).getInputStream()));

            System.out.println(parsedPdfDto.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

