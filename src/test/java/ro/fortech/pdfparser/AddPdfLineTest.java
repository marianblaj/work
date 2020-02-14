package ro.fortech.pdfparser;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.AddPdfLine;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfLineParser;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddPdfLineTest {

    @Autowired
    AddPdfLine addPdfLine;


    @Test
    public void whenPathIsArrayOfStringAndParsedPdfDto_thenReturnNewParsedParsedPdfDto() throws Exception {
        ParsedPdfDto parsedPdfDto=new ParsedPdfDto();
        assertThat(addPdfLine.addPdfLines(new String[]{"1", "2"},parsedPdfDto)).isNotNull();


    }

}