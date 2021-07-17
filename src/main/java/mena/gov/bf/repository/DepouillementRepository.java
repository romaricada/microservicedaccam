package mena.gov.bf.repository;
import mena.gov.bf.domain.Depouillement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Depouillement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepouillementRepository extends JpaRepository<Depouillement, Long> {
    Optional<Depouillement> findTopByAvisDacId(Long id);
}
