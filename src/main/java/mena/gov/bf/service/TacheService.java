package mena.gov.bf.service;

import mena.gov.bf.data.fileManager.FileManagerService;
import mena.gov.bf.data.fileManager.SFTPFileManagerService;
import mena.gov.bf.domain.*;
import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.domain.enumeration.TypeMessage;
import mena.gov.bf.notification.NotificationService;
import mena.gov.bf.repository.*;
import mena.gov.bf.service.dto.MembreCommissionDTO;
import mena.gov.bf.service.dto.TacheDTO;
import mena.gov.bf.service.dto.TacheEtapeDTO;
import mena.gov.bf.service.mapper.*;
import mena.gov.bf.service.mapper.MembreCommissionMapper;
import mena.gov.bf.service.mapper.MembreMapper;
import mena.gov.bf.service.mapper.TacheEtapeMapper;
import mena.gov.bf.service.mapper.TacheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Tache}.
 */
@Service
@Transactional
public class TacheService {

    private final Logger log = LoggerFactory.getLogger(TacheService.class);

    private final TacheRepository tacheRepository;

    private final TacheMapper tacheMapper;
    /* **************************************** */
    @Autowired
    private MembreCommissionRepository membreCommissionRepository;

    @Autowired
    private MembreCommissionMapper membreCommissionMapper;

    @Autowired
    private TacheEtapeRepository tacheEtapeRepository;

    @Autowired
    private TacheEtapeMapper tacheEtapeMapper;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private MembreMapper membreMapper;

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private TypeCommissionRepository typeCommissionRepository;

    @Autowired
    private TypeCommissionMapper typeCommissionMapper;

    @Autowired
    private FileManagerService fileManagerService;

    @Autowired
    private SFTPFileManagerService sftpManagerService;

    @Autowired
    private NotificationService notificationService;

    public TacheService(TacheRepository tacheRepository, TacheMapper tacheMapper) {
        this.tacheRepository = tacheRepository;
        this.tacheMapper = tacheMapper;
    }

    /**
     * Save a tache.
     *
     * @param tacheDTO the entity to save.
     * @return the persisted entity.
     */
    public TacheDTO save(TacheDTO tacheDTO) {
        log.debug("Request to save Tache : {}", tacheDTO);
        Tache tache = tacheMapper.toEntity(tacheDTO);
        tache = tacheRepository.save(tache);
        return tacheMapper.toDto(tache);
    }

    public TacheDTO createTache(TacheDTO tacheDTO) {
       Tache tache = tacheMapper.toEntity(tacheDTO);
        TypeCommission typeCommission = typeCommissionMapper.toEntity(tacheDTO.getTypeCommission());
           Set<MembreCommission> membreCommissions = membreCommissionRepository.findAllByDeletedIsFalseAndTacheId(tacheDTO.getId());
               membreCommissions.forEach(membreCommission -> membreCommission.setTache(null));
               membreCommissionRepository.saveAll(membreCommissions);
           Set<TacheEtape> tacheEtapes = tacheEtapeRepository.findAllByDeletedIsFalseAndTacheId(tacheDTO.getId());
           tacheEtapes.forEach(tacheEtape -> tacheEtape.setTache(null));
           tacheEtapeRepository.saveAll(tacheEtapes);

       tache.setDateCreation(LocalDate.now());
       Tache tache1 = tacheRepository.save(tache);

       if (!tacheDTO.getMembreCommissions().isEmpty()) {
           TypeCommission typeCommission1 = typeCommissionRepository.save(typeCommission);
           Set<MembreCommission> membreCommissions1 = tacheDTO.getMembreCommissions().stream()
               .peek(membreCommission -> {
                   Membre membre = membreRepository.save(membreMapper.toEntity(membreCommission.getMembre()));
                   membreCommission.setMembreId(membre.getId());
                   membreCommission.setTypeCommissionId(typeCommission1.getId());

                   if (tacheDTO.getTypeCommission().getId() == null) {
                       membreCommission.setId(null);
                   }
               }).map(membreCommissionMapper::toEntity).peek(membreCommission ->
                   membreCommission.setTache(tache1)).collect(Collectors.toSet());
           membreCommissionRepository.saveAll(membreCommissions1);
       }

        if (!tacheDTO.getTacheEtapes().isEmpty()) {
            Set<TacheEtape> tacheEtapes1 = tacheDTO.getTacheEtapes().stream()
                .map(tacheEtapeMapper::toEntity).peek(tacheEtape ->
                    tacheEtape.setTache(tache1)).collect(Collectors.toSet());
            tacheEtapeRepository.saveAll(tacheEtapes1);
        }

        if (!tacheDTO.getFiles().isEmpty()) {
            fileManagerService.fileUploading( tache1.getId(), TypeDossier.TACHE, tacheDTO.getFiles() );
        }

        if(tacheDTO.getId() == null) {
            notificationService.sendAlerteByTacheAndTypeMessage(tache, TypeMessage.ALERTEDEBUT);
        }

       return tacheMapper.toDto(tache1);
    }

    /**
     *
     * @param membreCommissionDTOS
     * @param action
     * @param tacheId
     * @return TacheDTO
     */
    public TacheDTO addOrRemoveMembreCommissFromTache(List<MembreCommissionDTO> membreCommissionDTOS, Boolean action, Long tacheId) {
        Tache tache = tacheRepository.getOne(tacheId);
        if (action) {
            tache.getMembreCommissions().addAll(membreCommissionDTOS.stream().map(membreCommissionMapper::toEntity).collect(Collectors.toSet()));
        } else {
            tache.getMembreCommissions().removeAll(membreCommissionDTOS.stream().map(membreCommissionMapper::toEntity).collect(Collectors.toSet()));
        }
        return tacheMapper.toDto(tacheRepository.save(tache));
    }

    /**
     * Get all the taches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TacheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Taches");
        return tacheRepository.findAll(pageable)
            .map(tacheMapper::toDto);
    }


    /**
     * Get one tache by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TacheDTO> findOne(Long id) {
        log.debug("Request to get Tache : {}", id);
        Optional<TacheDTO> tache = tacheRepository.findById(id)
            .map(tacheMapper::toDto);
        tache.ifPresent( tacheDTO   -> {
            tacheDTO.setFiles( this.fileManagerService.getEntityDataFile( tacheDTO.getId(), TypeDossier.TACHE));
            });
        return tache;
    }

    /**
     * Delete the tache by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tache : {}", id);
        tacheRepository.deleteById(id);
    }
    public List<TacheDTO> findAlltache() {
        List<TacheDTO> lisNature = tacheRepository.findAll().stream()
            .filter(value -> value.isDeleted() != null && !value.isDeleted()).map(tacheMapper::toDto)
            .collect(Collectors.toList());

        return lisNature;
    }


    /**
     * Recupération de la date de fin du délait.
     * @param tacheEtapeDTO
     * @return TacheEtapeDTO
     */
    public TacheEtapeDTO getDatefinByDelait(TacheEtapeDTO tacheEtapeDTO) {
      tacheEtapeDTO.setDateFin(tacheEtapeDTO.getDateDebut().plusDays(tacheEtapeDTO.getDelai()));
      return tacheEtapeDTO;
    }


    /**
     * Recupération par l'identifiant de l'objet.
     * @param objectId
     * @return Optional<TacheDTO>
     */
    public Optional<TacheDTO> findTacheByObjectId(String objectId) {
        Optional<TacheDTO> tache = tacheRepository.findAllByDeletedIsFalse().stream().filter(tache1 -> tache1.getObjectId() != null &&
                tache1.getObjectId().equals(objectId)).map(tacheMapper::toDto).findFirst();
        tache.ifPresent(tacheDTO ->
                tacheDTO.setFiles(this.fileManagerService.getEntityDataFile(tacheDTO.getId(), TypeDossier.TACHE)));
        return tache;
    }

    /*public List<TacheDTO> findAllByObjectIdAndTypeTache (String objectId, TypeTache typeTache) {
        return tacheRepository.findAll().stream().filter(tache -> tache.isDeleted() != null && !tache.isDeleted() && tache.getObjectId().equals(objectId)
        && tache.getTypeTache().equals(typeTache)).map(tacheMapper::toDto).collect(Collectors.toList());
    }*/

    /**
     * Recupération des tâches en fonction des critères.
     * @param id
     * @param etat
     * @param criteria
     * @return List<TacheDTO>
     */

   public List<TacheDTO> findTacheByCriteria(Long id, String etat, String criteria) {
        List<Tache> taches = tacheRepository.findAllByDeletedIsFalse();
        List<TacheDTO> tacheDTOS = new ArrayList<>();
       switch (criteria) {
           case "byAvisDac": {
               if (!etat.equals("ALL")) {
                   tacheDTOS = getTachesByEtat(getTacheByavisDac(taches, id), etat).stream().map(tacheMapper::toDto).collect(Collectors.toList());
               } else {
                   tacheDTOS = getTacheByavisDac(taches, id).stream().map(tacheMapper::toDto).collect(Collectors.toList());
               }
               break;
           }

           case "byLot": {
               if (!etat.equals("ALL")) {
                   tacheDTOS = getTachesByEtat(getTacheByLot(taches, id), etat).stream().map(tacheMapper::toDto).collect(Collectors.toList());
               } else {
                   tacheDTOS = getTacheByLot(taches, id).stream().map(tacheMapper::toDto).collect(Collectors.toList());
               }
               break;
           }

       }
       return tacheDTOS;
   }


    private List<Tache> getTacheByavisDac(List<Tache> taches, Long avisDacId) {
        return taches.stream().filter(tache -> tache.getAvisDacId().equals(avisDacId)).collect(Collectors.toList());
    }

    private List<Tache> getTacheByLot(List<Tache> taches, Long lotId) {
        Lot lot = lotRepository.getOne(lotId);
        return taches.stream().filter(tache -> tache.getLot() != null &&
            tache.getLot().getId().equals(lot.getId())).collect(Collectors.toList());
    }

    private List<Tache> getTachesByEtat(List<Tache> taches, String etat) {
        return taches.stream().filter(tache ->
            tache.getEtat() != null && tache.getEtat().toString().equals(etat)
            ).collect(Collectors.toList());
    }

    private Boolean finByTacheAndEtat(Set<TacheWorkflow> tacheWorkflows, String etat, Long tacheId) {
      List<TacheWorkflow> tacheWorkflows1 =  tacheWorkflows.stream().filter(tacheWorkflow ->
            tacheWorkflow.getTache().getId().equals(tacheId) &&
                tacheWorkflow.getWorkflow().getEtat().toString().equals(etat)).collect(Collectors.toList());
      return !tacheWorkflows1.isEmpty();
    }

    public List<TacheDTO> updateAll (List<TacheDTO> tacheDTOS) {
       tacheDTOS.forEach(tacheDTO -> {
           tacheDTO.setDeleted(true);
       });
       tacheRepository.saveAll(tacheDTOS.stream().map(tacheMapper::toEntity).collect(Collectors.toList()));
       List<TacheDTO> tacheDTOS1 = tacheRepository.findAll().stream().map(tacheMapper::toDto).filter(tacheDTO -> tacheDTO.isDeleted() != null
       && !tacheDTO.isDeleted()).collect(Collectors.toList());
       return tacheDTOS1;
    }

}
