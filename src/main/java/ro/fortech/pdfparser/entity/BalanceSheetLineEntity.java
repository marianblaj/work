package ro.fortech.pdfparser.entity;


import com.sun.istack.NotNull;
import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;


import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ifrs_balance_sheet_lines")
public class BalanceSheetLineEntity {

    @Id
    @GeneratedValue
    private long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "balance_sheets_id", nullable = false)
    private BalanceSheetEntity balanceSheet;


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


    public ParsedPdfLineDto toPojo() {
        ParsedPdfLineDto pojo = new ParsedPdfLineDto();
        this.balanceSheet = balanceSheet;
        this.solduriInitialeD = solduriInitialeD;
        this.solduriInitialeC = solduriInitialeC;
        this.rulajeD = rulajeD;
        this.rulajeC = rulajeC;
        this.totalRulajeC = totalRulajeC;
        this.totalRulajeD = totalRulajeD;
        this.sumeTotaleD = sumeTotaleD;
        this.sumeTotaleC = sumeTotaleC;
        this.solduriFinaleD = solduriFinaleD;
        this.solduriFinaleC = solduriFinaleC;
        return pojo;
    }


    public BalanceSheetEntity getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(BalanceSheetEntity balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public String getAccNr() {
        return accNr;
    }

    public void setAccNr(String accNr) {
        this.accNr = accNr;
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

    public BigDecimal getRulajeD() {
        return rulajeD;
    }

    public void setRulajeD(BigDecimal rulajeD) {
        this.rulajeD = rulajeD;
    }

    public BigDecimal getRulajeC() {
        return rulajeC;
    }

    public void setRulajeC(BigDecimal rulajeC) {
        this.rulajeC = rulajeC;
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


    @Override
    public String toString() {
        return "BalanceSheetLineEntity{" +
                "balanceSheet=" + balanceSheet +
                ", accNr='" + accNr + '\'' +
                ", solduriInitialeD=" + solduriInitialeD +
                ", solduriInitialeC=" + solduriInitialeC +
                ", rulajeD=" + rulajeD +
                ", rulajeC=" + rulajeC +
                ", totalRulajeC=" + totalRulajeC +
                ", totalRulajeD=" + totalRulajeD +
                ", sumeTotaleD=" + sumeTotaleD +
                ", sumeTotaleC=" + sumeTotaleC +
                ", solduriFinaleD=" + solduriFinaleD +
                ", solduriFinaleC=" + solduriFinaleC +
                '}';
    }
}
