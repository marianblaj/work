//package ro.fortech.pdfparser.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ro.fortech.pdfparser.entity.BalanceSheetEntity;
//import ro.fortech.pdfparser.service.ParserService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/balance")
//public class BalanceSheetController {
//
//    private ParserService parserService;
//
//    @Autowired
//    public BalanceSheetController(ParserService parserService) {
//        this.parserService = parserService;
//    }
//
//    @GetMapping("/")
//    public List<BalanceSheetEntity> getBalanceSheet()
//    {
//        return parserService.getBalanceSheet();
//    }
//
//}
