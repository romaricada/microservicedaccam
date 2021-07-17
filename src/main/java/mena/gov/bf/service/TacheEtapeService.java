package mena.gov.bf.service;

import mena.gov.bf.domain.TacheEtape;
import mena.gov.bf.repository.TacheEtapeRepository;
import mena.gov.bf.service.dto.TacheEtapeDTO;
import mena.gov.bf.service.mapper.TacheEtapeMapper;
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
 * service Implementation for managing {@link TacheEtape}.
 */
@Service
@Transactional
public class TacheEtapeService {

    private final Logger log = LoggerFactory.getLogger(TacheEtapeService.class);

    private final TacheEtapeRepository tacheEtapeRepository;

    private final TacheEtapeMapper tacheEtapeMapper;

    public TacheEtapeService(TacheEtapeRepository tacheEtapeRepository, TacheEtapeMapper tacheEtapeMapper) {
        this.tacheEtapeRepository = tacheEtapeRepository;
        this.tacheEtapeMapper = tacheEtapeMapper;
    }

    /**
     * Save a tacheEtape.
     *
     * @param tacheEtapeDTO the entity to save.
     * @return the persisted entity.
     */
    public TacheEtapeDTO save(TacheEtapeDTO tacheEtapeDTO) {
        log.debug("Request to save TacheEtape : {}", tacheEtapeDTO);
        TacheEtape tacheEtape = tacheEtapeMapper.toEntity(tacheEtapeDTO);
        tacheEtape = tacheEtapeRepository.save(tacheEtape);
        return tacheEtapeMapper.toDto(tacheEtape);
    }

    /**
     * Get all the tacheEtapes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TacheEtapeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TacheEtapes");
        return tacheEtapeRepository.findAll(pageable)
            .map(tacheEtapeMapper::toDto);
    }


    /**
     * Get one tacheEtape by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TacheEtapeDTO> findOne(Long id) {
        log.debug("Request to get TacheEtape : {}", id);
        return tacheEtapeRepository.findById(id)
            .map(tacheEtapeMapper::toDto);
    }

    /**
     * Delete the tacheEtape by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TacheEtape : {}", id);
        tacheEtapeRepository.deleteById(id);
    }

    public List<TacheEtapeDTO> filnAllWithoutPage() {
        return tacheEtapeRepository.findAllByDeletedIsFalse().stream().map(tacheEtapeMapper::toDto).collect(Collectors.toList());
    }
    public List<TacheEtapeDTO> findTacheEtapeByTache (Long tacheId) {
        return tacheEtapeRepository.findAll().stream().filter(tacheEtape -> tacheEtape.getTache().getId() != null && tacheEtape.getTache().getId().equals(tacheId))
            .map(tacheEtapeMapper::toDto).collect(Collectors.toList());
    }
}
