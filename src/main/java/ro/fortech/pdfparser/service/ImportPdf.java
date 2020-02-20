package ro.fortech.pdfparser.service;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

public class ImportPdf {

    private ParserService service;

    public ParsedPdfDto importPdf(String path) throws Exception {
        // File file = FileUtils.getFile(BL_FILENAME);

        InputStream in = new ClassPathResource(
                path, ParserService.class.getClassLoader()).getInputStream();

        return service.parse(in);
    }
}
