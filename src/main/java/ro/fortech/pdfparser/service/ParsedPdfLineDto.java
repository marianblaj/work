package ro.fortech.pdfparser.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParsedPdfLineDto {


    private String accNr;
    private BigDecimal solduriInitialeD;
    private BigDecimal solduriInitialeC;
    private BigDecimal rulajeD;
    private BigDecimal rulajeC;
    private BigDecimal totalRulajeC;
    private BigDecimal totalRulajeD;
    private BigDecimal sumeTotaleD;
    private BigDecimal sumeTotaleC;
    private BigDecimal solduriFinaleD;
    private BigDecimal solduriFinaleC;
}
