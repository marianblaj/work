package ro.fortech.pdfparser.controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;
import ro.fortech.pdfparser.service.refactor.ImportPdf;
import ro.fortech.pdfparser.service.refactor.PdfService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@RunWith(SpringRunner.class)
//@DataJpaTest
//@SpringBootTest
@WebMvcTest
public class BalanceControllerTest {

    @Autowired
    MockMvc mockMvc;



    @MockBean
    BalanceController balanceController;



     @Test
    public void whenStringPathValid_addToDataBase() throws Exception {
          BalanceController balanceControllerMock = Mockito.mock(BalanceController.class);
         when(balanceControllerMock.add("/2017 SAS balanta 31122017.pdf")).thenReturn(new BalanceSheetEntity());
         BalanceSheetEntity added = balanceController.add("/2017 SAS balanta 31122017.pdf");
         assertThat(added).isEqualTo(balanceController.add("/2017 SAS balanta 31122017.pdf"));

//         when(balanceController.add("/2017 SAS balanta 31122017.pdf"))
//                 .thenReturn(new BalanceSheetEntity());
//          MvcResult mvcResult = mockMvc.perform(
//                 MockMvcRequestBuilders.get("/")
//                 .accept(MediaType.APPLICATION_JSON))
//                 .andReturn();
//         System.out.println(mvcResult.getResponse());
//         verify(balanceController).add("/2017 SAS balanta 31122017.pdf");


     }
//    @PostMapping("/")
//    public void add(@RequestBody String path) throws Exception {
//        pdfService.add2Database(insidePath);
//    }






    /*@Mock
    PdfService pdfService;

    @Test
    public void whenStringPathValid_thenAddToDatabse() throws Exception{
        MockitoAnnotations.initMocks(this);
        pdfService.add2Database("/");
        verify(pdfService, times(1)).add2Database("/");
    }

    @Test
    public void retunAllFromDataBase() throws Exception{
        MockitoAnnotations.initMocks(this);
        pdfService.getAll();
        verify(pdfService, times(1)).getAll();
    }*/
}
