package mena.gov.bf.repository;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.service.dto.LotDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Lot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findAllByDeletedIsFalse();

Optional<Lot> findTop1ByAvisDacIdAndInfructueuxIsTrue(Long activiteId);

List<Lot> findAllByAvisDacIdAndDeletedIsFalse(Long avisDacId);

}
