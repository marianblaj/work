package ro.fortech.pdfparser;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;
import ro.fortech.pdfparser.service.ParserService;

import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest
public class JpaTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BalanceSheetRepository balanceSheetRepository;

    @Test
    public void contextLoads() throws Exception{
        Mockito.when(balanceSheetRepository.findAll()).thenReturn(
                Collections.emptyList()
        );

        MvcResult result=mockMvc.perform(
                MockMvcRequestBuilders.get("/balance/")
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(result.getResponse());
        Mockito.verify(balanceSheetRepository.findAll());

    }
}
