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


    public BigDecimal getTotalSumePrecedenteD() {
        return getTotal(ParsedPdfLineDto::getSolduriInitialeD);
    }

    public BigDecimal getTotalSumePrecedenteC() {
        return getTotal(ParsedPdfLineDto::getSolduriInitialeC);
    }

    public BigDecimal getRulajeD() {
        return getTotal(ParsedPdfLineDto::getRulajeD);
    }

    public BigDecimal getRulajeC() {
        return getTotal(ParsedPdfLineDto::getRulajeC);
    }

    public BigDecimal getTotalRulajeD() {
        return getTotal(ParsedPdfLineDto::getTotalRulajeD);
    }

    public BigDecimal getTotalRulajeC() {
        return getTotal(ParsedPdfLineDto::getTotalRulajeC);
    }

    public BigDecimal getTotalSumeTotaleD() {
        return getTotal(ParsedPdfLineDto::getSumeTotaleD);
    }

    public BigDecimal getTotalSumeTotaleC() {
        return getTotal(ParsedPdfLineDto::getSumeTotaleC);
    }

    public BigDecimal getTotalSolduriFinaleD() {
        return getTotal(ParsedPdfLineDto::getSolduriFinaleD);
    }

    public BigDecimal getTotalSolduriFinaleC() {
        return getTotal(ParsedPdfLineDto::getSolduriFinaleC);
    }

    public BigDecimal getTotal(Function<ParsedPdfLineDto, BigDecimal> coaColumn) {
        return lines
                .stream()
                .map(coaColumn)
                .reduce(BigDecimal
                        .ZERO, BigDecimal::add);
    }

}
