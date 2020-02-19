package ro.fortech.pdfparser.service.extras;

import lombok.Data;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ParsedPdfLineNameDto {

    private String accNr;
    private BigDecimal SolduriInitialeC;


    public List<ParsedPdfLineNameDto> toPojo(List<BalanceSheetLineEntity> balanceSheetLineEntities) {

        List<ParsedPdfLineNameDto> pojo = new ArrayList<>();
        for(BalanceSheetLineEntity sheetLineEntity : balanceSheetLineEntities) {
            ParsedPdfLineNameDto parsedPdfLineDto = new ParsedPdfLineNameDto();

            //parsedPdfLineDto.setBalanceSheetEntity(balanceSheetEntity);
            parsedPdfLineDto.setAccNr(sheetLineEntity.getAccNr());

            parsedPdfLineDto.setSolduriInitialeC(sheetLineEntity.getSolduriInitialeC());

            pojo.add(parsedPdfLineDto);
        }

        return pojo;
    }
}
