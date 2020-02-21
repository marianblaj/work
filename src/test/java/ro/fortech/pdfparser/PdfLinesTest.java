package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.refactor.PdfLineParser;
import ro.fortech.pdfparser.refactor.PdfLines;
import ro.fortech.pdfparser.refactor.PdfService;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfLinesTest {
    
    @Autowired
    PdfLines pdfLines;

    @Test
    public void whenAddLines() throws Exception{

        String[] lines = { "112 , 232 , 32 , 323 , 242 , 32 , 42 , 24 , 454 , 546 , 5654 , 4334" , "200 , 232 , 232 , 32 , 323 , 242 , 32 , 42 , 24 , 454 , 546 , 5654" };
        ParsedPdfDto dto = new ParsedPdfDto();

        ParsedPdfDto in = pdfLines.addPdfLines(lines,dto);
        System.out.println(in.toString());

        assertThat(in).isEqualTo(pdfLines.addPdfLines(lines,dto));

    }
    
}
