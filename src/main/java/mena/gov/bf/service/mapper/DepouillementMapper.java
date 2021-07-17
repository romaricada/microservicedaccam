package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.DepouillementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Depouillement} and its DTO {@link DepouillementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepouillementMapper extends EntityMapper<DepouillementDTO, Depouillement> {


    @Mapping(target = "candidatLots", ignore = true)
    @Mapping(target = "removeCandidatLots", ignore = true)
    Depouillement toEntity(DepouillementDTO depouillementDTO);

    default Depouillement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Depouillement depouillement = new Depouillement();
        depouillement.setId(id);
        return depouillement;
    }
}
