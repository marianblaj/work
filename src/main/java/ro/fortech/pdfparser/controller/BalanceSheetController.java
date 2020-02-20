package ro.fortech.pdfparser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.PdfService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sheet")
public class BalanceSheetController {

    private PdfService pdfService;

    @Autowired
    public BalanceSheetController(PdfService pdfService){this.pdfService = pdfService;}

    @GetMapping("/")
    public List<ParsedPdfDto> getBalanceSheet()
    {
        return pdfService.getAll().stream().map(BalanceSheetEntity::toDto).collect(Collectors.toList());
    }


}
