package mena.gov.bf.service;

import mena.gov.bf.domain.TacheWorkflow;
import mena.gov.bf.repository.TacheWorkflowRepository;
import mena.gov.bf.service.dto.TacheWorkflowDTO;
import mena.gov.bf.service.mapper.TacheWorkflowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * service Implementation for managing {@link TacheWorkflow}.
 */
@Service
@Transactional
public class TacheWorkflowService {

    private final Logger log = LoggerFactory.getLogger(TacheWorkflowService.class);

    private final TacheWorkflowRepository tacheWorkflowRepository;

    private final TacheWorkflowMapper tacheWorkflowMapper;

    public TacheWorkflowService(TacheWorkflowRepository tacheWorkflowRepository, TacheWorkflowMapper tacheWorkflowMapper) {
        this.tacheWorkflowRepository = tacheWorkflowRepository;
        this.tacheWorkflowMapper = tacheWorkflowMapper;
    }

    /**
     * Save a tacheWorkflow.
     *
     * @param tacheWorkflowDTO the entity to save.
     * @return the persisted entity.
     */
    public TacheWorkflowDTO save(TacheWorkflowDTO tacheWorkflowDTO) {
        log.debug("Request to save TacheWorkflow : {}", tacheWorkflowDTO);
        TacheWorkflow tacheWorkflow = tacheWorkflowMapper.toEntity(tacheWorkflowDTO);
        tacheWorkflow = tacheWorkflowRepository.save(tacheWorkflow);
        return tacheWorkflowMapper.toDto(tacheWorkflow);
    }

    /**
     * Get all the tacheWorkflows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TacheWorkflowDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TacheWorkflows");
        return tacheWorkflowRepository.findAll(pageable)
            .map(tacheWorkflowMapper::toDto);
    }


    /**
     * Get one tacheWorkflow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TacheWorkflowDTO> findOne(Long id) {
        log.debug("Request to get TacheWorkflow : {}", id);
        return tacheWorkflowRepository.findById(id)
            .map(tacheWorkflowMapper::toDto);
    }

    /**
     * Delete the tacheWorkflow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TacheWorkflow : {}", id);
        tacheWorkflowRepository.deleteById(id);
    }

}
