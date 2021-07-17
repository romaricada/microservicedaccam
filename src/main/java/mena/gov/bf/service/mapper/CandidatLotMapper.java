package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.CandidatLotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CandidatLot} and its DTO {@link CandidatLotDTO}.
 */
@Mapper(componentModel = "spring", uses = {LotMapper.class, CandidatMapper.class, DeliberationMapper.class, DepouillementMapper.class, ReclamationMapper.class})
public interface CandidatLotMapper extends EntityMapper<CandidatLotDTO, CandidatLot> {

    @Mapping(source = "lot.id", target = "lotId")
    @Mapping(source = "candidat.id", target = "candidatId")
    @Mapping(source = "candidat", target = "candidat")
    @Mapping(source = "deliberation.id", target = "deliberationId")
    @Mapping(source = "depouillement.id", target = "depouillementId")
    CandidatLotDTO toDto(CandidatLot candidatLot);

    @Mapping(target = "pieceCandidats", ignore = true)
    @Mapping(target = "removePieceCandidats", ignore = true)
    @Mapping(source = "lotId", target = "lot")
    @Mapping(source = "candidatId", target = "candidat")
    @Mapping(source = "deliberationId", target = "deliberation")
    @Mapping(source = "depouillementId", target = "depouillement")
    CandidatLot toEntity(CandidatLotDTO candidatLotDTO);

    default CandidatLot fromId(Long id) {
        if (id == null) {
            return null;
        }
        CandidatLot candidatLot = new CandidatLot();
        candidatLot.setId(id);
        return candidatLot;
    }
}
