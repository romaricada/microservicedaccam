package mena.gov.bf.repository;
import mena.gov.bf.domain.Workflow;
import mena.gov.bf.domain.enumeration.Etat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Workflow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
Workflow findAllByDeletedIsFalseAndEtat(Etat etat);
}
