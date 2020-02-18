package ro.fortech.pdfparser.service.refactor;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParserPdfService;

import java.io.InputStream;

@Service
public class ImportPdf {

    // String path = "/2017 SAS balanta 31122017.pdf";

    private  PdfParser pdfParser;

    public ImportPdf(PdfParser pdfParser) {
        this.pdfParser = pdfParser;
    }


    public ParsedPdfDto importPdf(String path) throws Exception {
        InputStream in = new ClassPathResource(path, ParserPdfService.class.getClassLoader()).getInputStream();
        return pdfParser.parse(in);
    }
}
