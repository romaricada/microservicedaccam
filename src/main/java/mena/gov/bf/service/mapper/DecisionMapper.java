package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.DecisionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Decision} and its DTO {@link DecisionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReclamationMapper.class})
public interface DecisionMapper extends EntityMapper<DecisionDTO, Decision> {

    @Mapping(source = "reclamation.id", target = "reclamationId")
    DecisionDTO toDto(Decision decision);

    @Mapping(source = "reclamationId", target = "reclamation")
    Decision toEntity(DecisionDTO decisionDTO);

    default Decision fromId(Long id) {
        if (id == null) {
            return null;
        }
        Decision decision = new Decision();
        decision.setId(id);
        return decision;
    }
}
