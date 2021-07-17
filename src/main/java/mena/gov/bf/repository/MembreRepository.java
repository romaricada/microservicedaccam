package mena.gov.bf.repository;
import mena.gov.bf.domain.Membre;
import mena.gov.bf.domain.MembreCommission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Membre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> { ;
}
