package ro.fortech.pdfparser.service;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Data
public class ParsedPdfDto {

    private String numeFirma;
    private String cf;
    private LocalDate from;
    private LocalDate to;
    private List<ParsedPdfLineDto> lines = new ArrayList<>();


}
