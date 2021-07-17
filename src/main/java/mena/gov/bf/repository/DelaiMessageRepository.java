package mena.gov.bf.repository;
import mena.gov.bf.domain.DelaiMessage;
import mena.gov.bf.domain.enumeration.TypeMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the DelaiMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DelaiMessageRepository extends JpaRepository<DelaiMessage, Long> {
    Optional<DelaiMessage> findTop1ByTypeMessageAndTempsAlerteIsNotNull(TypeMessage typeMessage);
    Optional<DelaiMessage> findTop1ByTypeMessageAndTempsAlerteIsNull(TypeMessage typeMessage);

    List<DelaiMessage> findDelaiMessageByDeletedIsFalse();
}
