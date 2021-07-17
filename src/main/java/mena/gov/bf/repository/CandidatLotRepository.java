package mena.gov.bf.repository;
import mena.gov.bf.domain.CandidatLot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CandidatLot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatLotRepository extends JpaRepository<CandidatLot, Long> {
List<CandidatLot> findCandidatLotsByDepouillementIdAndDepouillementIsNotNullAndDeletedIsFalse(Long idDepouillement);
List<CandidatLot> findCandidatLotsByDepouillementIdAndDepouillementIsNullAndDeletedIsFalse(Long idDepouillement);
List<CandidatLot> findCandidatLotsByLotIdAndDepouillementIsNotNullAndDeletedIsFalseAndSoumissionnaireIsTrue(Long lotId);
    CandidatLot findCandidatLotsByLotIdAndAttributaireIsTrue(Long lotId);
List<CandidatLot> findAllByLotIdAndDeletedIsFalseAndAttributaireIsNotNullAndAttributaireIsTrue(Long loId);
CandidatLot findAllByLotIdAndDeletedIsFalse(Long loId);
List<CandidatLot> findCandidatLotsByCandidatIdAndAttributaireIsTrueAndDeletedIsFalse(Long candidatId);
List<CandidatLot> findAllByCandidatIdAndDeletedIsFalse(Long idCandidat);
List<CandidatLot> findAllByDeletedIsFalse();

}
