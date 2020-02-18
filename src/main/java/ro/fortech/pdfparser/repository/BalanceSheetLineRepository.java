package ro.fortech.pdfparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;

public interface BalanceSheetLineRepository extends JpaRepository<BalanceSheetLineEntity, Long> {
}
