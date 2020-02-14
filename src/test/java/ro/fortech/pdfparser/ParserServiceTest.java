package ro.fortech.pdfparser;

import org.junit.jupiter.api.Test;
import ro.fortech.pdfparser.service.ParserService;

class ParserServiceTest {

    @Test
    void importPdf() throws Exception {
        new ParserService().importPdf();
        //new ParserService().create();
    }
}