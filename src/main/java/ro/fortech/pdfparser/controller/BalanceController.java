package ro.fortech.pdfparser.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;
import ro.fortech.pdfparser.entity.exceptions.FileAlreadyParsedException;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    ParserService service;

    public BalanceController(ParserService service) {
        this.service=service;
    }

    @PostMapping("/add/{path}")
    public void add(@PathVariable String path) throws Exception {
        service.add(path);
    }



    @GetMapping("/")
    @ResponseBody
    public List<ParsedPdfDto> getAll() {
        List<BalanceSheetEntity> balanceSheetEntities = service.getAll();
        return (List<ParsedPdfDto>) balanceSheetEntities.stream()
                .map(BalanceSheetEntity::toDto)
                .collect(Collectors.toList());
    }

}
