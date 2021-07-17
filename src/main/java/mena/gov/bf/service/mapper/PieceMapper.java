package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.PieceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Piece} and its DTO {@link PieceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PieceMapper extends EntityMapper<PieceDTO, Piece> {


    @Mapping(target = "pieceCandidats", ignore = true)
    @Mapping(target = "removePieceCandidats", ignore = true)
    Piece toEntity(PieceDTO pieceDTO);

    default Piece fromId(Long id) {
        if (id == null) {
            return null;
        }
        Piece piece = new Piece();
        piece.setId(id);
        return piece;
    }
}
