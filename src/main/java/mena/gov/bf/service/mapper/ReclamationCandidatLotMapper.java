package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ReclamationCandidatLotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReclamationCandidatLot} and its DTO {@link ReclamationCandidatLotDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReclamationMapper.class, CandidatLotMapper.class, DecisionMapper.class})
public interface ReclamationCandidatLotMapper extends EntityMapper<ReclamationCandidatLotDTO, ReclamationCandidatLot> {

    @Mapping(source = "reclamation.id", target = "reclamationId")
    @Mapping(source = "decision.id", target = "decisionId")
    @Mapping(source = "candidatLot.id", target = "candidatLotId")
    @Mapping(source = "candidatLot", target = "candidatLot")
    ReclamationCandidatLotDTO toDto(ReclamationCandidatLot reclamationCandidatLot);

    @Mapping(source = "reclamationId", target = "reclamation")
    @Mapping(source = "candidatLotId", target = "candidatLot")
    @Mapping(source = "decisionId", target = "decision")
    ReclamationCandidatLot toEntity(ReclamationCandidatLotDTO reclamationCandidatLotDTO);

    default ReclamationCandidatLot fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReclamationCandidatLot reclamationCandidatLot = new ReclamationCandidatLot();
        reclamationCandidatLot.setId(id);
        return reclamationCandidatLot;
    }
}
