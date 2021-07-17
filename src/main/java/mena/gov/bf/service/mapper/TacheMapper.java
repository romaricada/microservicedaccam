package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.TacheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tache} and its DTO {@link TacheDTO}.
 */
@Mapper(componentModel = "spring", uses = {LotMapper.class})
public interface TacheMapper extends EntityMapper<TacheDTO, Tache> {

    @Mapping(source = "lot.id", target = "lotId")
    @Mapping(source = "lot", target = "lot")
    TacheDTO toDto(Tache tache);

    @Mapping(target = "tacheWorkflows", ignore = true)
    @Mapping(target = "removeTacheWorkflows", ignore = true)
    @Mapping(target = "tacheEtapes", ignore = true)
    @Mapping(target = "removeTacheEtapes", ignore = true)
    @Mapping(target = "membreCommissions", ignore = true)
    @Mapping(target = "removeMembreCommissions", ignore = true)
    @Mapping(source = "lotId", target = "lot")
    Tache toEntity(TacheDTO tacheDTO);

    default Tache fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tache tache = new Tache();
        tache.setId(id);
        return tache;
    }
}
