package ro.fortech.pdfparser.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Function;

@Getter
@Setter
public class ParsedPdfDto {

    private String numeFirma;



    private String cf;
    private LocalDate from;
    private LocalDate to;
    private List<ParsedPdfLineDto> lines = new ArrayList<>();

    public BigDecimal getTotal(Function<ParsedPdfLineDto, BigDecimal> coaColumn) {
        return lines
                .stream()
                .map(coaColumn)
                .reduce(BigDecimal
                        .ZERO, BigDecimal::add);
    }

}
