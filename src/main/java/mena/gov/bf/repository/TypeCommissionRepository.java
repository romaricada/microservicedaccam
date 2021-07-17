package mena.gov.bf.repository;
import mena.gov.bf.domain.TypeCommission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TypeCommission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeCommissionRepository extends JpaRepository<TypeCommission, Long> {

    List<TypeCommission> findAllByDeletedIsFalse();

}
