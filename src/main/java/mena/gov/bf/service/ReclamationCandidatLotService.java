package mena.gov.bf.service;

import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.domain.ReclamationCandidatLot;
import mena.gov.bf.repository.CandidatLotRepository;
import mena.gov.bf.repository.DecisionRepository;
import mena.gov.bf.repository.ReclamationCandidatLotRepository;
import mena.gov.bf.service.dto.CandidatDTO;
import mena.gov.bf.service.dto.CandidatLotDTO;
import mena.gov.bf.service.dto.DecisionDTO;
import mena.gov.bf.service.dto.ReclamationCandidatLotDTO;
import mena.gov.bf.service.mapper.CandidatLotMapper;
import mena.gov.bf.service.mapper.DecisionMapper;
import mena.gov.bf.service.mapper.ReclamationCandidatLotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link ReclamationCandidatLot}.
 */
@Service
@Transactional
public class ReclamationCandidatLotService {

    private final Logger log = LoggerFactory.getLogger(ReclamationCandidatLotService.class);

    private final ReclamationCandidatLotRepository reclamationCandidatLotRepository;

    private final CandidatLotRepository candidatLotRepository;

    private final CandidatLotService candidatLotService;

    private final ReclamationCandidatLotMapper reclamationCandidatLotMapper;

    private final DecisionRepository decisionRepository;

    private final DecisionMapper decisionMapper;

    @Autowired
    CandidatLotMapper candidatLotMapper;

    public ReclamationCandidatLotService(ReclamationCandidatLotRepository reclamationCandidatLotRepository,
                                         CandidatLotService candidatLotService, CandidatLotRepository candidatLotRepository,
                                         ReclamationCandidatLotMapper reclamationCandidatLotMapper, DecisionRepository decisionRepository,
                                         DecisionMapper decisionMapper) {
        this.reclamationCandidatLotRepository = reclamationCandidatLotRepository;
        this.reclamationCandidatLotMapper = reclamationCandidatLotMapper;
        this.candidatLotRepository = candidatLotRepository;
        this.candidatLotService = candidatLotService;
        this.decisionRepository = decisionRepository;
        this.decisionMapper = decisionMapper;
    }

    /**
     * Save a reclamationCandidatLot.
     *
     * @param reclamationCandidatLotDTO the entity to save.
     * @return the persisted entity.
     */
    public ReclamationCandidatLotDTO save(ReclamationCandidatLotDTO reclamationCandidatLotDTO) {
        log.debug("Request to save ReclamationCandidatLot : {}", reclamationCandidatLotDTO);
        ReclamationCandidatLot reclamationCandidatLot = reclamationCandidatLotMapper.toEntity(reclamationCandidatLotDTO);
        reclamationCandidatLot = reclamationCandidatLotRepository.save(reclamationCandidatLot);
        return reclamationCandidatLotMapper.toDto(reclamationCandidatLot);
    }

    /**
     * Get all the reclamationCandidatLots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReclamationCandidatLotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReclamationCandidatLots");
        return reclamationCandidatLotRepository.findAll(pageable)
            .map(reclamationCandidatLotMapper::toDto);
    }


    /**
     * Get one reclamationCandidatLot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReclamationCandidatLotDTO> findOne(Long id) {
        log.debug("Request to get ReclamationCandidatLot : {}", id);
        return reclamationCandidatLotRepository.findById(id)
            .map(reclamationCandidatLotMapper::toDto);
    }

    /**
     * Delete the reclamationCandidatLot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ReclamationCandidatLot : {}", id);
        reclamationCandidatLotRepository.deleteById(id);
    }


    /* public List<ReclamationCandidatLotDTO> initReclamationCandidatLot(Long lotId) {
        List<ReclamationCandidatLot> reclamationCandidatLots = new ArrayList<>();

        List<CandidatLot> candidatLots = this.candidatLotService.findSoumissionnaireByLotWithoutDTO(lotId);
        log.debug("======================candidatLots==============================================================={}", candidatLots);
        if (!candidatLots.isEmpty()) {
            candidatLots.forEach(candidatLot -> {
                ReclamationCandidatLot reclamationCandidatLot = new ReclamationCandidatLot();
                reclamationCandidatLot.setCandidatLot(candidatLot);
                reclamationCandidatLot.setDeleted(false);
                reclamationCandidatLots.add(reclamationCandidatLot);
            });
        }
        return reclamationCandidatLotMapper.toDto(reclamationCandidatLots);
    }

     public List<ReclamationCandidatLotDTO> initReclamationCandidatLot(List<Long> lotId) {
        List<ReclamationCandidatLot> reclamationCandidatLots = new ArrayList<>();

        List<CandidatLot> candidatLots = this.candidatLotService.findSoumissionnaireByLotWithoutDTOS(lotId);
        log.debug("======================candidatLots==============================================================={}", candidatLots);
        if (!candidatLots.isEmpty()) {
            candidatLots.forEach(candidatLot -> {
                ReclamationCandidatLot reclamationCandidatLot = new ReclamationCandidatLot();
                reclamationCandidatLot.setCandidatLot(candidatLot);
                reclamationCandidatLot.setDeleted(false);
                reclamationCandidatLots.add(reclamationCandidatLot);
            });
        }
        return reclamationCandidatLotMapper.toDto(reclamationCandidatLots);
    }*/

     public List<ReclamationCandidatLotDTO> initReclamationCandidatLot2 (List<Long> lotId) {
         log.debug("======================Lots==============================================================={}", lotId);

         List<ReclamationCandidatLot> reclamationCandidatLots = new ArrayList<>();

        List<CandidatLot> candidatLots = candidatLotService.findSoumissionnaireByLotWithoutDTOS(lotId);

         if (!candidatLots.isEmpty()) {
             candidatLots.forEach(candidatLot1 -> {
                 ReclamationCandidatLot reclamationCandidatLot = new ReclamationCandidatLot();
                 reclamationCandidatLot.setCandidatLot(candidatLot1);
                 reclamationCandidatLot.setDeleted(false);
                 reclamationCandidatLots.add(reclamationCandidatLot);
             });
         }
         return reclamationCandidatLotMapper.toDto(reclamationCandidatLots);

     }

    public List<ReclamationCandidatLot> findAllRecmationCandidatLotByReclamation(Long idReclamation) {
        return reclamationCandidatLotRepository.findAll().stream()
            .filter(reclamationCandidatLot -> reclamationCandidatLot.getReclamation() != null && reclamationCandidatLot.getReclamation().getId().equals(idReclamation)
                && reclamationCandidatLot.isDeleted() != null && !reclamationCandidatLot.isDeleted()).collect(Collectors.toList());
    }

    public Optional<DecisionDTO> findDecisionByReclamationCandidatLot (Long idDecision) {
        return reclamationCandidatLotRepository.findAll().stream().filter(reclamationCandidatLot -> reclamationCandidatLot.getDecision() != null
            && reclamationCandidatLot.getId().equals(idDecision)).map(ReclamationCandidatLot::getDecision)
            .map(decisionMapper::toDto).findFirst();

    }

//    public Optional<CandidatLotDTO> findCandidatByReclamationCandidatLot (Long idCandidatLot){
//        return reclamationCandidatLotRepository.findAll().stream().filter(reclamationCandidatLot -> reclamationCandidatLot.getCandidatLot != null
//        && reclamationCandidatLot.getCandidatLot().getCandidat().getId().equals(idCandidatLot)).map(Re)
//    }

}
