package ro.fortech.pdfparser.refactor;

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
        BalanceSheetEntity updatedDto = new BalanceSheetEntity();
        updatedDto = updatedDto.toBalanceSheetEntity(importPdf.importPdf(path));
        System.out.println(updatedDto);
        balanceSheetRepository.save(updatedDto);

        for (int i=0;i<updatedDto.getLines().size();i++){
            balanceSheetLineRepository.save(updatedDto.getLines().get(i));
        }
    }

    public List<BalanceSheetEntity> getAll() {
        return balanceSheetRepository.findAll();
    }

}
