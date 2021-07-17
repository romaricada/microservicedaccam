package mena.gov.bf.service;

import mena.gov.bf.domain.*;
import mena.gov.bf.downloadFile.DownLoadFileService;
import mena.gov.bf.data.fileManager.FileManagerService;
import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.repository.*;
import mena.gov.bf.service.dto.*;
import mena.gov.bf.service.mapper.CandidatCautionLotMapper;
import mena.gov.bf.service.mapper.CandidatLotMapper;
import mena.gov.bf.service.mapper.DepouillementMapper;
import mena.gov.bf.service.mapper.InstitutionFinanciereMapper;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Depouillement}.
 */
@Service
@Transactional
public class DepouillementService {

    private final Logger log = LoggerFactory.getLogger(DepouillementService.class);

    private final DepouillementRepository depouillementRepository;

    private final DepouillementMapper depouillementMapper;

    private final CandidatLotRepository candidatLotRepository;

    private final CandidatLotService candidatLotService;

    private final CandidatLotMapper candidatLotMapper;

    private final CandidatCautionLotRepository candidatCautionLotRepository;

    private final CandidatCautionLotService candidatCautionLotService;

    private final CandidatCautionLotMapper candidatCautionLotMapper;

    private final InstitutionFinanciereRepository institutionFinanciereRepository;


    @Autowired
    private LotRepository lotRepository;

    @Autowired
    FileManagerService fileManagerService;

    @Autowired
    DownLoadFileService downLoadFileService;


    public DepouillementService(
            DepouillementRepository depouillementRepository,
            DepouillementMapper depouillementMapper,
            CandidatLotRepository candidatLotRepository,
            CandidatLotService candidatLotService,
            CandidatLotMapper candidatLotMapper,
            CandidatCautionLotRepository candidatCautionLotRepository,
            CandidatCautionLotService candidatCautionLotService,
            CandidatCautionLotMapper candidatCautionLotMapper, InstitutionFinanciereRepository institutionFinanciereRepository) {
        this.depouillementRepository = depouillementRepository;
        this.depouillementMapper = depouillementMapper;
        this.candidatLotRepository = candidatLotRepository;
        this.candidatLotService = candidatLotService;
        this.candidatLotMapper = candidatLotMapper;
        this.candidatCautionLotRepository = candidatCautionLotRepository;
        this.candidatCautionLotService = candidatCautionLotService;
        this.candidatCautionLotMapper = candidatCautionLotMapper;
        this.institutionFinanciereRepository = institutionFinanciereRepository;
    }

    /**
     * Save a depouillement.
     *
     * @param depouillementDTO the entity to save.
     * @return the persisted entity.
     */
    @ResponseBody
    public DepouillementDTO save(DepouillementDTO depouillementDTO) {
        log.debug("Request to save Depouillement : {}", depouillementDTO.getCandidats());

        /**
         * Enregistrement du depouillement
         */
        Depouillement depouillement = depouillementMapper.toEntity(depouillementDTO);
        depouillement = depouillementRepository.save(depouillement);


        /**
         * Nouvelle liste de candidat
         */
        List<CandidatDTO> candidats = depouillementDTO.getCandidats();
        if (!candidats.isEmpty()) {
            for (CandidatDTO candidat : candidats) {
                List<CandidatLot> candidatLotListExite = this.findAllCandidatLotByAvisDac(candidat.getId(), depouillement.getAvisDacId());
                if (!candidatLotListExite.isEmpty()) {
                    candidatLotListExite.forEach(value -> {
                        value.setRetenu(false);
                        value.setSoumissionnaire(false);
                        value.setDepouillement(null);
                        value.setMontantLuTtc(null);
                    });
                    candidatLotRepository.saveAll(candidatLotListExite);
                    List<CandidatLotDTO> newCandidatLotDTOList = candidat.getSoumisionniares();
                    if (!newCandidatLotDTOList.isEmpty()) {
                        for (CandidatLot candidatLot : candidatLotListExite) {
                            Optional<CandidatLotDTO> candidatLotDtoOptional = newCandidatLotDTOList.stream().filter(cdlot -> cdlot.getLotId().equals(candidatLot.getLot().getId())).findFirst();
                            if (candidatLotDtoOptional.isPresent()) {
                                candidatLotDtoOptional.get().setRetenu(true);
                                candidatLotDtoOptional.get().setSoumissionnaire(true);
                                candidatLotDtoOptional.get().setDepouillementId(depouillement.getId());
                                if (candidatLotDtoOptional.get().getMontantLuTtc()!=null) {
                                    candidatLotDtoOptional.get().setMontantLuHt((candidatLotDtoOptional.get().getMontantLuTtc() * 100) / 18);
                                }
                                candidatLotRepository.save(candidatLotMapper.toEntity(candidatLotDtoOptional.get()));
                                /*
                                 * Caution du lot.
                                 */
                                if (!candidatLotDtoOptional.get().getCandidatCautionLots().isEmpty()) {
                                    CandidatCautionLot candidatCautionLot = candidatCautionLotMapper.toEntity(candidatLotDtoOptional.get().getCandidatCautionLots().get(0));
                                    /*
                                     * Verifier s'il y a pas une caution de depouillement déjà enregistrer
                                     */
                                    if (depouillement.getId() != null) {
                                        Optional<CandidatCautionLot> candidatCautionLotExist = candidatCautionLotRepository
                                                .findAllByDepoullementIdAndCandidatLotId(depouillement.getId(), candidatLot.getId());
                                        // Suppresion de l'instance qui existe dans la base
                                        candidatCautionLotExist.ifPresent(candidatCautionLotRepository::delete);
                                    }
                                    candidatCautionLot.setCandidatLot(candidatLot);
                                    candidatCautionLot.setDepoullementId(depouillement.getId());

                                    Optional<InstitutionFinanciere> institutionFinanciere = institutionFinanciereRepository.findAll().stream().filter(institutionFinanciere1 ->
                                            institutionFinanciere1.getLibelle().equalsIgnoreCase(candidatLotDtoOptional.get().getCandidatCautionLots().get(0).getInstitutionFinanciere())).findFirst();
                                    if (!institutionFinanciere.isPresent()) {
                                        InstitutionFinanciere institutionFinanciere1 = new InstitutionFinanciere();
                                        institutionFinanciere1.setLibelle(candidatLotDtoOptional.get().getCandidatCautionLots().get(0).getInstitutionFinanciere());
                                        institutionFinanciereRepository.save(institutionFinanciere1);
                                    }
                                    candidatCautionLotRepository.save(candidatCautionLot);
                                }
                            }
                        }
                    } else {
                        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Aucun lot trouvé pour ce soummisionnaire");
                    }
                }
            }
        }

        if (!depouillementDTO.getFiles().isEmpty()) {
            fileManagerService.fileUploading(depouillement.getId(), TypeDossier.DEPOUILLEMENT, depouillementDTO.getFiles());
        }


        return depouillementMapper.toDto(depouillement);
    }

    private List<CandidatDTO> initCandidatInDepouillementAndCandidatCaution(Long idDepouillement, Long avisDacId) {
        List<CandidatDTO> candidatDTOList = candidatLotService.findAllSoummisionnaireByDepouillement(idDepouillement)
                .stream().map(CandidatLotDTO::getCandidat).distinct().collect(Collectors.toList());
        if (!candidatDTOList.isEmpty()) {
            candidatDTOList.forEach(candidatDTO -> {
                candidatDTO.setSoumisionniares(this.getCandidatLotDTOByCandidat(idDepouillement, candidatDTO.getId(), avisDacId));

            });
        }
        return candidatDTOList;
    }

    /**
     * Recuperation de lots du candidat par avis dac.
     *
     * @param candidatId
     * @param avisDacId
     * @return List<CandidatLot>
     */
    private List<CandidatLot> findAllCandidatLotByAvisDac(Long candidatId, Long avisDacId) {
        return candidatLotRepository.findAll().stream()
                .filter(candidatLot -> candidatLot.getCandidat() != null
                        && candidatLot.getCandidat().getId().equals(candidatId)
                        && candidatLot.getLot() != null
                        && candidatLot.getLot().getAvisDac().getId().equals(avisDacId))
                .collect(Collectors.toList());
    }

    private List<CandidatLot> findAllCandidatLotByActiviteAndLotNonInfructuex(Long candidatId, Long avisDacId, Long lotId) {
        return candidatLotRepository.findAll().stream().filter(candidatLot -> candidatLot.getCandidat() != null && candidatLot.getCandidat().getId().equals(candidatId)
                && candidatLot.getLot() != null && candidatLot.getLot().getId().equals(lotId) && !candidatLot.getLot().isInfructueux()
                && candidatLot.getLot().getAvisDac().getId().equals(avisDacId)).collect(Collectors.toList());
    }

    /**
     * Get all the depouillements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */

    @Transactional(readOnly = true)
    public Page<DepouillementDTO> findAll(Pageable pageable, Long avisDacId) {
        log.debug("Request to get all Depouillements");
        List<DepouillementDTO> depouillementDTOArrayList = new ArrayList<>();
        if (avisDacId != 0) {
            depouillementDTOArrayList = depouillementRepository.findAll()
                    .stream().filter(depouillement -> depouillement.getAvisDacId() != null && depouillement.getAvisDacId().equals(avisDacId)
                            && depouillement.isDeleted() != null && !depouillement.isDeleted())
                    .map(depouillementMapper::toDto).collect(Collectors.toList());
        }

        if (!depouillementDTOArrayList.isEmpty()) {
            depouillementDTOArrayList.forEach(depouillementDTO -> {
                depouillementDTO.setCandidats(this.initCandidatInDepouillementAndCandidatCaution(depouillementDTO.getId(), avisDacId));

                Optional<Lot> lot = lotRepository.findTop1ByAvisDacIdAndInfructueuxIsTrue(depouillementDTO.getAvisDacId());
                if (lot.isPresent()) {
                    depouillementDTO.setInvalide(true);
                } else {
                    depouillementDTO.setInvalide(false);
                }
                //  depouillementDTO.setFiles( this.sftpManagerService.findAllFilesByEntity( depouillementDTO.getId(),TypeDossier.DEPOUILLEMENT ) );
            });
        }
        return new PageImpl<>(depouillementDTOArrayList, pageable, depouillementDTOArrayList.size());
    }

    private List<CandidatLotDTO> getCandidatLotDTOByCandidat(Long depId, Long candidatId, Long avisDacId) {
        List<CandidatLotDTO> candidatLotDTOList = candidatLotRepository.findAll().stream().filter(candidatLot -> candidatLot.getDepouillement() != null && candidatLot.getDepouillement().getId().equals(depId)
                && candidatLot.getCandidat() != null && candidatLot.getCandidat().getId().equals(candidatId)
                && candidatLot.getLot() != null && candidatLot.getLot().getAvisDac().getId().equals(avisDacId) && candidatLot.isRetenu()
                && candidatLot.isSoumissionnaire() && !candidatLot.isDeleted()).map(candidatLotMapper::toDto).collect(Collectors.toList());
        candidatLotDTOList.forEach(candidatLotDTO -> {
            Optional<CandidatCautionLot> candidatCautionLotOptional = this.candidatCautionLotRepository
                    .findAllByDepoullementIdAndCandidatLotId(depId, candidatLotDTO.getId());
            if (candidatCautionLotOptional.isPresent()) {
                candidatLotDTO.getCandidatCautionLots().add(candidatCautionLotMapper.toDto(candidatCautionLotOptional.get()));
            } else {
                candidatLotDTO.setCandidatCautionLots(new ArrayList<>());
            }
        });
        return candidatLotDTOList;
    }


    /**
     * Get one depouillement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DepouillementDTO> findOne(Long id) {
        log.debug("Request to get Depouillement : {}", id);
        Optional<DepouillementDTO> depouillement = depouillementRepository.findById(id).map(depouillementMapper::toDto);
        depouillement.ifPresent(depouillementDTO -> depouillementDTO.setFiles(this.fileManagerService.getEntityDataFile(id, TypeDossier.DEPOUILLEMENT)));
        return depouillement;
    }

    /**
     * Delete the depouillement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Depouillement : {}", id);
        depouillementRepository.deleteById(id);
    }
}
