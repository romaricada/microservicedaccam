package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ReclamationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reclamation} and its DTO {@link ReclamationDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvisDacMapper.class})
public interface ReclamationMapper extends EntityMapper<ReclamationDTO, Reclamation> {

    @Mapping(source = "avisDac.id", target = "avisDacId")
    ReclamationDTO toDto(Reclamation reclamation);

    @Mapping(source = "avisDacId", target = "avisDac")
    Reclamation toEntity(ReclamationDTO reclamationDTO);



    default Reclamation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reclamation reclamation = new Reclamation();
        reclamation.setId(id);
        return reclamation;
    }
}
