package ro.fortech.pdfparser.entity;


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

    public List<BalanceSheetLineEntity> getLines() {
        return lines;
    }

    @NotNull
    @Column(name = "nume_firma", nullable = false, updatable = false)
    private String numeFirma;

    @NotNull
    @Column(name = "cod_fiscal", nullable = false, updatable = false)
    private String cf;

    @NotNull
    @Column(name = "from_date", nullable = false, updatable = false)
    private LocalDate from;

    @NotNull
    @Column(name = "to_date", nullable = false, updatable = false)
    private LocalDate to;

    @OneToMany(mappedBy = "balanceSheet", fetch = FetchType.EAGER)
    private List<BalanceSheetLineEntity> lines = new ArrayList<>();




    public void setLines(List<BalanceSheetLineEntity> lines) {
        this.lines = lines;
    }

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

    public BigDecimal getTotalSumePrecedenteD() {
        return getTotal(BalanceSheetLineEntity::getSolduriInitialeD);
    }

    public BigDecimal getTotalSumePrecedenteC() {
        return getTotal(BalanceSheetLineEntity::getSolduriInitialeC);
    }

    public BigDecimal getRulajeD() {
        return getTotal(BalanceSheetLineEntity::getRulajeD);
    }

    public BigDecimal getRulajeC() {
        return getTotal(BalanceSheetLineEntity::getRulajeC);
    }

    public BigDecimal getTotalRulajeD() {
        return getTotal(BalanceSheetLineEntity::getTotalRulajeD);
    }

    public BigDecimal getTotalRulajeC() {
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
//
//    public BigDecimal getTotal(String account, Function<BalanceSheetLineEntity, BigDecimal> balanceColumn) {
//        return lines.stream().filter(a -> a.getAccountIFRS().getAccount().equalsIgnoreCase(account)).map(balanceColumn).reduce(BigDecimal.ZERO, BigDecimal::add);
//    }


    @Override
    public String toString() {
        return "BalanceSheetEntity{" +
                "from=" + from +
                ", to=" + to +
                ", totalSumePrecedenteD=" + getTotalSumePrecedenteD() +
                ", totalSumePrecedenteC=" + getTotalSumePrecedenteC() +
                ", rulajeD=" + getRulajeD() +
                ", rulajeC=" + getRulajeC() +
                ", totalRulajeD=" + getTotalRulajeD() +
                ", totalRulajeC=" + getTotalRulajeC() +
                ", totalSumeTotaleD=" + getTotalSumeTotaleD() +
                ", totalSumeTotaleC=" + getTotalSumeTotaleC() +
                ", totalSolduriFinaleD=" + getTotalSolduriFinaleD() +
                ", totalSolduriFinaleC=" + getTotalSolduriFinaleC() +
                '}';
    }
}