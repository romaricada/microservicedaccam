package mena.gov.bf.service;

import mena.gov.bf.domain.Candidat;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.repository.CandidatLotRepository;
import mena.gov.bf.repository.CandidatRepository;
import mena.gov.bf.service.dto.CandidatDTO;
import mena.gov.bf.service.mapper.CandidatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Candidat}.
 */
@Service
@Transactional
public class CandidatService {

    private final Logger log = LoggerFactory.getLogger(CandidatService.class);

    private final CandidatRepository candidatRepository;

    private final CandidatMapper candidatMapper;

    @Autowired
    private CandidatLotService candidatLotService;

    @Autowired
    private CandidatLotRepository candidatLotRepository;

    public CandidatService(CandidatRepository candidatRepository, CandidatMapper candidatMapper) {
        this.candidatRepository = candidatRepository;
        this.candidatMapper = candidatMapper;
    }

    /**
     * Save a candidat.
     *
     * @param candidatDTO the entity to save.
     * @return the persisted entity.
     */
    public CandidatDTO save(CandidatDTO candidatDTO) {
        log.debug("Request to save Candidat : {}", candidatDTO);
        Candidat candidat = candidatMapper.toEntity(candidatDTO);
        candidat = candidatRepository.save(candidat);
        return candidatMapper.toDto(candidat);
    }

    /**
     * Get all the candidats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatDTO> findAllCandidat(Pageable pageable) {
        log.debug("Request to get all Candidats");
        List<CandidatDTO> candidatDTOS = candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() ).map( CandidatLot::getCandidat ).filter(candidat -> candidat.isDeleted() !=null &&
            !candidat.isDeleted()).map( candidatMapper::toDto ).distinct().collect( Collectors.toList());
        log.debug("=====================================================================================================");
        log.debug("candidatDTOS.size() : {}", candidatDTOS.size());
        log.debug("=====================================================================================================");

        return new PageImpl<>( candidatDTOS, pageable, candidatDTOS.size() );
    }

    /**
     * Get all the candidats by activite.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatDTO> findAll(Pageable pageable, Long activiteId) {
        log.debug("Request to get all Candidats");
        List<CandidatDTO> candidatDTOS = candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null
            && candidatLot.getLot().getAvisDac().getId().equals( activiteId )
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() ).map( CandidatLot::getCandidat ).filter(candidat -> candidat.isDeleted() !=null &&
            !candidat.isDeleted()).map( candidatMapper::toDto ).distinct().collect( Collectors.toList());
        log.debug("=====================================================================================================");
        log.debug("candidatDTOS.size() : {}", candidatDTOS.size());
        log.debug("=====================================================================================================");
        if(!candidatDTOS.isEmpty()){
            candidatDTOS.forEach(candidatDTO -> {
                candidatDTO.setLots(this.candidatLotService.findLotByCandidat(candidatDTO.getId(), activiteId));
                log.debug("=====================================================================================================");
                log.debug(" liste des lots par candidat 1 : {}", this.candidatLotService.findLotByCandidat(candidatDTO.getId(), activiteId));
                log.debug("=====================================================================================================");
                if (!candidatDTO.getLots().isEmpty()){
                    candidatDTO.getLots().forEach(lotDTO -> {
                        lotDTO.setDossierPaye(this.candidatLotService.findLotByCandidate(candidatDTO.getId(),activiteId,lotDTO.getId()));
                        log.debug("=====================================================================================================");
                        log.debug(" liste des lots par candidat 2 : {}", this.candidatLotService.findLotByCandidate(candidatDTO.getId(),activiteId,lotDTO.getId()));
                        log.debug("=====================================================================================================");
                    });
                }
            });
        }
        return new PageImpl<>( candidatDTOS, pageable, candidatDTOS.size() );
    }

    /**
     * Get all the candidats by Avis Dac.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatDTO> findAllByAvisDacId(Pageable pageable, Long AvisDacId) {
        log.debug("Request to get all Candidats");
        List<CandidatDTO> candidatDTOS = candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null
            && candidatLot.getLot().getAvisDac().getId().equals( AvisDacId )
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() ).map( CandidatLot::getCandidat ).filter(candidat -> candidat.isDeleted() !=null &&
            !candidat.isDeleted()).map( candidatMapper::toDto ).distinct().collect( Collectors.toList());
        log.debug("=====================================================================================================");
        log.debug("candidatDTOS.size() : {}", candidatDTOS.size());
        log.debug("=====================================================================================================");
        if(!candidatDTOS.isEmpty()){
            candidatDTOS.forEach(candidatDTO -> {
                candidatDTO.setLots(this.candidatLotService.findLotByCandidatAndAviDacId(candidatDTO.getId(), AvisDacId));
                log.debug("=====================================================================================================");
                log.debug(" liste des lots par candidat 1 : {}", this.candidatLotService.findLotByCandidatAndAviDacId(candidatDTO.getId(), AvisDacId));
                log.debug("=====================================================================================================");
                if (!candidatDTO.getLots().isEmpty()){
                    candidatDTO.getLots().forEach(lotDTO -> {
                        lotDTO.setDossierPaye(this.candidatLotService.findLotByCandidateAndAviDacIdAndLot(candidatDTO.getId(),AvisDacId,lotDTO.getId()));
                        log.debug("=====================================================================================================");
                        log.debug(" liste des lots par candidat 2 : {}", this.candidatLotService.findLotByCandidateAndAviDacIdAndLot(candidatDTO.getId(),AvisDacId,lotDTO.getId()));
                        log.debug("=====================================================================================================");
                    });
                }
            });
        }
        return new PageImpl<>( candidatDTOS, pageable, candidatDTOS.size() );
    }


    /**
     * Get one candidat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CandidatDTO> findOne(Long id) {
        log.debug("Request to get Candidat : {}", id);
        return candidatRepository.findById(id)
            .map(candidatMapper::toDto);
    }

    /**
     * Delete the candidat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Candidat : {}", id);
        candidatRepository.deleteById(id);
    }

    /*public List<CandidatLotDTO> updateaAll(List<CandidatLotDTO> candidatLotDTOS) {
        candidatLotDTOS.forEach( candidatLotDTO -> {
            candidatLotDTO.setDeleted( true );
        } );
        candidatLotRepository.saveAll( candidatLotDTOS.stream().map( candidatLotMapper::toEntity ).collect( Collectors.toList() ) );
        List<CandidatLotDTO> candidatLotDTOS1 = candidatLotRepository.findAll().stream().map( candidatLotMapper::toDto ).filter( candidatLotDTO ->
            candidatLotDTO.isDeleted() != null && !candidatLotDTO.isDeleted() ).collect( Collectors.toList() );

        return candidatLotDTOS1;
    }*/

    public List<CandidatDTO> updateaAll ( List<CandidatDTO> candidatDTOS) {
        candidatDTOS.forEach(candidatDTO -> {
            candidatDTO.setDeleted(true);
        });
        candidatRepository.saveAll(candidatDTOS.stream().map(candidatMapper::toEntity).collect(Collectors.toList()));
        List<CandidatDTO> candidatDTOS1 = candidatRepository.findAll().stream().map(candidatMapper::toDto).filter(candidatDTO ->
            candidatDTO.isDeleted() != null && !candidatDTO.isDeleted()).collect(Collectors.toList());
        return candidatDTOS1;
    }

    public List<CandidatDTO> findAllCandidatByAvisDac(Long idAvisDac) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null
            && candidatLot.getLot().getAvisDac().getId().equals( idAvisDac )
            && candidatLot.getDepouillement() == null
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() ).map( CandidatLot::getCandidat ).map( candidatMapper::toDto )
            .distinct().peek( candidatDTO -> candidatDTO.setSoumisionniares( new ArrayList<>(  ) ) ).collect( Collectors.toList());
    }

    public List<CandidatDTO> findAllbyAvisDacShow(Long avisdacId){
        List<CandidatDTO> candidatDTOS = candidatRepository.findAll().stream().filter(candidat -> candidat.getAvisdacId()!=null
        && candidat.getAvisdacId().equals(avisdacId)).map(candidatMapper::toDto).collect(Collectors.toList());
        return candidatDTOS;
    }

/*
    public List<CandidatDTO> findAllCandidat( Long activiteId) {
        List<CandidatDTO> candidatDTOS = candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null
            && candidatLot.getLot().getActiviteId().equals( activiteId )
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() ).map( CandidatLot::getCandidat ).filter(candidat -> candidat.isDeleted() !=null &&
            !candidat.isDeleted()).map( candidatMapper::toDto ).distinct().collect( Collectors.toList());
        log.debug("=====================================================================================================");
        log.debug("candidatDTOS.size() : {}", candidatDTOS.size());
        log.debug("=====================================================================================================");
        if(!candidatDTOS.isEmpty()){
            candidatDTOS.forEach(candidatDTO -> {
                candidatDTO.setLots(this.candidatLotService.findLotByCandidat(candidatDTO.getId(), activiteId));
            });
        }
        return candidatDTOS;
    }
*/
}
