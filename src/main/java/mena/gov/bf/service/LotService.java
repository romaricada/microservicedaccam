package mena.gov.bf.service;

import mena.gov.bf.domain.Lot;
import mena.gov.bf.repository.*;
import mena.gov.bf.service.dto.CandidatLotDTO;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.service.mapper.LotMapper;
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
 * service Implementation for managing {@link Lot}.
 */
@Service
@Transactional
public class LotService {

    private final Logger log = LoggerFactory.getLogger(LotService.class);

    private final LotRepository lotRepository;

    private final LotMapper lotMapper;

    private final CandidatLotService candidatLotService;

//    @Autowired
//    private DepouillementService depouillementService;
//
//    @Autowired
//    private CautionMapper cautionMapper;
//
//    @Autowired
//    private CautionRepository cautionRepository;
//
//    @Autowired
//    private CautionService cautionService;
//
//    @Autowired
//    private TypeCautionRepository typeCautionRepository;
//
//    @Autowired
//    private TypeCautionMapper typeCautionMapper;
    public LotService(
            LotRepository lotRepository,
            LotMapper lotMapper,
            CandidatLotService candidatLotService) {
        this.lotRepository = lotRepository;
        this.lotMapper = lotMapper;
        this.candidatLotService = candidatLotService;
    }

    /**
     * Save a lot.
     *
     * @param lotDTO the entity to save.
     * @return the persisted entity.
     */
    public LotDTO save(LotDTO lotDTO) {
        log.debug("Request to save Lot : {}", lotDTO);
        /**
         * Traitement des mise à jour de la table caution
         */
//        Set<CautionDTO> cautionLotsDTOs = lotDTO.getCautionslots();
//        List<Caution> cautionList = new ArrayList<>();
//        if (!cautionLotsDTOs.isEmpty()) {
//            // suppression des caution retirer
//         cautionRepository.deleteAll(this.deleteCaution(cautionLotsDTOs, lot.getId()));
//            for (CautionDTO caution : cautionLotsDTOs) {
//                caution.setLotId(lot.getId());
//                if (caution.getTypeCautionId() == null) {
//                    TypeCaution tc = typeCautionRepository.save(typeCautionMapper.toEntity(caution.getTypeCaution()));
//                    caution.setTypeCautionId(tc.getId());
//                }
//                cautionList.add(cautionMapper.toEntity(caution));
//            }
//            cautionRepository.saveAll(cautionList);
//        }
        return lotMapper.toDto(lotRepository.save(lotMapper.toEntity(lotDTO)));
    }
//    private List<Caution> deleteCaution (Set<CautionDTO> newList, Long lotId) {
//        List<Caution> existCautionList = cautionRepository.findAll().stream()
//                    .filter(c -> c.isDeleted() != null && c.getLot() != null && c.getLot().getId().equals(lotId))
//                    .collect(Collectors.toList());
//         List<Caution> suppList = new ArrayList<>();
//            if (!existCautionList.isEmpty()) {
//                existCautionList.forEach(exist -> {
//                   if (!newList.stream().filter(newEl-> newEl.getId()!=null && newEl.getId().equals(exist.getId())).findFirst().isPresent()) {
//                       suppList.add(exist);
//                   }
//                });
//            }
//        return suppList;
//    }

    /**
     * Get all the lots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lots");
        return lotRepository.findAll(pageable)
                .map(lotMapper::toDto);
    }

    /**
     * Get one lot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LotDTO> findOne(Long id) {
        log.debug("Request to get Lot : {}", id);
        return lotRepository.findById(id)
                .map(lotMapper::toDto);
    }

    /**
     * Delete the lot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Lot : {}", id);
        lotRepository.deleteById(id);
    }

    public List<LotDTO> deleteAll(List<LotDTO> lotDTOS) {
        lotDTOS.forEach(naturePrestationDTO -> {
            naturePrestationDTO.setDeleted(true);
        });
        lotRepository.deleteAll(
                lotDTOS.stream().map(lotMapper::toEntity).collect(Collectors.toList()));
        return lotDTOS;

    }



    public void changeStatusLot(LotDTO lotDTO) {
        Lot lot = lotMapper.toEntity(lotDTO);
        lotRepository.save(lot);
    }

    public List<LotDTO> findAllLotByActiviteWithCandidat(Long activiteId, boolean status) {
        List<LotDTO> lotDTOList = this.findLotByAvisDac(activiteId);
        if (!lotDTOList.isEmpty()) {
            lotDTOList.forEach(lotDTO -> {
                if (!status) {
                    // lotDTO.setCandidatLots( this.candidatLotService.findAllByLot( lotDTO.getId() ) );
                } else {
                    //  lotDTO.setCandidatLots( this.initCandidatLotAndCandidatCaution( lotDTO.getId() ) );
                }
            });
        }
        return lotDTOList;
    }

    public List<LotDTO> findAllLot(Long lotId) {
        return lotRepository.findAllByDeletedIsFalse()
                .stream()
                .filter(lot -> lot.getId() != null && lot.getId() == lotId)
                .map(lotMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<CandidatLotDTO> initCandidatLotAndCandidatCaution(Long lotId) {
        List<CandidatLotDTO> candidatLotDTOList = candidatLotService.findSoumissionnaireByLot(lotId);
        if (!candidatLotDTOList.isEmpty()) {
            //  return depouillementService.getCandidatDTO( candidatLotDTOList );
        }
        return new ArrayList<>();
    }

    public List<LotDTO> findOneLot(Long lotId) {
        return lotRepository.findAll().stream().filter(lot -> lot.getId() != null && lot.getId().equals(lotId))
                .map(lotMapper::toDto).collect(Collectors.toList());
    }

    public List<LotDTO> findLotByAvisDac(Long avisDacId){
        return lotRepository.findAllByAvisDacIdAndDeletedIsFalse(avisDacId).stream().filter(lot ->lot.getAvisDac()!= null).map(lotMapper::toDto)
            .collect(Collectors.toList());
    }

    /* public List<LotDTO> findAllLot(Long lotId) {
        return lotRepository.findAllByDeletedIsFalse().stream().filter(lot -> lot.isDeleted() != null
        && !lot.isDeleted() && lot.getId().equals(lotId)).map(lotMapper::toDto).collect(Collectors.toList());
    }*/
}
