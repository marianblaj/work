package ro.fortech.pdfparser;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.ParserPdfService;
import ro.fortech.pdfparser.service.ParserService;

@RunWith(SpringRunner.class)
@DataJpaTest
class ParserServiceTest {

    @Autowired
    private BalanceSheetRepository balanceSheetRepository;

    @Autowired
    private BalanceSheetLineRepository balanceSheetLineRepository;

    String path = "/2017 SAS balanta 31122017.pdf";

    @Test
    void importPdf() throws Exception {
//        new ParserPdfService().importPdf();
        ParsedPdfDto dto =  new ParserService(balanceSheetRepository,balanceSheetLineRepository).importPdf(path);

        System.out.println(dto.toString());
    }

}