package ro.fortech.pdfparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.refactor.PdfService;
import ro.fortech.pdfparser.service.ParsedPdfDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/balance")
public class BalanceSheetController {

    private PdfService pdfService;

    @Autowired
    public BalanceSheetController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

//    String path = "/2017 SAS balanta 31122017.pdf";
    @GetMapping("/")
    public List<ParsedPdfDto> getBalanceSheet()
    {
        List<BalanceSheetEntity> balanceSheet = pdfService.getAll();
        return balanceSheet.stream().map(BalanceSheetEntity::sheetToDto).collect(Collectors.toList());
    }

    @PostMapping("/{path}")
    public void addSheet(@PathVariable String path) throws Exception {
        pdfService.add2Database(path);
    }

}
