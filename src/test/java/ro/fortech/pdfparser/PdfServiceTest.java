package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfService;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@RunWith(SpringRunner.class)

@SpringBootTest
public class PdfServiceTest {

    @Mock
    PdfService pdfService;

    @Test
    public void whenStringPathValid_thenReturnNewParsedPdfD() throws Exception{
        MockitoAnnotations.initMocks(this);

        //balanceSheetEntity = pdfService.add2Database("String orice");
       // pdfService.add2Database("afsdsfg");



    //assertThat(pdfService.getAll().stream().collect(Collectors.toList())).isInstanceOf(ArrayList.class);


    pdfService.add2Database("/");
    verify(pdfService, times(1)).add2Database("/");

        //  assertThat(pdfService.update(new ParsedPdfDto())).isInstanceOf(BalanceSheetEntity.class);
        // System.out.println(balanceSheetEntity);
        //  assertThat(pdfService.add2Database("String orice")).isInstanceOf(BalanceSheetEntity.class);


    }

}


































