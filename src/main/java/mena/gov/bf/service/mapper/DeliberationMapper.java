package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.DeliberationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Deliberation} and its DTO {@link DeliberationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeliberationMapper extends EntityMapper<DeliberationDTO, Deliberation> {


    @Mapping(target = "candidatLots", ignore = true)
    @Mapping(target = "removeCandidatLots", ignore = true)
    Deliberation toEntity(DeliberationDTO deliberationDTO);

    default Deliberation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Deliberation deliberation = new Deliberation();
        deliberation.setId(id);
        return deliberation;
    }
}
