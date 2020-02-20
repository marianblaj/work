package ro.fortech.pdfparser.service.refactor;

import org.springframework.stereotype.Service;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PdfService {

    private BalanceSheetRepository balanceSheetRepository;
    private BalanceSheetLineRepository balanceSheetLineEntity;

    public PdfService(BalanceSheetRepository balanceSheetRepository, BalanceSheetLineRepository balanceSheetLineEntity) {
        this.balanceSheetRepository = balanceSheetRepository;
        this.balanceSheetLineEntity = balanceSheetLineEntity;
    }

    public List<BalanceSheetEntity> getAll(){
        return balanceSheetRepository.findAll();

    }
}
