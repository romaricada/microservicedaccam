package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.CandidatCautionLotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CandidatCautionLot} and its DTO {@link CandidatCautionLotDTO}.
 */
@Mapper(componentModel = "spring", uses = {CandidatLotMapper.class, CautionMapper.class, InstitutionFinanciereMapper.class})
public interface CandidatCautionLotMapper extends EntityMapper<CandidatCautionLotDTO, CandidatCautionLot> {

    @Mapping(source = "candidatLot.id", target = "candidatLotId")
    @Mapping(source = "caution.id", target = "cautionId")
    @Mapping(source = "caution", target = "caution")
    CandidatCautionLotDTO toDto(CandidatCautionLot candidatCautionLot);

    @Mapping(source = "candidatLotId", target = "candidatLot")
    @Mapping(source = "cautionId", target = "caution")
    CandidatCautionLot toEntity(CandidatCautionLotDTO candidatCautionLotDTO);

    default CandidatCautionLot fromId(Long id) {
        if (id == null) {
            return null;
        }
        CandidatCautionLot candidatCautionLot = new CandidatCautionLot();
        candidatCautionLot.setId(id);
        return candidatCautionLot;
    }
}
