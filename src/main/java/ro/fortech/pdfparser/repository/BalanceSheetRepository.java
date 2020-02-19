package ro.fortech.pdfparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.service.ParsedPdfDto;

@Repository("BalanceSheetRepository")
public interface BalanceSheetRepository extends JpaRepository<BalanceSheetEntity, Long> {
    String findByCf(String cif);

}
