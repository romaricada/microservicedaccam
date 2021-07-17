package mena.gov.bf.service;

import mena.gov.bf.data.fileManager.FileManagerService;
import mena.gov.bf.domain.Decision;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.domain.Reclamation;
import mena.gov.bf.domain.ReclamationCandidatLot;
import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.repository.DecisionRepository;
import mena.gov.bf.repository.LotRepository;
import mena.gov.bf.repository.ReclamationCandidatLotRepository;
import mena.gov.bf.repository.ReclamationRepository;
import mena.gov.bf.service.dto.DecisionDTO;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.service.dto.ReclamationCandidatLotDTO;
import mena.gov.bf.service.dto.ReclamationDTO;
import mena.gov.bf.service.mapper.DecisionMapper;
import mena.gov.bf.service.mapper.LotMapper;
import mena.gov.bf.service.mapper.ReclamationCandidatLotMapper;
import mena.gov.bf.service.mapper.ReclamationMapper;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Reclamation}.
 */
@Service
@Transactional
public class ReclamationService {

    private final Logger log = LoggerFactory.getLogger(ReclamationService.class);

    private final ReclamationRepository reclamationRepository;

    private final ReclamationMapper reclamationMapper;

    private final ReclamationCandidatLotRepository reclamationCandidatLotRepository;
    private final ReclamationCandidatLotService reclamationCandidatLotService;
    private final ReclamationCandidatLotMapper reclamationCandidatLotMapper;
    private final DecisionRepository decisionRepository;
    private final DecisionMapper decisionMapper;

    @Autowired
    FileManagerService fileManagerService;

    @Autowired
    LotRepository lotRepository;

    @Autowired
    LotMapper lotMapper;

    @Autowired
    DecisionService decisionService;

    public ReclamationService(
        ReclamationRepository reclamationRepository,
        ReclamationMapper reclamationMapper,
        ReclamationCandidatLotRepository reclamationCandidatLotRepository,
        ReclamationCandidatLotService reclamationCandidatLotService,
        ReclamationCandidatLotMapper reclamationCandidatLotMapper,
        DecisionRepository decisionRepository, DecisionMapper decisionMapper) {
        this.reclamationRepository = reclamationRepository;
        this.reclamationMapper = reclamationMapper;
        this.reclamationCandidatLotRepository = reclamationCandidatLotRepository;
        this.reclamationCandidatLotService = reclamationCandidatLotService;
        this.reclamationCandidatLotMapper = reclamationCandidatLotMapper;
        this.decisionRepository = decisionRepository;
        this.decisionMapper = decisionMapper;
    }

    /**
     * Save a reclamation.
     *
     * @param reclamationDTO the entity to save.
     * @return the persisted entity.
     */
   /* public ReclamationDTO save(ReclamationDTO reclamationDTO) {
        log.debug("Request to save Reclamation : {}", reclamationDTO);
        Reclamation reclamation = reclamationMapper.toEntity(reclamationDTO);
        reclamation = reclamationRepository.save(reclamation);

        List<ReclamationCandidatLot> reclamationCandidatLotListExiste = reclamationCandidatLotService.findAllRecmationCandidatLotByReclamation(reclamation.getId());
        if (!reclamationCandidatLotListExiste.isEmpty()) {
            reclamationCandidatLotListExiste.forEach(reclamationCandidatLot -> {
                reclamationCandidatLot.setReclamation(null);
                reclamationCandidatLot.setDeleted(true);
            });
            reclamationCandidatLotRepository.saveAll(reclamationCandidatLotListExiste);
        }
        List<ReclamationCandidatLotDTO> reclamationCandidatLotDTOS = reclamationDTO.getReclamationCandidatLots();
        if (!reclamationCandidatLotDTOS.isEmpty()) {
            Reclamation finalReclamation = reclamation;
            reclamationCandidatLotDTOS.forEach(reclamationCandidatLotDTO -> {
                if (reclamationCandidatLotDTO.getDecision() != null) {
                    Decision decision = decisionRepository.save(decisionMapper.toEntity(reclamationCandidatLotDTO.getDecision()));
                    reclamationCandidatLotDTO.setDecisionId(decision.getId());
                }
                reclamationCandidatLotDTO.setReclamationId(finalReclamation.getId());
            });

        }

        reclamationCandidatLotRepository.saveAll(reclamationCandidatLotMapper.toEntity(reclamationCandidatLotDTOS));


        if (!reclamationDTO.getFiles().isEmpty()) {
            fileManagerService.fileUploading( reclamation.getId(), TypeDossier.RECLAMATION, reclamationDTO.getFiles() );
        }
        return reclamationMapper.toDto(reclamation);
    }*/


    public ReclamationDTO save(ReclamationDTO reclamationDTO) {
        log.debug("Request to save Reclamation : {}", reclamationDTO);
        Reclamation reclamation = reclamationMapper.toEntity(reclamationDTO);
        /*if (reclamationDTO.getAvisDacId() != null){
            reclamationDTO.setAvisDacId(reclamation.geta);
            log.debug("=====================================reclamation.getAvisDac()======================================={}", reclamation.getAvisDac());

        }*/
        reclamation = reclamationRepository.save(reclamation);
        log.debug("=====================================reclamation enregistrée======================================={}", reclamation);
        if(!reclamationDTO.getLots().isEmpty()) {
            List<Lot> savListlots = new ArrayList<>();
            for (LotDTO lotDTO: reclamationDTO.getLots()) {
                lotDTO.setReclamationId(reclamation.getId());
                savListlots.add(lotMapper.toEntity(lotDTO));
            }
            lotRepository.saveAll(savListlots);
        }


        List<ReclamationCandidatLot> reclamationCandidatLotListExiste = reclamationCandidatLotService.findAllRecmationCandidatLotByReclamation(reclamation.getId());
        log.debug("=====================================reclamationCandidatLotListExiste======================================={}", reclamationCandidatLotListExiste);
        if (!reclamationCandidatLotListExiste.isEmpty()) {
            Reclamation finalReclamation2 = reclamation;
            reclamationCandidatLotListExiste.forEach(reclamationCandidatLot -> {
                reclamationCandidatLot.setReclamation(finalReclamation2);
                reclamationCandidatLot.setDeleted(true);
            });
            reclamationCandidatLotRepository.saveAll(reclamationCandidatLotListExiste);
        }
        List<ReclamationCandidatLotDTO> reclamationCandidatLotDTOS = reclamationDTO.getReclamationCandidatLots();
        log.debug("======================================reclamationCandidatLotDTOS======================================{}", reclamationCandidatLotDTOS);
        if (!reclamationCandidatLotDTOS.isEmpty()) {
             Reclamation finalReclamation = reclamation;
             Reclamation finalReclamation3 = reclamation;
             Reclamation finalReclamation4 = reclamation;
            reclamationCandidatLotDTOS.forEach(reclamationCandidatLotDTO -> {
                if (reclamationCandidatLotDTO.getDecisions() != null) {
                    List<Decision> savListDecision = new ArrayList<>();
                     decisionRepository.deleteAll(this.actualisationDesDecisions(reclamationDTO.getDecisions(), finalReclamation3.getId()));
                    log.debug("======================================reclamationDTO.getDecisions()======================================{}", reclamationDTO.getDecisions());
                    for (DecisionDTO decisionDTO : reclamationDTO.getDecisions()) {
                        decisionDTO.setReclamationId(finalReclamation4.getId());
                        savListDecision.add(decisionMapper.toEntity(decisionDTO));
                    }
                    this.decisionRepository.saveAll(savListDecision);
                    reclamationCandidatLotDTO.setReclamationId(finalReclamation.getId());
                }

            });
        }

        reclamationCandidatLotRepository.saveAll(reclamationCandidatLotMapper.toEntity(reclamationCandidatLotDTOS));

        return reclamationMapper.toDto(reclamation);

    }



    /**
     * Get all the reclamations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReclamationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reclamations");
        List<ReclamationDTO> reclamationDTOS = reclamationRepository.findAll().stream().filter(reclamation -> reclamation.isDeleted() != null
            && !reclamation.isDeleted()).map(reclamationMapper::toDto).collect(Collectors.toList());
        if (!reclamationDTOS.isEmpty()) {
            reclamationDTOS.forEach(reclamationDTO -> {
                reclamationDTO.setReclamationCandidatLots(this.initReclamationCandidatLotAndDecision(reclamationDTO.getId()));
            });
        }

        /*return reclamationRepository.findAll(pageable)
            .map(reclamationMapper::toDto);*/

        log.debug("=======================================this.reclamationDTOS==============================================================");
        log.debug("reclamationDTOS.size() : {}", reclamationDTOS);
        log.debug("=====================================================================================================");

        return new PageImpl<>( reclamationDTOS, pageable, reclamationDTOS.size() );

    }

    private List<ReclamationCandidatLotDTO> initReclamationCandidatLotAndDecision(Long idReclamation) {
        List<ReclamationCandidatLotDTO> reclamationCandidatLotDTOS = reclamationCandidatLotMapper.toDto(reclamationCandidatLotService.findAllRecmationCandidatLotByReclamation(idReclamation));
        if (!reclamationCandidatLotDTOS.isEmpty()) {
            reclamationCandidatLotDTOS.forEach(reclamationCandidatLotDTO -> {
                Optional<DecisionDTO> decisionDTO = this.reclamationCandidatLotService.findDecisionByReclamationCandidatLot(reclamationCandidatLotDTO.getDecisionId());
                if ((decisionDTO.isPresent())) {
                    reclamationCandidatLotDTO.setDecision(decisionDTO.get());
                }
            });
        }
        return reclamationCandidatLotDTOS;
    }

    /**
     * Get one reclamation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReclamationDTO> findOne(Long id) {
        log.debug("Request to get Reclamation : {}", id);
        return reclamationRepository.findById(id)
            .map(reclamationMapper::toDto);
    }

    /**
     * Delete the reclamation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Reclamation : {}", id);
        reclamationRepository.deleteById(id);
    }

    public List<ReclamationDTO> findReclamationByLot (Long lotId) {
        return reclamationRepository.findAll().stream().filter(reclamation -> reclamation.getLot().getId() != null && reclamation.getLot().getId().equals(lotId)
            && !reclamation.isDeleted())
            .map(reclamationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReclamationDTO> findReclamationByavisDac (Long avisDacId) {
         List<ReclamationDTO> reclamationDTOS =  reclamationRepository.findAll().stream().filter(reclamation -> reclamation.getAvisDac() != null && reclamation.getAvisDac().getId().equals(avisDacId)
            && !reclamation.isDeleted())
            .map(reclamationMapper::toDto).collect(Collectors.toList());
        log.debug("les Reclamations retournées : {}", reclamationDTOS);
        return reclamationDTOS;
    }

    public List<ReclamationDTO> updateAall (List<ReclamationDTO> reclamationDTOS) {
        reclamationDTOS.forEach(reclamationDTO -> {
            reclamationDTO.setDeleted(true);
        });
        reclamationRepository.saveAll(reclamationDTOS.stream().map(reclamationMapper::toEntity).collect(Collectors.toList()));
        List<ReclamationDTO> reclamationDTOS1= reclamationRepository.findAll().stream().map(reclamationMapper::toDto).filter(reclamationDTO ->
            reclamationDTO.isDeleted() != null && !reclamationDTO.isDeleted()).collect(Collectors.toList());
        return reclamationDTOS1;
    }

    /**
     * Mise a jour des decisions avant l'enregistrement.
     *
     * @param newDecisionList the decisionList.
     * @param reclamationId the id of reclamation.
     * @return the entity.
     */
    private List<Decision> actualisationDesDecisions(Set<DecisionDTO> newDecisionList, Long reclamationId) {
        // List<Decision> decisions = new ArrayList<>(decisionRepository.findAllByReclamationIdAndReclamationIsNotNullAndDeletedIsFalse(reclamationId));
        List<Decision> decisions = decisionRepository.findAll().stream().filter(d -> d.getReclamation() != null && d.getReclamation().getId().equals(reclamationId))
            .collect(Collectors.toList());
        log.debug("===============================================decisions======================================================= : {}", decisions);
        List<Decision> suppList =  new ArrayList<>();
        if (!decisions.isEmpty()) {
            decisions.stream().filter(decision -> (newDecisionList.stream().filter(v -> v.getId() != null && v.getId().equals(decision.getId())).findFirst().isPresent()))
                .forEachOrdered(decision -> {
                    suppList.add(decision);
                });
        }
        return suppList;
    }
}
