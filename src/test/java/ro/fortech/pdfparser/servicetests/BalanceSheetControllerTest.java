package ro.fortech.pdfparser.servicetests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.fortech.pdfparser.PdfParserApplication;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.refactor.PdfService;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PdfParserApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class BalanceSheetControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PdfService pdfService;

    @Test
    public void givenBalanceSheet_whenGetAll_thenReturn() throws Exception {

        BalanceSheetEntity bal = new BalanceSheetEntity();
        List<BalanceSheetEntity> allBal = Arrays.asList(bal);
        //String numeExcepected = "SOFT APLICATIV SI SERVICII S.A.";
        given(pdfService.getAll()).willReturn(allBal);

        mvc.perform(get("/api/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numeFirma", is(bal.getNumeFirma())));
    }

}
