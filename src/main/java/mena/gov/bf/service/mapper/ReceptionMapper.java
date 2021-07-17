package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reception} and its DTO {@link ReceptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvisDacMapper.class})
public interface ReceptionMapper extends EntityMapper<ReceptionDTO, Reception> {


    @Mapping(source = "avisDac.id", target = "avisDacId")
    @Mapping(source = "avisDac", target = "avisDac")
    @Mapping(source = "avisDacId", target = "avisDac")


    /* @Mapping(source = "lot.id", target = "lotId")
    @Mapping(source = "lot", target = "lot")
    @Mapping(source = "lotId", target = "lot") b*/


    default Reception fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reception reception = new Reception();
        reception.setId(id);
        return reception;
    }
}
