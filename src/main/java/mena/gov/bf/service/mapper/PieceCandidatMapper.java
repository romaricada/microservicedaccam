package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.PieceCandidatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PieceCandidat} and its DTO {@link PieceCandidatDTO}.
 */
@Mapper(componentModel = "spring", uses = {CandidatLotMapper.class, PieceMapper.class})
public interface PieceCandidatMapper extends EntityMapper<PieceCandidatDTO, PieceCandidat> {

    @Mapping(source = "candidatLot.id", target = "candidatLotId")
    @Mapping(source = "piece.id", target = "pieceId")
    @Mapping(source = "piece", target = "piece")
    PieceCandidatDTO toDto(PieceCandidat pieceCandidat);

    @Mapping(source = "candidatLotId", target = "candidatLot")
    @Mapping(source = "pieceId", target = "piece")
    PieceCandidat toEntity(PieceCandidatDTO pieceCandidatDTO);

    default PieceCandidat fromId(Long id) {
        if (id == null) {
            return null;
        }
        PieceCandidat pieceCandidat = new PieceCandidat();
        pieceCandidat.setId(id);
        return pieceCandidat;
    }
}
