package ro.fortech.pdfparser;

import lombok.Data;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.BigDecimalProvider;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfLineParser;
import ro.fortech.pdfparser.service.refactor.PdfLines;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class PdfLinesTest {

    String[] lines= {"Hello", "World"};

    @Autowired
    PdfLines pdfLines;

    @Autowired
    ImportPdf importPdf;

    @Test
    public void whenLineParser() throws Exception{

        ParsedPdfDto parsedPdfDto = importPdf.importPdf("/2017 SAS balanta 31122017.pdf");

        ParsedPdfDto dto = pdfLines.addPdfLines(lines,parsedPdfDto);

        //ParsedPdfDto found = new ParsedPdfDto()

        System.out.println(dto.toString());

        //asserthat dto is equal to?

//        assertThat(dto).isEqualTo(pdfLines.addPdfLines(lines,parsedPdfDto));

        //asserthat ce facem prin new Parsedpdfdto manually is equal to ce intra in prin metoda.

    }

}
