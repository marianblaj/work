package ro.fortech.pdfparser.entity;


import com.sun.istack.NotNull;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@Entity
@Table(name = "balance_sheet_lines")
public class BalanceSheetLineEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balance_sheet_id",  nullable = false )
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




//    public static ParsedPdfLineDto toDto(BalanceSheetLineEntity balanceSheetLineEntity) {
//        ParsedPdfLineDto pojo = new ParsedPdfLineDto();
//        pojo.setAccNr(balanceSheetLineEntity.getAccNr());
//        this.solduriInitialeC = solduriInitialeC;
//        this.rulajeD = rulajeD;
//        this.rulajeC = rulajeC;
//        this.totalRulajeC = totalRulajeC;
//        this.totalRulajeD = totalRulajeD;
//        this.sumeTotaleD = sumeTotaleD;
//        this.sumeTotaleC = sumeTotaleC;
//        this.solduriFinaleD = solduriFinaleD;
//        this.solduriFinaleC = solduriFinaleC;
//        return pojo;
//    }


    public static BalanceSheetLineEntity update(ParsedPdfLineDto pojo) {
        BalanceSheetLineEntity bal = new BalanceSheetLineEntity();

        bal.setSolduriInitialeD(pojo.getSolduriInitialeD());
        bal.setSolduriInitialeC(pojo.getSolduriInitialeC());
        bal.setRulajeD(pojo.getRulajePerioadaD());
        bal.setRulajeC(pojo.getRulajePerioadaC());
        bal.setTotalRulajeD(pojo.getTotalRulajeD());
        bal.setTotalRulajeC(pojo.getTotalRulajeC());
        bal.setSumeTotaleD(pojo.getSumeTotaleD());
        bal.setSumeTotaleC(pojo.getSumeTotaleC());
        bal.setSolduriFinaleD(pojo.getSolduriFinaleD());
        bal.setSolduriFinaleC(pojo.getSolduriFinaleC());


        return bal;
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


    public ParsedPdfLineDto toDto() {
        ParsedPdfLineDto pdf = new ParsedPdfLineDto();
        pdf.setAccNr(getAccNr());
        pdf.setSolduriInitialeD(getSolduriInitialeD());
        pdf.setSolduriInitialeC(getSolduriInitialeC());
        pdf.setRulajePerioadaD(getRulajeD());
        pdf.setRulajePerioadaC(getRulajeC());
        pdf.setTotalRulajeD(getTotalRulajeD());
        pdf.setTotalRulajeC(getTotalRulajeC());
        pdf.setSumeTotaleD(getSumeTotaleD());
        pdf.setSumeTotaleC(getSumeTotaleC());
        pdf.setSolduriFinaleD(getSolduriFinaleD());
        pdf.setSolduriFinaleC(getSolduriFinaleC());
        //BalanceSheetLineEntity balanceSheetLineEntity = new BalanceSheetLineEntity();
//        pdf.setLines(lines
//                .stream()
//                .map(BalanceSheetLineEntity::toDto)
//                .collect(Collectors.toList()));
        return pdf;
    }
//    @Override
//    public String toString() {
//        return "BalanceSheetLineEntity{" +
//                "balanceSheet=" + balanceSheet +
//                ", accNr='" + accNr + '\'' +
//                ", solduriInitialeD=" + solduriInitialeD +
//                ", solduriInitialeC=" + solduriInitialeC +
//                ", rulajeD=" + rulajeD +
//                ", rulajeC=" + rulajeC +
//                ", totalRulajeC=" + totalRulajeC +
//                ", totalRulajeD=" + totalRulajeD +
//                ", sumeTotaleD=" + sumeTotaleD +
//                ", sumeTotaleC=" + sumeTotaleC +
//                ", solduriFinaleD=" + solduriFinaleD +
//                ", solduriFinaleC=" + solduriFinaleC +
//                '}';
//    }
}
