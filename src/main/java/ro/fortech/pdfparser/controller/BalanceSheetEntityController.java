package ro.fortech.pdfparser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParserService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BalanceSheetEntityController {

    @Autowired
    private ParserService parserService;

    @PostMapping("/")

    @GetMapping("/")
    @ResponseBody
    public List<ParsedPdfDto> getAll() {
        List<BalanceSheetEntity> balanceSheetEntities = parserService.getBalance();
        return balanceSheetEntities.stream().map(BalanceSheetEntity :: toPojo).collect(Collectors.toList());
    }

}

