package mena.gov.bf.service;

import mena.gov.bf.data.fileManager.FileManagerService;
import mena.gov.bf.domain.CandidatCautionLot;
import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.repository.CandidatCautionLotRepository;
import mena.gov.bf.service.dto.CandidatCautionLotDTO;
import mena.gov.bf.service.mapper.CandidatCautionLotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link CandidatCautionLot}.
 */
@Service
@Transactional
public class CandidatCautionLotService {

    private final Logger log = LoggerFactory.getLogger( CandidatCautionLotService.class );

    private final CandidatCautionLotRepository candidatCautionLotRepository;

    private final CandidatCautionLotMapper candidatCautionLotMapper;

    private final FileManagerService fileManagerService;

    public CandidatCautionLotService(CandidatCautionLotRepository candidatCautionLotRepository, CandidatCautionLotMapper candidatCautionLotMapper, FileManagerService fileManagerService) {
        this.candidatCautionLotRepository = candidatCautionLotRepository;
        this.candidatCautionLotMapper = candidatCautionLotMapper;
        this.fileManagerService = fileManagerService;
    }

    /**
     * Save a candidatCautionLot.
     *
     * @param candidatCautionLotDTO the entity to save.
     * @return the persisted entity.
     */
    public CandidatCautionLotDTO save(CandidatCautionLotDTO candidatCautionLotDTO) {
        log.debug( "Request to save CandidatCautionLot : {}", candidatCautionLotDTO );
        CandidatCautionLot candidatCautionLot = candidatCautionLotMapper.toEntity( candidatCautionLotDTO );
        candidatCautionLot = candidatCautionLotRepository.save( candidatCautionLot );
        if (!candidatCautionLotDTO.getFiles().isEmpty()) {
            fileManagerService.fileUploading(candidatCautionLot.getId(), TypeDossier.CANDIDATCAUTIONLOT,
                candidatCautionLotDTO.getFiles());
        }
        return candidatCautionLotMapper.toDto( candidatCautionLot );
    }

    /**
     * Get all the candidatCautionLots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatCautionLotDTO> findAll(Pageable pageable) {
        log.debug( "Request to get all CandidatCautionLots" );
        return candidatCautionLotRepository.findAll( pageable )
            .map( candidatCautionLotMapper::toDto );
    }


    /**
     * Get one candidatCautionLot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CandidatCautionLotDTO> findOne(Long id) {
        log.debug( "Request to get CandidatCautionLot : {}", id );
        Optional<CandidatCautionLotDTO> candidatCautionLot = candidatCautionLotRepository.findById(id).map(candidatCautionLotMapper::toDto);
        candidatCautionLot.ifPresent(candidatCautionLotDTO -> candidatCautionLotDTO.setFiles(this.fileManagerService.getEntityDataFile(id, TypeDossier.CANDIDATCAUTIONLOT)));
        return candidatCautionLot;
    }

    /**
     * Delete the candidatCautionLot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug( "Request to delete CandidatCautionLot : {}", id );
        candidatCautionLotRepository.deleteById( id );
    }

    public Optional<CandidatCautionLotDTO> findCandidatCautionLotByCandidatLot(Long idCandidatLot) {
        return candidatCautionLotRepository.findAll().stream()
            .filter( candidatCautionLot -> candidatCautionLot.isDepoullement() != null && candidatCautionLot.isDepoullement()
                && candidatCautionLot.getCandidatLot() != null && candidatCautionLot.getCandidatLot().getId().equals( idCandidatLot )
                && candidatCautionLot.isDeleted() != null && !candidatCautionLot.isDeleted() )
            .map( candidatCautionLotMapper::toDto ).findFirst();
    }

    public List<CandidatCautionLotDTO> findCautionCandidatLot(Long candididatLotId){
       return candidatCautionLotRepository.findAll().stream().filter(candidatCautionLot -> candidatCautionLot.getCandidatLot()!=null &&
           candidatCautionLot.getCandidatLot().getId().equals(candididatLotId)).map(candidatCautionLotMapper::toDto).collect(Collectors.toList());
    }
}
