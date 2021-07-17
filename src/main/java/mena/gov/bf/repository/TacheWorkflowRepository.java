package mena.gov.bf.repository;
import mena.gov.bf.domain.TacheWorkflow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
 * Spring Data  repository for the TacheWorkflow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TacheWorkflowRepository extends JpaRepository<TacheWorkflow, Long> {
    Set<TacheWorkflow> findAllByDeletedIsFalseAndTacheId(Long id);
}
