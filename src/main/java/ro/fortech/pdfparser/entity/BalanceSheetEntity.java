package ro.fortech.pdfparser.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ifrs_balance_sheets")
public class BalanceSheetEntity extends  BaseEntity{

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
}
