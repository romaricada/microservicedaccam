package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.TypeCautionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeCaution} and its DTO {@link TypeCautionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeCautionMapper extends EntityMapper<TypeCautionDTO, TypeCaution> {



    default TypeCaution fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeCaution typeCaution = new TypeCaution();
        typeCaution.setId(id);
        return typeCaution;
    }
}
