package ro.fortech.pdfparser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.fortech.pdfparser.service.ParserPdfService;
import ro.fortech.pdfparser.service.ParserService;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfParser;

class ParserServiceTest {

    @Test
    void importPdf() throws Exception {
        String path = "/2017 SAS balanta 31122017.pdf";
        //new ParserPdfService().importPdf();
      //  new ParserPdfService().importPdf();
       // new ImportPdf().importPdf(path);
        new ImportPdf(new PdfParser()).importPdf(path);
    }
}