package ro.fortech.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BalanceSheetEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BalanceSheetRepository baseSheetRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate from = LocalDate.parse("12.03.2012",formatter);
    private LocalDate to = LocalDate.parse("14.03.2012",formatter);

    private BalanceSheetEntity balanceSheetEntity = new BalanceSheetEntity("Fortech","ro1234",from,to);

    @Test
    public void whenFindByName()
    {
        entityManager.persist(balanceSheetEntity);
        entityManager.flush();

        BalanceSheetEntity found = baseSheetRepository.findByNumeFirma(balanceSheetEntity.getNumeFirma());

        assertThat(found.getNumeFirma())
                .isEqualTo(balanceSheetEntity.getNumeFirma());
    }

    @Test
    public void whenSave()
    {
        baseSheetRepository.save(balanceSheetEntity);
    }
}
