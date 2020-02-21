package ro.fortech.pdfparser.service.refactor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import java.io.InputStream;

@Component
public class ImportPdf {
    // String path = "/2017 SAS balanta 31122017.pdf";

    @Autowired
    private PdfParseMethod pdfParseMethod;

    public ParsedPdfDto importPdf(String path) throws Exception {
        InputStream in = new ClassPathResource(path, PdfService.class.getClassLoader()).getInputStream();
        return pdfParseMethod.parse(in);
    }
}