package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.PdfLines;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfLinesTest {

    @Autowired
    private PdfLines pdfLines;

    @Test
    public void whenStringArray_thenReturnDate(){
        String[] lines = {"123 , 12131 , 1231 , 12434 , 235395 , 14208 , 429064 , 120597315 , 20908 , 358375 , 90964"};
        ParsedPdfDto dto = new ParsedPdfDto();

        ParsedPdfDto in = pdfLines.addPdfLines(lines,dto);
        System.out.println(in);
    }
}
