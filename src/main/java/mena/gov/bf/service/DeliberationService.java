package mena.gov.bf.service;

import mena.gov.bf.data.fileManager.FileManagerService;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.domain.Deliberation;
import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.repository.CandidatLotRepository;
import mena.gov.bf.repository.DeliberationRepository;
import mena.gov.bf.service.dto.DeliberationDTO;
import mena.gov.bf.service.mapper.CandidatLotMapper;
import mena.gov.bf.service.mapper.DeliberationMapper;
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
 * service Implementation for managing {@link Deliberation}.
 */
@Service
@Transactional
public class DeliberationService {

    private final Logger log = LoggerFactory.getLogger(DeliberationService.class);

    private final DeliberationRepository deliberationRepository;

    private final DeliberationMapper deliberationMapper;
    private final CandidatLotRepository candidatLotRepository;
    private final CandidatLotMapper candidatLotMapper;
    @Autowired
    private FileManagerService fileManagerService;

    public DeliberationService(DeliberationRepository deliberationRepository, DeliberationMapper deliberationMapper,
            CandidatLotRepository candidatLotRepository, CandidatLotMapper candidatLotMapper) {
        this.deliberationRepository = deliberationRepository;
        this.deliberationMapper = deliberationMapper;
        this.candidatLotRepository = candidatLotRepository;
        this.candidatLotMapper = candidatLotMapper;
    }

    /**
     * Save a deliberation.
     *
     * @param deliberationDTO the entity to save.
     * @return the persisted entity.
     */
    public DeliberationDTO save(DeliberationDTO deliberationDTO) {
        log.debug("Request to save Deliberation : {}", deliberationDTO);
        Deliberation deliberation = deliberationMapper.toEntity(deliberationDTO);
        deliberation = deliberationRepository.save(deliberation);

        /**
         * Traitement des mise à jour de la table candidatLot
         */

        List<CandidatLot> candidatLots = candidatLotMapper.toEntity(deliberationDTO.getCandidatLots());
        if (!candidatLots.isEmpty()) {
            for (CandidatLot candidatLot : candidatLots) {
                candidatLot.setDeliberation(deliberation);
                if (candidatLot.getMontantCorrigeTtc() != null) {
                    candidatLot.setMontantCorrigeHt((candidatLot.getMontantCorrigeTtc() * 100) / 18);
                }
            }
            candidatLotRepository.saveAll(candidatLots);
        }

        if (!deliberationDTO.getFiles().isEmpty()) {
            fileManagerService.fileUploading(deliberation.getId(), TypeDossier.DELIBERATION,
                    deliberationDTO.getFiles());
        }
        return deliberationMapper.toDto(deliberation);
    }

    /**
     * Get all the deliberations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DeliberationDTO> findAll(Pageable pageable, Long lotId) {
        log.debug("Request to get all Deliberations");
        List<DeliberationDTO> deliberations = new ArrayList<>();
        if (lotId != 0) {
            deliberations = deliberationRepository.findAll().stream()
                    .filter(deliberation -> deliberation.getLotId() != null && deliberation.getLotId().equals(lotId)
                            && deliberation.isDeleted() != null && !deliberation.isDeleted())
                    .map(deliberationMapper::toDto).collect(Collectors.toList());
        } else {
            deliberationRepository.findAll().stream()
                    .filter(deliberation -> deliberation.isDeleted() != null && !deliberation.isDeleted())
                    .map(deliberationMapper::toDto).collect(Collectors.toList());
        }

        return new PageImpl<>(deliberations, pageable, deliberations.size());
    }

    /**
     * Get one deliberation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeliberationDTO> findOne(Long id) {
        log.debug("Request to get Deliberation : {}", id);
        Optional<DeliberationDTO> deliberation = deliberationRepository.findById(id).map(deliberationMapper::toDto);
        deliberation.ifPresent(deliberationDTO -> deliberationDTO.setFiles(this.fileManagerService.getEntityDataFile(id, TypeDossier.DELIBERATION)));
        return deliberation;
    }

    /**
     * Delete the deliberation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Deliberation : {}", id);
        deliberationRepository.deleteById(id);
    }

    public List<DeliberationDTO> deleteAllDeliberation(List<DeliberationDTO> deliberationDTOS) {
        deliberationDTOS.forEach(deliberationDTO -> {
            deliberationDTO.setDeleted(true);
        });
        deliberationRepository
                .deleteAll(deliberationDTOS.stream().map(deliberationMapper::toEntity).collect(Collectors.toList()));
        return deliberationDTOS;
    }

    public DeliberationDTO activeDeliberation(DeliberationDTO deliberationDTO) {
        Deliberation deliberation = deliberationMapper.toEntity(deliberationDTO);
        updateDeliberation(deliberation);
        deliberation = deliberationRepository.save(deliberation);
        return deliberationMapper.toDto(deliberation);
    }

    public DeliberationDTO activeDeliberationEtatMarche(DeliberationDTO deliberationDTO) {
        Deliberation deliberation = deliberationMapper.toEntity(deliberationDTO);
        updateDeliberationEtatMarche(deliberation);
        deliberation = deliberationRepository.save(deliberation);
        return deliberationMapper.toDto(deliberation);
    }

    public void updateDeliberation(Deliberation deliberation) {
        if (!deliberation.isValide()) {
            this.deliberationRepository.saveAll(deliberationRepository.findAll().stream()
                    .peek(deliberation1 -> deliberation.setValide(false)).collect(Collectors.toList()));
        }
    }

    public void updateDeliberationEtatMarche(Deliberation deliberation) {
        if (deliberation.getEtatMarche()== false) {
            this.deliberationRepository.saveAll(deliberationRepository.findAll().stream()
                    .peek(deliberation1 -> deliberation.setEtatMarche(false)).collect(Collectors.toList()));
        }
    }
}
