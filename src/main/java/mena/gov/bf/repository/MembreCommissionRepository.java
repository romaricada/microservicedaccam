package mena.gov.bf.repository;

import mena.gov.bf.domain.MembreCommission;
import mena.gov.bf.domain.Tache;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * Spring Data  repository for the MembreCommission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MembreCommissionRepository extends JpaRepository<MembreCommission, Long> {
        List<MembreCommission> findMembreCommissionByDeletedIsFalse();
        Set<MembreCommission> findAllByDeletedIsFalseAndTacheId(Long id);
       // List<MembreCommission> findAllByDeletedIsFalseAndAvisDacIdAndTypeCommissionId(Long activiteId, Long typCommissId);
        List<MembreCommission> findAllByDeletedIsFalseAndAvisDacIdAndTypeCommissionIdAndTache(Long avisDacId, Long typCommissId, Tache tache);
        List<MembreCommission> findAllByTypeCommissionIdAndDeletedIsFalse(Long typeId);
        List<MembreCommission> findAllByTacheIdAndTacheIsNotNullAndDeletedIsFalse(Long tacheId);
        List<MembreCommission> findAllByDeletedIsFalseAndAvisDacIdAndTypeCommissionId(Long avisDacId, Long typeComissionId);

        List<MembreCommission> findMembreCommissionByMembreIdAndDeletedIsFalse(Long membreId);

}
