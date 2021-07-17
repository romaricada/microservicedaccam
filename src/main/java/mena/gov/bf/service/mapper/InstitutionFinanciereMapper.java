package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.InstitutionFinanciereDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InstitutionFinanciere} and its DTO {@link InstitutionFinanciereDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstitutionFinanciereMapper extends EntityMapper<InstitutionFinanciereDTO, InstitutionFinanciere> {



    default InstitutionFinanciere fromId(Long id) {
        if (id == null) {
            return null;
        }
        InstitutionFinanciere institutionFinanciere = new InstitutionFinanciere();
        institutionFinanciere.setId(id);
        return institutionFinanciere;
    }
}
