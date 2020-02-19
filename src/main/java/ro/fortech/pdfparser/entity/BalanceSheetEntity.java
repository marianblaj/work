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
public class BalanceSheetEntity extends BaseEntity {

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

    @OneToMany(mappedBy = "balanceSheet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BalanceSheetLineEntity> lines = new ArrayList<>();

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

    public BalanceSheetEntity toBalanceSheetEntity(ParsedPdfDto pojo) {
        BalanceSheetEntity bal = new BalanceSheetEntity();
        bal.setNumeFirma(pojo.getNumeFirma());
        bal.setCf(pojo.getCf());
        bal.setFrom(pojo.getFrom());
        bal.setTo(pojo.getTo());
        List<BalanceSheetLineEntity> balanceSheetLineEntities = pojo.getLines()
                .stream()
                .map(ParsedPdfLineDto::update)
                .collect(Collectors.toList());
        balanceSheetLineEntities
                .forEach(balanceSheetLineEntity -> balanceSheetLineEntity.setBalanceSheet(bal));
        bal.setLines(balanceSheetLineEntities);
        System.out.println("--------------------------");
        System.out.println(bal.getNumeFirma());
        System.out.println("--------------------------");
        return bal;
    }

//    public ParsedPdfDto toPojo() {
//        ParsedPdfDto pdf = new ParsedPdfDto();
//        pdf.setCf(cf);
//        pdf.setFrom(from);
//        pdf.setTo(to);
//        //BalanceSheetLineEntity balanceSheetLineEntity = new BalanceSheetLineEntity();
//        pdf.setLines(lines
//                .stream()
//                .map(BalanceSheetLineEntity::update)
//                .collect(Collectors.toList()));
//        return pdf;
//    }


    //
//    public String getNumeFirma() {
//        return numeFirma;
//    }
//
//    public void setNumeFirma(String numeFirma) {
//        this.numeFirma = numeFirma;
//    }
//
//    public String getCf() {
//        return cf;
//    }
//
//    public void setCf(String cf) {
//        this.cf = cf;
//    }
//
//    public LocalDate getFrom() {
//        return from;
//    }
//
//    public void setFrom(LocalDate from) {
//        this.from = from;
//    }
//
//    public LocalDate getTo() {
//        return to;
//    }
//
//    public void setTo(LocalDate to) {
//        this.to = to;
//    }
//
//    public List<BalanceSheetLineEntity> getLines() {
//        return lines;
//    }
//
//    public void setLines(List<BalanceSheetLineEntity> lines) {
//        this.lines = lines;
//    }
//
//    public BigDecimal getTotal(Function<BalanceSheetLineEntity, BigDecimal> coaColumn) {
//        return lines
//                .stream()
//                .map(coaColumn)
//                .reduce(BigDecimal
//                        .ZERO, BigDecimal::add);
//    }


//
//    public BigDecimal getTotal(String account, Function<BalanceSheetLineEntity, BigDecimal> balanceColumn) {
//        return lines.stream().filter(a -> a.getAccountIFRS().getAccount().equalsIgnoreCase(account)).map(balanceColumn).reduce(BigDecimal.ZERO, BigDecimal::add);
//    }


//
}
