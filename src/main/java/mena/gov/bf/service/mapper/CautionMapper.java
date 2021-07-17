package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.CautionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Caution} and its DTO {@link CautionDTO}.
 */
@Mapper(componentModel = "spring", uses = {LotMapper.class, TypeCautionMapper.class})
public interface CautionMapper extends EntityMapper<CautionDTO, Caution> {
/**
 * 
 * @param caution
 * @return 
 */
    @Mapping(source = "lot.id", target = "lotId")
    @Mapping(source = "typeCaution.id", target = "typeCautionId")
    @Mapping(source = "typeCaution", target = "typeCaution")
    @Override
    CautionDTO toDto(Caution caution);

    /**
     *
     * @param cautionDTO
     * @return
     */
    @Mapping(source = "lotId", target = "lot")
    @Mapping(source = "typeCautionId", target = "typeCaution")
    @Override
    Caution toEntity(CautionDTO cautionDTO);

    default Caution fromId(Long id) {
        if (id == null) {
            return null;
        }
        Caution caution = new Caution();
        caution.setId(id);
        return caution;
    }
}
