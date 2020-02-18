package ro.fortech.pdfparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;

@Repository
public interface BalanceSheetRepository extends JpaRepository<BalanceSheetEntity, Long> {

    public BalanceSheetEntity findByNumeFirma(String numeFirma);
}
