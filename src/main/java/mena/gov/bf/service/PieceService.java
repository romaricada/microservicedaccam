package mena.gov.bf.service;

import mena.gov.bf.domain.Piece;
import mena.gov.bf.repository.PieceRepository;
import mena.gov.bf.service.dto.PieceDTO;
import mena.gov.bf.service.mapper.PieceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Piece}.
 */
@Service
@Transactional
public class PieceService {

    private final Logger log = LoggerFactory.getLogger(PieceService.class);

    private final PieceRepository pieceRepository;

    private final PieceMapper pieceMapper;

    public PieceService(PieceRepository pieceRepository, PieceMapper pieceMapper) {
        this.pieceRepository = pieceRepository;
        this.pieceMapper = pieceMapper;
    }

    /**
     * Save a piece.
     *
     * @param pieceDTO the entity to save.
     * @return the persisted entity.
     */
    public PieceDTO save(PieceDTO pieceDTO) {
        log.debug("Request to save Piece : {}", pieceDTO);
        Piece piece = pieceMapper.toEntity(pieceDTO);
        piece = pieceRepository.save(piece);
        return pieceMapper.toDto(piece);
    }

    /**
     * Get all the pieces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PieceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pieces");
        List<PieceDTO> pieceDTOS = pieceRepository.findAll()
            .stream()
            .map(pieceMapper::toDto)
            .filter(pieceDTO -> pieceDTO.isDeleted() != null && !pieceDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(pieceDTOS, pageable, pieceDTOS.size());
    }



    /**
     * Get one piece by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PieceDTO> findOne(Long id) {
        log.debug("Request to get Piece : {}", id);
        return pieceRepository.findById(id)
            .map(pieceMapper::toDto);
    }

    /**
     * Delete the piece by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Piece : {}", id);
        pieceRepository.deleteById(id);
    }
    public List<PieceDTO> updateAall(List<PieceDTO> pieceDTOS){
        pieceDTOS.forEach(pieceDTO -> {
            pieceDTO.setDeleted(true);
        });
        pieceRepository.saveAll(pieceDTOS.stream().map(pieceMapper::toEntity).collect(Collectors.toList()));
        List<PieceDTO> pieceDTOS1=pieceRepository.findAll().stream().map(pieceMapper::toDto).filter(pieceDTO ->
            pieceDTO.isDeleted()!=null && !pieceDTO.isDeleted()).collect(Collectors.toList());
        return pieceDTOS;
    }
}
