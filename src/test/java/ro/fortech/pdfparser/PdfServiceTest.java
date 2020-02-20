package ro.fortech.pdfparser;


import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.refactor.PdfService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class PdfServiceTest {

    @Autowired
    private BalanceSheetRepository balanceSheetRepository;

    @Autowired
    private BalanceSheetLineRepository balanceSheetLineRepository;

    @Autowired
    private PdfService pdfService;

    @Test
    public void whenFindAll_ThenReturnBalanceSheets() throws Exception {
        pdfService.add2Database("/2017 SAS balanta 31122017.pdf");

        List<BalanceSheetEntity> listBalance =  pdfService.getAll();

        System.out.println(listBalance.toString());

        assertThat(listBalance).isEqualTo(pdfService.getAll());

    }


}
