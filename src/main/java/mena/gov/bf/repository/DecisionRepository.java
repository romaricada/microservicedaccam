package mena.gov.bf.repository;
import mena.gov.bf.domain.Decision;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Decision entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {
    /**
     *
     * @return {@link D}
     */
    List<Decision> findAllByDeletedIsFalse();

    List<Decision> findAllByReclamationIdAndReclamationIsNotNullAndDeletedIsFalse(Long idReclamation);
}
