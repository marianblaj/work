package ro.fortech.pdfparser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.extras.ParsedPdfNameDto;

import ro.fortech.pdfparser.service.refactor.PdfService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BalanceSheetEntityController {

    @Autowired
    private PdfService pdfService;

    //String path = "/2017 SAS balanta 31122017.pdf";
    @PostMapping("/{path}")
    public void add(@PathVariable String path) {
        try {
            pdfService.add2Database(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    @ResponseBody
    public List<ParsedPdfDto> getAll() {
        List<BalanceSheetEntity> balanceSheetEntities = pdfService.getAll();
        return balanceSheetEntities.stream().map(BalanceSheetEntity :: toPojo).collect(Collectors.toList());
    }

    @GetMapping("/1")
    @ResponseBody
    public List<ParsedPdfNameDto> getAllByName() {
        List<BalanceSheetEntity> balanceSheetEntities = pdfService.getAll();
        return balanceSheetEntities.stream().map(BalanceSheetEntity :: toNamePojo).collect(Collectors.toList());
    }


}

