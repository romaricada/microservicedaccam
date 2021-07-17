package mena.gov.bf.repository;
import mena.gov.bf.domain.TacheEtape;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * Spring Data  repository for the TacheEtape entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TacheEtapeRepository extends JpaRepository<TacheEtape, Long> {
    List<TacheEtape> findAllByDeletedIsFalse();
    Set<TacheEtape> findAllByDeletedIsFalseAndTacheId(Long id);
}
