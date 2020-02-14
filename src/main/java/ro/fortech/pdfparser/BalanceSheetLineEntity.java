package ro.fortech.pdfparser;


import com.sun.istack.NotNull;
import lombok.Data;

import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@Entity
@Table(name = "ifrs_balance_sheet_lines")
public class BalanceSheetLineEntity {

    @Id
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "balance_sheet_id",  nullable = false)
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
