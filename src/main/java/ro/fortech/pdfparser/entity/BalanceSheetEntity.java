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
@Table(name = "balance_sheets")
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

    @OneToMany(mappedBy = "balanceSheet", fetch = FetchType.EAGER)
    private List<BalanceSheetLineEntity> lines = new ArrayList<>();


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

    public static ParsedPdfDto toDto(BalanceSheetEntity balanceSheetEntity) {
        ParsedPdfDto parsedPdfDto = new ParsedPdfDto();
        parsedPdfDto.setNumeFirma(balanceSheetEntity.numeFirma);
        parsedPdfDto.setCf(balanceSheetEntity.cf);
        parsedPdfDto.setFrom(balanceSheetEntity.from);
        parsedPdfDto.setTo(balanceSheetEntity.to);
        ParsedPdfLineDto parsedPdfLineDto = new ParsedPdfLineDto();
        parsedPdfDto.setLines(parsedPdfLineDto.toDto(balanceSheetEntity.getLines()));


        return parsedPdfDto;
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
