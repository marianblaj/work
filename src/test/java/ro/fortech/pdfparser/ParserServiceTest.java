package ro.fortech.pdfparser;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import ro.fortech.pdfparser.service.ParserPdfService;
import ro.fortech.pdfparser.service.ParserService;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
class ParserServiceTest {


    @Test
    void importPdf() throws Exception {
        // ParserPdfService().importPdf();

        //new ParserService().importPdf();
    }

//    @Test
//    void DetailsForSheet() throws Exception {
//        InputStream file = new ClassPathResource(
//                "/2017 SAS balanta 31122017.pdf", ParserPdfService.class.getClassLoader()).getInputStream();
//
//            PDFTextStripper pdfStripper = null;
//            PDDocument pdDoc = null;
//            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
//            parser.parse();
//            COSDocument cosDoc = parser.getDocument();
//            pdfStripper = new PDFTextStripper();
//            pdDoc = new PDDocument(cosDoc);
//            pdfStripper.setSortByPosition(true);
//            pdfStripper.setStartPage(0);
//            pdfStripper.setEndPage(pdDoc.getNumberOfPages());
//
//            String parsedText = pdfStripper.getText(pdDoc);
//
//            String[] lines = parsedText.split(System.lineSeparator());
//
//            ParsedPdfDto dto = new ParserService().getDetails(parsedText, lines[0]);
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate from = LocalDate.parse("2017-12-01",formatter);
//            LocalDate to = LocalDate.parse("2017-12-31",formatter);
//            List<ParsedPdfLineDto> parsedLines = new ArrayList<>();
//
//            ParsedPdfDto found = new ParsedPdfDto("SOFT APLICATIV SI SERVICII S.A.   ", "RO2577839   ", from, to, parsedLines);
//
//            assertThat(found).isEqualTo(dto);
//
//    }


    //getDetails Method test

}