package ro.fortech.pdfparser.refactortests;


import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.refactor.PdfService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class PdfServiceTest {
    @Autowired
    private PdfService pdfService;

    @Test
    public void whenFindCf_ThenReturnBalanceSheets() throws Exception {
        pdfService.add2Database("/2017 SAS balanta 31122017.pdf");

        String cf = "RO2577839   ";
        List<BalanceSheetEntity> listBalance =  pdfService.getAll();


        assertThat(listBalance.get(0).getCf()).isEqualTo(cf);

    }


}
