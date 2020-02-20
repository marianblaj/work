package ro.fortech.pdfparser.service;

import lombok.Data;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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


    public static BalanceSheetLineEntity update(ParsedPdfLineDto pojo) {
        BalanceSheetLineEntity bal = new BalanceSheetLineEntity();
        bal.setSolduriFinaleD(pojo.getSolduriInitialeD());
        bal.setSolduriFinaleC(pojo.getSolduriInitialeC());
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

    public List<ParsedPdfLineDto> toDto(List<BalanceSheetLineEntity> balanceSheetLineEntities) {

        List<ParsedPdfLineDto> pojo = new ArrayList<>();
        for(BalanceSheetLineEntity sheetLineEntity : balanceSheetLineEntities) {
            ParsedPdfLineDto parsedPdfLineDto = new ParsedPdfLineDto();

            parsedPdfLineDto.setAccNr(sheetLineEntity.getAccNr());
            parsedPdfLineDto.setSolduriInitialeD(sheetLineEntity.getSolduriInitialeD());
            parsedPdfLineDto.setSolduriInitialeC(sheetLineEntity.getSolduriInitialeC());
            parsedPdfLineDto.setRulajePerioadaD(sheetLineEntity.getRulajeD());
            parsedPdfLineDto.setRulajePerioadaC(sheetLineEntity.getRulajeC());
            parsedPdfLineDto.setTotalRulajeD(sheetLineEntity.getTotalRulajeD());
            parsedPdfLineDto.setTotalRulajeC(sheetLineEntity.getTotalRulajeC());
            parsedPdfLineDto.setSumeTotaleD(sheetLineEntity.getSumeTotaleD());
            parsedPdfLineDto.setSumeTotaleC(sheetLineEntity.getSumeTotaleC());
            parsedPdfLineDto.setSolduriFinaleD(sheetLineEntity.getSolduriFinaleD());
            parsedPdfLineDto.setSolduriFinaleC(sheetLineEntity.getSolduriFinaleC());

            pojo.add(parsedPdfLineDto);
        }
        return pojo;
    }

}
