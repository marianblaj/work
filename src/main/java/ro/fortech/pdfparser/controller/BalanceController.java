package ro.fortech.pdfparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.PdfService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    PdfService pdfService;

    String insidePath = "/2017 SAS balanta 31122017.pdf";

    @Autowired
    public BalanceController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/add")
    public void add(@RequestBody String path) throws Exception {
        pdfService.add2Database(insidePath);
    }

    @GetMapping("/")
    @ResponseBody
    public  List<ParsedPdfDto> getAll() {
        List<BalanceSheetEntity> balanceSheetEntities = pdfService.getAll();
        return balanceSheetEntities.stream()
                .map(BalanceSheetEntity::toDto)
                .collect(Collectors.toList());

    }

}
