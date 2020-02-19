package ro.fortech.pdfparser.service.extras;

import lombok.Data;
import ro.fortech.pdfparser.service.extras.ParsedPdfLineNameDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParsedPdfNameDto {

    private String numeFirma;
    private List<ParsedPdfLineNameDto> lines = new ArrayList<>();
}
