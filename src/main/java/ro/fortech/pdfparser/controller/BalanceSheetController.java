//package ro.fortech.pdfparser.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import ro.fortech.pdfparser.entity.BalanceSheetEntity;
//import ro.fortech.pdfparser.service.ParsedPdfDto;
//import ro.fortech.pdfparser.service.refactor.PdfParser;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/pdf")
//public class BalanceSheetController {
//
//
//    private PdfParser pdfParser;
//
//    @Autowired
//    public BalanceSheetController(PdfParser pdfParser) {
//        this.pdfParser = pdfParser;
//    }
//
//    @PostMapping("/")
//    public void addPdf(){
//        pdfParser.addData();
//}
//
//    @GetMapping("/")
//    public List<ParsedPdfDto> getAllData(){
//
//        return pdfParser.getAllPdf().stream().collect(Collectors.toList());
//    }
//
//}
