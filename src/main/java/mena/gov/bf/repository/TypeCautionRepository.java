package mena.gov.bf.repository;
import mena.gov.bf.domain.TypeCaution;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeCaution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeCautionRepository extends JpaRepository<TypeCaution, Long> {

}
