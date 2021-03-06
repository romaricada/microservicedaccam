package mena.gov.bf.repository;
import mena.gov.bf.domain.Reclamation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reclamation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

}
