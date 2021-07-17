package mena.gov.bf.repository;
import mena.gov.bf.domain.CandidatCautionLot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the CandidatCautionLot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatCautionLotRepository extends JpaRepository<CandidatCautionLot, Long> {
Optional<CandidatCautionLot> findAllByDepoullementId(Long id);
Optional<CandidatCautionLot> findAllByDepoullementIdAndCandidatLotId(Long idDepouillement, Long idCandidatLot);
}
