package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.LotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lot} and its DTO {@link LotDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvisDacMapper.class})
public interface LotMapper extends EntityMapper<LotDTO, Lot> {

    /**
     *
     * @param lot
     * @return
     */
    @Mapping(source = "avisDac.id", target = "avisDacId")
    @Override
    LotDTO toDto(Lot lot);

    /**
     *
     * @param lotDTO
     * @return
     */
    @Mapping(target = "candidatLots", ignore = true)
    @Mapping(target = "removeCandidatLots", ignore = true)
    @Mapping(target = "taches", ignore = true)
    @Mapping(source = "avisDacId", target = "avisDac")
    @Override
    Lot toEntity(LotDTO lotDTO);

    default Lot fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lot lot = new Lot();
        lot.setId(id);
        return lot;
    }
}
