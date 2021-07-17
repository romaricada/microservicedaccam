package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.CandidatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Candidat} and its DTO {@link CandidatDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CandidatMapper extends EntityMapper<CandidatDTO, Candidat> {


    @Mapping(target = "candidatLots", ignore = true)
    @Mapping(target = "removeCandidatLots", ignore = true)
    Candidat toEntity(CandidatDTO candidatDTO);

    CandidatDTO toDto(Candidat candidat);
    default Candidat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Candidat candidat = new Candidat();
        candidat.setId(id);
        return candidat;
    }
}
