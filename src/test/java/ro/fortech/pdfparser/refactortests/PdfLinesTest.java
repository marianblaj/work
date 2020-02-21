package ro.fortech.pdfparser.refactortests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfLines;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class PdfLinesTest {

    String[] lines1= {"1012 CAPITAL SUBSCRIS VARSAT              0.00        421 237.50              0.00              0.00              0.00              0.00              0.00        421 237.50              0.00        421 237.50"
            , "105 REZERVE  DIN REEVALUARE              0.00      1 756 702.32              0.00              0.00              0.00              0.00              0.00      1 756 702.32              0.00      1 756 702.32"};

    @Autowired
    PdfLines pdfLines;

    @Autowired
    ImportPdf importPdf;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Test
    public void whenLineParser() throws Exception{



        String from = "2017-12-31";
        LocalDate localFrom = LocalDate.parse(from);
        String to = "2017-12-01";
        LocalDate localTo = LocalDate.parse(to);
        List<ParsedPdfLineDto> lines = new ArrayList<>();
        ParsedPdfDto dtoExpected = new ParsedPdfDto("SOFT APLICATIV SI SERVICII S.A.   ", "RO2577839   ",localTo, localFrom, lines);

        ParsedPdfLineDto lineExpected = new ParsedPdfLineDto("105", BigDecimal.valueOf(0.00).setScale(2), BigDecimal.valueOf(1756702.32).setScale(2), BigDecimal.valueOf(0.00).setScale(2), BigDecimal.valueOf(0.00).setScale(2),
                BigDecimal.valueOf(0.00).setScale(2), BigDecimal.valueOf(0.00).setScale(2), BigDecimal.valueOf(0.00).setScale(2), BigDecimal.valueOf(1756702.32).setScale(2), BigDecimal.valueOf(0.00).setScale(2)
                ,BigDecimal.valueOf(1756702.32).setScale(2) );


        dtoExpected = pdfLines.addPdfLines(lines1,dtoExpected);

        System.out.println( dtoExpected.getLines().get(1));
        System.out.println( lineExpected );


        Assert.assertEquals(dtoExpected.getLines().get(1), lineExpected);




        //asserthat dto is equal to?

//        assertThat(dto).isEqualTo(pdfLines.addPdfLines(lines,parsedPdfDto));

        //asserthat ce facem prin new Parsedpdfdto manually is equal to ce intra in prin metoda.

    }

}
