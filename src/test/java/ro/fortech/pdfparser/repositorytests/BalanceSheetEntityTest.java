package ro.fortech.pdfparser.repositorytests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BalanceSheetEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BalanceSheetRepository sheetRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate from = LocalDate.parse("12.03.2012",formatter);
    private LocalDate to = LocalDate.parse("14.03.2012",formatter);

    @Test
    public void whenFindByName()
    {
        BalanceSheetEntity sheet = new BalanceSheetEntity("Fortech","ro1234",from,to);
        testEntityManager.persist(sheet);
        testEntityManager.flush();

        BalanceSheetEntity found = sheetRepository.findByNumeFirma(sheet.getNumeFirma());
        assertThat(found.getNumeFirma())
                .isEqualTo(sheet.getNumeFirma());
    }

    @Test
    public void myTest() throws Exception {
        BalanceSheetEntity sheet = new BalanceSheetEntity("Fortech","ro1234",from,to);
        sheetRepository.save(sheet);

        BalanceSheetEntity sheet2 = sheetRepository.findByNumeFirma("Fortech");
        assertThat(sheet.getNumeFirma()).isEqualTo(sheet2.getNumeFirma());
    }

}