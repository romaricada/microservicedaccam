package mena.gov.bf.repository;
import mena.gov.bf.domain.ReclamationCandidatLot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReclamationCandidatLot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReclamationCandidatLotRepository extends JpaRepository<ReclamationCandidatLot, Long> {

}
