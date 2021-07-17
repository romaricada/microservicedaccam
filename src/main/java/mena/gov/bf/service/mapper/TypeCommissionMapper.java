package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.TypeCommissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeCommission} and its DTO {@link TypeCommissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeCommissionMapper extends EntityMapper<TypeCommissionDTO, TypeCommission> {


    @Mapping(target = "commissions", ignore = true)
    @Mapping(target = "removeCommissions", ignore = true)
    TypeCommission toEntity(TypeCommissionDTO typeCommissionDTO);

    default TypeCommission fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeCommission typeCommission = new TypeCommission();
        typeCommission.setId(id);
        return typeCommission;
    }
}
