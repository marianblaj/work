package ro.fortech.pdfparser.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ifrs_balance_sheets")
public class BalanceSheetEntity {

    @Id
    @GeneratedValue
     private long id;


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

    @OneToMany(cascade = CascadeType.ALL )
    private List<BalanceSheetLineEntity> lines = new ArrayList<>();

    public BalanceSheetEntity(String numeFirma,String cf,LocalDate from, LocalDate to)
    {
        this.numeFirma=numeFirma;
        this.cf=cf;
        this.from=from;
        this.to=to;
    }

    public BalanceSheetEntity update(ParsedPdfDto pojo) {
        BalanceSheetEntity bal = new BalanceSheetEntity();
        bal.cf = pojo.getCf();
        bal.from = pojo.getFrom();
        bal.to = pojo.getTo();
        //BalanceSheetLineEntity balanceSheetLineEntity = new BalanceSheetLineEntity();
        bal.lines = pojo.getLines()
                .stream()
                .map(ParsedPdfLineDto::update)
                .collect(Collectors.toList());
        return bal;
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

    public void setLines(List<BalanceSheetLineEntity> lines) {
        this.lines = lines;
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


//
}
