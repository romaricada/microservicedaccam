package mena.gov.bf.repository;
import mena.gov.bf.domain.PieceCandidat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PieceCandidat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PieceCandidatRepository extends JpaRepository<PieceCandidat, Long> {

}
