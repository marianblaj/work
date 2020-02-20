package ro.fortech.pdfparser.service.refactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParsedPdfLineDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfService {

    @Autowired
    private ImportPdf importPdf;

    @Autowired
    private BalanceSheetRepository balanceSheetRepository;

    @Autowired
    private BalanceSheetLineRepository balanceSheetLineRepository;

        public void add2Database(String path) throws Exception {
            BalanceSheetEntity updatedDto;
            updatedDto = update(importPdf.importPdf(path));
            System.out.println(updatedDto);
            balanceSheetRepository.save(updatedDto);

            for (int i=0;i<updatedDto.getLines().size();i++){
                balanceSheetLineRepository.save(updatedDto.getLines().get(i));
            }
        }

        public List<BalanceSheetEntity> getAll() {
            return balanceSheetRepository.findAll();
        }

        public BalanceSheetEntity update(ParsedPdfDto pojo) {
            BalanceSheetEntity bal = new BalanceSheetEntity();
            bal.setNumeFirma(pojo.getNumeFirma());
            bal.setCf(pojo.getCf());
            bal.setFrom(pojo.getFrom());
            bal.setTo(pojo.getTo());
            List<BalanceSheetLineEntity> balanceSheetLineEntities = pojo.getLines()
                    .stream()
                    .map(ParsedPdfLineDto::update)
                    .collect(Collectors.toList());
            balanceSheetLineEntities.forEach(balanceSheetLineEntity -> balanceSheetLineEntity.setBalanceSheet(bal));
            bal.setLines(balanceSheetLineEntities);
            return bal;
        }
    }


