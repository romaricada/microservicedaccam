package mena.gov.bf.service;

import mena.gov.bf.domain.PieceCandidat;
import mena.gov.bf.repository.PieceCandidatRepository;
import mena.gov.bf.repository.PieceRepository;
import mena.gov.bf.service.dto.PieceCandidatDTO;
import mena.gov.bf.service.dto.PieceDTO;
import mena.gov.bf.service.mapper.PieceCandidatMapper;
import mena.gov.bf.service.mapper.PieceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link PieceCandidat}.
 */
@Service
@Transactional
public class PieceCandidatService {

    private final Logger log = LoggerFactory.getLogger( PieceCandidatService.class );

    private final PieceCandidatRepository pieceCandidatRepository;

    private final PieceCandidatMapper pieceCandidatMapper;

    private final PieceRepository pieceRepository;

    private final PieceMapper pieceMapper;

    public PieceCandidatService(
        PieceCandidatRepository pieceCandidatRepository,
        PieceCandidatMapper pieceCandidatMapper,
        PieceRepository pieceRepository,
        PieceMapper pieceMapper) {
        this.pieceCandidatRepository = pieceCandidatRepository;
        this.pieceCandidatMapper = pieceCandidatMapper;
        this.pieceRepository = pieceRepository;
        this.pieceMapper = pieceMapper;
    }

    /**
     * Save a pieceCandidat.
     *
     * @param pieceCandidatDTO the entity to save.
     * @return the persisted entity.
     */
    public PieceCandidatDTO save(PieceCandidatDTO pieceCandidatDTO) {
        log.debug( "Request to save PieceCandidat : {}", pieceCandidatDTO );
        PieceCandidat pieceCandidat = pieceCandidatMapper.toEntity( pieceCandidatDTO );
        pieceCandidat = pieceCandidatRepository.save( pieceCandidat );
        return pieceCandidatMapper.toDto( pieceCandidat );
    }

    /**
     * Get all the pieceCandidats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PieceCandidatDTO> findAll(Pageable pageable) {
        log.debug( "Request to get all PieceCandidats" );
        return pieceCandidatRepository.findAll( pageable )
            .map( pieceCandidatMapper::toDto );
    }


    /**
     * Get one pieceCandidat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PieceCandidatDTO> findOne(Long id) {
        log.debug( "Request to get PieceCandidat : {}", id );
        return pieceCandidatRepository.findById( id )
            .map( pieceCandidatMapper::toDto );
    }

    /**
     * Delete the pieceCandidat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug( "Request to delete PieceCandidat : {}", id );
        pieceCandidatRepository.deleteById( id );
    }

    public List<PieceCandidatDTO> initPieceCandidat() {
        List<PieceDTO> pieces = pieceRepository.findAll().stream().filter( value -> value.isDeleted() != null && !value.isDeleted() )
            .map( pieceMapper::toDto ).collect( Collectors.toList() );
        List<PieceCandidatDTO> pieceCandidatDTOList = new ArrayList<>();
        if (!pieces.isEmpty()) {
            for (PieceDTO piece : pieces) {
                PieceCandidatDTO pieceCandidat = new PieceCandidatDTO();
                pieceCandidat.setPieceId( piece.getId() );
                pieceCandidat.setPiece( piece );
                pieceCandidat.setValide( true );
                pieceCandidat.setDeleted( false );
                pieceCandidatDTOList.add( pieceCandidat );
            }
        }
        return pieceCandidatDTOList;
    }

    private List<PieceCandidat> getPieceByDepouillementOfCandidatLot(Long candidatLotId, Long depouillementId) {
        return pieceCandidatRepository.findAll().stream().filter( piece -> piece.getCandidatLot() != null
            && piece.getCandidatLot().getDepouillement()!=null
            && piece.getCandidatLot().getDepouillement().getId().equals( depouillementId )
            && piece.isDeleted() != null && !piece.isDeleted()
            && piece.getCandidatLot() != null && piece.getCandidatLot().getId().equals( candidatLotId ) )
            .collect( Collectors.toList() );
    }
    public List<PieceCandidat> findAllPiceCandidatByCandidatLot(Long candidatLotId, Long depouillementId) {
        return this.getPieceByDepouillementOfCandidatLot( candidatLotId,depouillementId );
    }

    public List<PieceCandidatDTO> findAllPiceCandidatByCandidatLotWithDTO(Long candidatLotId, Long depouillementId) {
        return this.getPieceByDepouillementOfCandidatLot( candidatLotId,depouillementId ).stream().map( pieceCandidatMapper::toDto )
            .collect( Collectors.toList());
    }
}
