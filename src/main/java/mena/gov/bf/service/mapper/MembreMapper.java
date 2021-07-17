package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.MembreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Membre} and its DTO {@link MembreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MembreMapper extends EntityMapper<MembreDTO, Membre> {


    @Mapping(target = "commissions", ignore = true)
    @Mapping(target = "removeCommissions", ignore = true)
    Membre toEntity(MembreDTO membreDTO);

    default Membre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Membre membre = new Membre();
        membre.setId(id);
        return membre;
    }
}
