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

    public ParsedPdfDto(String numeFirma,String cf, LocalDate from, LocalDate to, List<ParsedPdfLineDto> lines) {
        this.numeFirma = numeFirma;
        this.cf =cf;
        this.from = from;
        this.to = to;
        this.lines = lines;
    }

    public ParsedPdfDto(String numeFirma,String cf, LocalDate from, LocalDate to) {
        this.numeFirma = numeFirma;
        this.cf =cf;
        this.from = from;
        this.to = to;
    }

    public ParsedPdfDto(){}


    public String getNumeFirma() {
        return numeFirma;
    }

    public void setNumeFirma(String numeFirma) {
        this.numeFirma = numeFirma;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public List<ParsedPdfLineDto> getLines() {
        return lines;
    }

    public void setLines(List<ParsedPdfLineDto> lines) {
        this.lines = lines;
    }

    public BigDecimal getTotal(Function<ParsedPdfLineDto, BigDecimal> coaColumn) {
        return lines
                .stream()
                .map(coaColumn)
                .reduce(BigDecimal
                        .ZERO, BigDecimal::add);
    }

}
