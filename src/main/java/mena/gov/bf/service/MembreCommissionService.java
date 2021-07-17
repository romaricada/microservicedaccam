package mena.gov.bf.service;

import mena.gov.bf.domain.MembreCommission;
import mena.gov.bf.domain.Tache;
import mena.gov.bf.repository.MembreCommissionRepository;
import mena.gov.bf.repository.TacheRepository;
import mena.gov.bf.service.dto.MembreCommissionDTO;
import mena.gov.bf.service.dto.MembreDTO;
import mena.gov.bf.service.mapper.MembreCommissionMapper;
import mena.gov.bf.service.mapper.MembreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link MembreCommission}.
 */
@Service
@Transactional
public class MembreCommissionService {

    private final Logger log = LoggerFactory.getLogger(MembreCommissionService.class);

    private final MembreCommissionRepository membreCommissionRepository;

    private final MembreCommissionMapper membreCommissionMapper;

    @Autowired

    MembreMapper membreMapper;

    private TacheRepository tacheRepository;


    public MembreCommissionService(MembreCommissionRepository membreCommissionRepository, MembreCommissionMapper membreCommissionMapper) {
        this.membreCommissionRepository = membreCommissionRepository;
        this.membreCommissionMapper = membreCommissionMapper;
    }

    /**
     * Save a membreCommission.
     *
     * @param membreCommissionDTO the entity to save.
     * @return the persisted entity.
     */
    public MembreCommissionDTO save(MembreCommissionDTO membreCommissionDTO) {
        log.debug("Request to save MembreCommission : {}", membreCommissionDTO);
        MembreCommission membreCommission = membreCommissionMapper.toEntity(membreCommissionDTO);
        membreCommission = membreCommissionRepository.save(membreCommission);
        return membreCommissionMapper.toDto(membreCommission);
    }

    /**
     * Get all the membreCommissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MembreCommissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MembreCommissions");
        List<MembreCommissionDTO> membreCommissionDTOS = membreCommissionRepository.findAll().stream()
            .map(membreCommissionMapper::toDto)
            .filter(naturePrestationDTO -> naturePrestationDTO.isDeleted() != null
                && !naturePrestationDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(membreCommissionDTOS, pageable, membreCommissionDTOS.size());
    }


    /**
     * Get one membreCommission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MembreCommissionDTO> findOne(Long id) {
        log.debug("Request to get MembreCommission : {}", id);
        return membreCommissionRepository.findById(id)
            .map(membreCommissionMapper::toDto);
    }

    /**
     * Delete the membreCommission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MembreCommission : {}", id);
        membreCommissionRepository.deleteById(id);
    }
    public List<MembreCommissionDTO> updateAall(List<MembreCommissionDTO> membreCommissionDTOS) {
        membreCommissionDTOS.forEach(membreCommissionDTO -> {
            membreCommissionDTO.setDeleted(true);
        });
        membreCommissionRepository.saveAll(
            membreCommissionDTOS.stream().map(membreCommissionMapper::toEntity).collect(Collectors.toList()));
        List<MembreCommissionDTO> membreCommissionDTOS1 = membreCommissionRepository.findAll().stream()
            .map(membreCommissionMapper::toDto)
            .filter(naturePrestationDTO -> naturePrestationDTO.isDeleted() != null
                && !naturePrestationDTO.isDeleted())
            .collect(Collectors.toList());
        log.debug("========================================================");
        log.debug("=================this.membreCommissionDTOS1==================");
        return membreCommissionDTOS;
    }


    public List<MembreDTO> findMembreByTypeCommission(Long typeId) {
        return membreCommissionRepository.findAllByTypeCommissionIdAndDeletedIsFalse(typeId)
            .stream()
            .map(MembreCommission::getMembre)
            .map(membreMapper::toDto)
            .collect(Collectors.toList());
    }



    @Transactional(readOnly = true)
    public List<MembreCommissionDTO> listTypeCommission(Long typeCommissionId) {
        return membreCommissionRepository.findMembreCommissionByDeletedIsFalse()
            .stream()
            .filter(membreCommission -> membreCommission.getId() != null && membreCommission.getId().equals(typeCommissionId))
            .map(membreCommissionMapper::toDto)
            .collect(Collectors.toList());
    }

   /* public List<MembreCommissionDTO> findAllByActivite(Long activiteId, Long typeCommissId) {
        List<MembreCommissionDTO> membreCommissionDTOS = membreCommissionRepository.findAllByDeletedIsFalseAndAvisDacIdAndTypeCommissionId(activiteId, typeCommissId)
            .stream().map(membreCommissionMapper::toDto).collect(Collectors.toList());

        membreCommissionDTOS.forEach(membreCommissionDTO -> {
            log.debug("===========Le Membre=========");
            log.debug("{}", membreCommissionDTO.getId());
        });
        return membreCommissionDTOS;
    }*/


//    filtre des membres par avisDac et type commission

    public List<MembreCommissionDTO> findAllByAvisDac (Long avisDacId, Long typeCommissionId) {
        List<MembreCommissionDTO> membreCommissionDTOS = membreCommissionRepository.findAllByDeletedIsFalseAndAvisDacIdAndTypeCommissionId(avisDacId, typeCommissionId)
            .stream().map(membreCommissionMapper::toDto).distinct().collect(Collectors.toList());
        return membreCommissionDTOS;
    }

    public List<MembreCommissionDTO> membreCommissAffectedToTache(Long avisDacId, Long typeCommissId, Long tacheId, Boolean isAffected) {
        Tache tache = tacheRepository.getOne(tacheId);
        List<MembreCommissionDTO> membreCommissionDTOS;

        List<MembreCommission> membreCommissions = membreCommissionRepository.findAllByDeletedIsFalseAndAvisDacIdAndTypeCommissionId(avisDacId, typeCommissId);
        List<MembreCommissionDTO> membreCommissionDTOS1 =  membreCommissions.stream().filter(membreCommission ->
                !membreCommission.getTache().getId().equals(tache.getId()) ||
                    membreCommission.getTache() == null).map(membreCommissionMapper::toDto)
                .collect(Collectors.toList());

        List<MembreCommissionDTO> membreCommissionDTOS2 = membreCommissionRepository.findAllByDeletedIsFalseAndAvisDacIdAndTypeCommissionIdAndTache(avisDacId, typeCommissId, tache)
            .stream().map(membreCommissionMapper::toDto).collect(Collectors.toList());

        if (isAffected) {
            membreCommissionDTOS = membreCommissionDTOS1;
        } else {
            membreCommissionDTOS = membreCommissionDTOS2;
        }

        return membreCommissionDTOS;
    }

    public List<MembreCommissionDTO> getMembreCommissionByMemebre(Long membreId) {

        return membreCommissionMapper.toDto(membreCommissionRepository.findMembreCommissionByMembreIdAndDeletedIsFalse(membreId));
    }


}
