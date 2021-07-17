package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.TacheEtapeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TacheEtape} and its DTO {@link TacheEtapeDTO}.
 */
@Mapper(componentModel = "spring", uses = {TacheMapper.class})
public interface TacheEtapeMapper extends EntityMapper<TacheEtapeDTO, TacheEtape> {

    @Mapping(source = "tache.id", target = "tacheId")
    TacheEtapeDTO toDto(TacheEtape tacheEtape);

    @Mapping(source = "tacheId", target = "tache")
    TacheEtape toEntity(TacheEtapeDTO tacheEtapeDTO);

    default TacheEtape fromId(Long id) {
        if (id == null) {
            return null;
        }
        TacheEtape tacheEtape = new TacheEtape();
        tacheEtape.setId(id);
        return tacheEtape;
    }
}
