package ro.fortech.pdfparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;


public interface BalanceSheetRepository extends JpaRepository<BalanceSheetEntity, Long> {


}
