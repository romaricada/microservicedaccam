package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.MembreCommissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MembreCommission} and its DTO {@link MembreCommissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MembreMapper.class, TypeCommissionMapper.class, TacheMapper.class})
public interface MembreCommissionMapper extends EntityMapper<MembreCommissionDTO, MembreCommission> {

    @Mapping(source = "membre.id", target = "membreId")
    @Mapping(source = "membre", target = "membre")
    @Mapping(source = "typeCommission.id", target = "typeCommissionId")
    @Mapping(source = "typeCommission", target = "typeCommission")
    @Mapping(source = "tache.id", target = "tacheId")
    MembreCommissionDTO toDto(MembreCommission membreCommission);

    @Mapping(source = "membreId", target = "membre")
    @Mapping(source = "typeCommissionId", target = "typeCommission")
    @Mapping(source = "tacheId", target = "tache")
    MembreCommission toEntity(MembreCommissionDTO membreCommissionDTO);

    default MembreCommission fromId(Long id) {
        if (id == null) {
            return null;
        }
        MembreCommission membreCommission = new MembreCommission();
        membreCommission.setId(id);
        return membreCommission;
    }
}
