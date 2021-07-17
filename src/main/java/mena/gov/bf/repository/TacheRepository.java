package mena.gov.bf.repository;
import mena.gov.bf.domain.Tache;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Tache entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
 List<Tache> findAllByDeletedIsFalseAndAvisDacId(Long id);
 List<Tache> findAllByDeletedIsFalse();
 List<Tache> findAllByAvisDacIdAndAvisDacIdIsNotNullAndDeletedIsFalse(Long idAvisDac);
}
