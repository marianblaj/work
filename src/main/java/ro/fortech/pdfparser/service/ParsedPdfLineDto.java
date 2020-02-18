package ro.fortech.pdfparser.service;

import lombok.Data;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;

import java.math.BigDecimal;

@Data
public class ParsedPdfLineDto {

    public String getAccNr() {
        return accNr;
    }

    public void setAccNr(String accNr) {
        this.accNr = accNr;
    }

    private String accNr;
    private BigDecimal solduriInitialeD;
    private BigDecimal solduriInitialeC;
    private BigDecimal rulajePerioadaD;
    private BigDecimal rulajePerioadaC;
    private BigDecimal totalRulajeD;
    private BigDecimal totalRulajeC;
    private BigDecimal sumeTotaleD;
    private BigDecimal sumeTotaleC;
    private BigDecimal solduriFinaleD;
    private BigDecimal solduriFinaleC;


    public ParsedPdfLineDto toPojo() {
        ParsedPdfLineDto pojo = new ParsedPdfLineDto();
        this.solduriInitialeD = solduriInitialeD;
        this.solduriInitialeC = solduriInitialeC;
        this.rulajePerioadaD = rulajePerioadaD;
        this.rulajePerioadaC = rulajePerioadaC;
        this.totalRulajeC = totalRulajeC;
        this.totalRulajeD = totalRulajeD;
        this.sumeTotaleD = sumeTotaleD;
        this.sumeTotaleC = sumeTotaleC;
        this.solduriFinaleD = solduriFinaleD;
        this.solduriFinaleC = solduriFinaleC;
        return pojo;
    }

    public static BalanceSheetLineEntity update(ParsedPdfLineDto pojo) {

        BalanceSheetLineEntity bal = new BalanceSheetLineEntity();

        bal.setAccNr(pojo.getAccNr());


        bal.setBalanceSheet(new BalanceSheetEntity());

        bal.setSolduriInitialeD(pojo.getSolduriInitialeD());
        bal.setSolduriInitialeC(pojo.getSolduriInitialeC());
        bal.setRulajeD(pojo.getRulajePerioadaD());
        bal.setRulajeC(pojo.getRulajePerioadaC());
        bal.setTotalRulajeC(pojo.getTotalRulajeC());
        bal.setTotalRulajeD(pojo.getTotalRulajeD());
        bal.setSumeTotaleD(pojo.getSumeTotaleD());
        bal.setSumeTotaleC(pojo.getSumeTotaleC());
        bal.setSolduriFinaleD(pojo.getSolduriFinaleD());
        bal.setSolduriFinaleC(pojo.getSolduriFinaleC());

        return bal;
    }

    public BigDecimal getSolduriInitialeD() {
        return solduriInitialeD;
    }

    public void setSolduriInitialeD(BigDecimal solduriInitialeD) {
        this.solduriInitialeD = solduriInitialeD;
    }

    public BigDecimal getSolduriInitialeC() {
        return solduriInitialeC;
    }

    public void setSolduriInitialeC(BigDecimal solduriInitialeC) {
        this.solduriInitialeC = solduriInitialeC;
    }

    public BigDecimal getRulajePerioadaD() {
        return rulajePerioadaD;
    }

    public void setRulajePerioadaD(BigDecimal rulajePerioadaD) {
        this.rulajePerioadaD = rulajePerioadaD;
    }

    public BigDecimal getRulajePerioadaC() {
        return rulajePerioadaC;
    }

    public void setRulajePerioadaC(BigDecimal rulajePerioadaC) {
        this.rulajePerioadaC = rulajePerioadaC;
    }

    public BigDecimal getTotalRulajeC() {
        return totalRulajeC;
    }

    public void setTotalRulajeC(BigDecimal totalRulajeC) {
        this.totalRulajeC = totalRulajeC;
    }

    public BigDecimal getTotalRulajeD() {
        return totalRulajeD;
    }

    public void setTotalRulajeD(BigDecimal totalRulajeD) {
        this.totalRulajeD = totalRulajeD;
    }

    public BigDecimal getSumeTotaleD() {
        return sumeTotaleD;
    }

    public void setSumeTotaleD(BigDecimal sumeTotaleD) {
        this.sumeTotaleD = sumeTotaleD;
    }

    public BigDecimal getSumeTotaleC() {
        return sumeTotaleC;
    }

    public void setSumeTotaleC(BigDecimal sumeTotaleC) {
        this.sumeTotaleC = sumeTotaleC;
    }

    public BigDecimal getSolduriFinaleD() {
        return solduriFinaleD;
    }

    public void setSolduriFinaleD(BigDecimal solduriFinaleD) {
        this.solduriFinaleD = solduriFinaleD;
    }

    public BigDecimal getSolduriFinaleC() {
        return solduriFinaleC;
    }

    public void setSolduriFinaleC(BigDecimal solduriFinaleC) {
        this.solduriFinaleC = solduriFinaleC;
    }
}
