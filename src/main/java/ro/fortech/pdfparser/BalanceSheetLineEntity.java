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
    private BigDecimal sumePrecedenteD;
    private BigDecimal sumePrecedenteC;
    private BigDecimal rulajeD;
    private BigDecimal rulajeC;
    private BigDecimal sumeTotaleD;
    private BigDecimal sumeTotaleC;
    private BigDecimal solduriFinaleD;
    private BigDecimal solduriFinaleC;

    @Override
    public String toString() {
        return "{" +
                "accNr='" + accNr + '\'' +
                ", sumePrecedenteD=" + sumePrecedenteD +
                ", sumePrecedenteC=" + sumePrecedenteC +
                ", rulajeD=" + rulajeD +
                ", rulajeC=" + rulajeC +
                ", sumeTotaleD=" + sumeTotaleD +
                ", sumeTotaleC=" + sumeTotaleC +
                ", solduriFinaleD=" + solduriFinaleD +
                ", solduriFinaleC=" + solduriFinaleC +
                '}';
    }
}