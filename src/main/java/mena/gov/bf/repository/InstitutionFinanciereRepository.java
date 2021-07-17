package mena.gov.bf.repository;

import mena.gov.bf.domain.InstitutionFinanciere;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InstitutionFinanciere entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstitutionFinanciereRepository extends JpaRepository<InstitutionFinanciere, Long> {
}
