package ro.fortech.pdfparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fortech.pdfparser.entity.BalanceSheetLineEntity;

@Repository("BalanceSheetLineRepository")
public interface BalanceSheetLineRepository extends JpaRepository<BalanceSheetLineEntity, Long> {
}
