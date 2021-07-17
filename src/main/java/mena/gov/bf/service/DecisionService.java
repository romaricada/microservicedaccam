package mena.gov.bf.service;

import mena.gov.bf.domain.Decision;
import mena.gov.bf.repository.DecisionRepository;
import mena.gov.bf.service.dto.DecisionDTO;
import mena.gov.bf.service.mapper.DecisionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Decision}.
 */
@Service
@Transactional
public class DecisionService {

    private final Logger log = LoggerFactory.getLogger(DecisionService.class);
    private final DecisionRepository decisionRepository;

    private final DecisionMapper decisionMapper;

    public DecisionService(DecisionRepository decisionRepository, DecisionMapper decisionMapper) {
        this.decisionRepository = decisionRepository;
        this.decisionMapper = decisionMapper;
    }

    /**
     * Save a decision.
     *
     * @param decisionDTO the entity to save.
     * @return the persisted entity.
     */
    public DecisionDTO save(DecisionDTO decisionDTO) {
        log.debug("Request to save Decision : {}", decisionDTO);
        Decision decision = decisionMapper.toEntity(decisionDTO);
        decision = decisionRepository.save(decision);
        return decisionMapper.toDto(decision);
    }

    /**
     * Get all the decisions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DecisionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Decisions");
        return decisionRepository.findAll(pageable)
            .map(decisionMapper::toDto);
    }


    /**
     * Get one decision by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DecisionDTO> findOne(Long id) {
        log.debug("Request to get Decision : {}", id);
        return decisionRepository.findById(id)
            .map(decisionMapper::toDto);
    }

    /**
     * Delete the decision by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Decision : {}", id);
        decisionRepository.deleteById(id);
    }

    public List<DecisionDTO> findAllDecisionByReclamation (Long reclamationId) {
        return decisionRepository.findAllByDeletedIsFalse().stream().filter(decision -> decision.getReclamation() != null
        && decision.getReclamation().getId().equals(reclamationId)).map(decisionMapper::toDto).collect(Collectors.toList());
    }


}
