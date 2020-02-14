package ro.fortech.pdfparser;

import lombok.Data;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ifrs_balance_sheets")
public class BalanceSheetEntity extends BaseEntity{

    @Id
    @GeneratedValue
     private long id;
    @NotNull
    @Column(name = "from_date", nullable = false, updatable = false)
    private LocalDate from;

    @NotNull
    @Column(name = "to_date", nullable = false, updatable = false)
    private LocalDate to;

    @OneToMany(mappedBy = "balanceSheet", fetch = FetchType.EAGER)
    private List<BalanceSheetLineEntity> lines = new ArrayList<>();


    public BigDecimal getTotalSolduriInitialeD() {
        return getTotal(BalanceSheetLineEntity::getSolduriInitialeD);
    }

    public BigDecimal getTotalSolduriInitialeC() {
        return getTotal(BalanceSheetLineEntity::getSolduriInitialeC);
    }

    public BigDecimal getTotalRulajeD() {
        return getTotal(BalanceSheetLineEntity::getRulajeD);
    }

    public BigDecimal getTotalRulajeC() {
        return getTotal(BalanceSheetLineEntity::getRulajeC);
    }

    public BigDecimal getTotalTotalRulajeD() {
        return getTotal(BalanceSheetLineEntity::getTotalRulajeD);
    }

    public BigDecimal getTotalTotalRulajeC() {
        return getTotal(BalanceSheetLineEntity::getTotalRulajeC);
    }


    public BigDecimal getTotalSumeTotaleD() {
        return getTotal(BalanceSheetLineEntity::getSumeTotaleD);
    }

    public BigDecimal getTotalSumeTotaleC() {
        return getTotal(BalanceSheetLineEntity::getSumeTotaleC);
    }

    public BigDecimal getTotalSolduriFinaleD() {
        return getTotal(BalanceSheetLineEntity::getSolduriFinaleD);
    }

    public BigDecimal getTotalSolduriFinaleC() {
        return getTotal(BalanceSheetLineEntity::getSolduriFinaleC);
    }

    public BigDecimal getTotal(Function<BalanceSheetLineEntity, BigDecimal> coaColumn) {
        return lines
                .stream()
                .map(coaColumn)
                .reduce(BigDecimal
                        .ZERO, BigDecimal::add);
    }

//    public BigDecimal getTotal(String account, Function<BalanceSheetLineEntity, BigDecimal> balanceColumn) {
//        return lines.stream().filter(a -> a.getAccountIFRS().getAccount().equalsIgnoreCase(account)).map(balanceColumn).reduce(BigDecimal.ZERO, BigDecimal::add);
//    }


    @Override
    public String toString() {
        return "{" +
                "totalSumePrecedenteD=" + getTotalSolduriInitialeD() +
                ", totalSumePrecedenteC=" + getTotalSolduriInitialeC() +
                ", totalRulajeD=" + getTotalRulajeD() +
                ", totalRulajeC=" + getTotalRulajeC() +
                ", totalTotalRulajeD=" + getTotalTotalRulajeD() +
                ", totalTotalRulajeC=" + getTotalTotalRulajeC() +
                ", totalSumeTotaleD=" + getTotalSumeTotaleD() +
                ", totalSumeTotaleC=" + getTotalSumeTotaleC() +
                ", totalSolduriFinaleD=" + getTotalSolduriFinaleD() +
                ", totalSolduriFinaleC=" + getTotalSolduriFinaleC() +
                '}';
    }
}
