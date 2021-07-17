package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.DelaiMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DelaiMessage} and its DTO {@link DelaiMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DelaiMessageMapper extends EntityMapper<DelaiMessageDTO, DelaiMessage> {



    default DelaiMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        DelaiMessage delaiMessage = new DelaiMessage();
        delaiMessage.setId(id);
        return delaiMessage;
    }
}
