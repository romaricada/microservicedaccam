package mena.gov.bf.service;

import mena.gov.bf.domain.TacheWorkflow;
import mena.gov.bf.domain.Workflow;
import mena.gov.bf.repository.TacheWorkflowRepository;
import mena.gov.bf.repository.WorkflowRepository;
import mena.gov.bf.service.dto.WorkflowDTO;
import mena.gov.bf.service.mapper.WorkflowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Workflow}.
 */
@Service
@Transactional
public class WorkflowService {

    private final Logger log = LoggerFactory.getLogger(WorkflowService.class);

    private final WorkflowRepository workflowRepository;

    private final TacheWorkflowRepository tacheWorkflowRepository;

    private final WorkflowMapper workflowMapper;

    public WorkflowService(WorkflowRepository workflowRepository, WorkflowMapper workflowMapper, TacheWorkflowRepository tacheWorkflowRepository) {
        this.workflowRepository = workflowRepository;
        this.workflowMapper = workflowMapper;
        this.tacheWorkflowRepository=tacheWorkflowRepository;
    }

    /**
     * Save a workflow.
     *
     * @param workflowDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkflowDTO save(WorkflowDTO workflowDTO) {
        log.debug("Request to save Workflow : {}", workflowDTO);
        Workflow workflow = workflowMapper.toEntity(workflowDTO);
        workflow = workflowRepository.save(workflow);
        return workflowMapper.toDto(workflow);
    }

    /**
     * Get all the workflows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Workflows");
        List<WorkflowDTO> workflowDTOS = workflowRepository.findAll()
            .stream()
            .map(workflowMapper::toDto)
            .filter(workf -> workf.isDeleted() != null && !workf.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(workflowDTOS, pageable, workflowDTOS.size());
    }


    /**
     * Get one workflow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkflowDTO> findOne(Long id) {
        log.debug("Request to get Workflow : {}", id);
        return workflowRepository.findById(id)
            .map(workflowMapper::toDto);
    }

    /**
     * Delete the workflow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Workflow : {}", id);
        workflowRepository.deleteById(id);
    }
    public List<WorkflowDTO> updateAall(List<WorkflowDTO> workflowDTOS){
        workflowDTOS.forEach(workflowDTO -> {
            workflowDTO.setDeleted(true);
            List<TacheWorkflow> tacheWorkflows=tacheWorkflowRepository.findAll().stream().filter(tache->(tache.isDeleted()!=null && !tache.isDeleted()) && (tache.getWorkflow().getId()==workflowDTO.getId())).collect(Collectors.toList());
            System.out.println("=============="+tacheWorkflows.size()+"=====================");
            if(!tacheWorkflows.isEmpty()){
                tacheWorkflows.forEach(tacheToDelet->{
                    tacheToDelet.setDeleted(true);
                });
            }
        });

        workflowRepository.saveAll(workflowDTOS.stream().map(workflowMapper::toEntity).collect(Collectors.toList()));
        List<WorkflowDTO> workflowDTOS1=workflowRepository.findAll().stream().map(workflowMapper::toDto).filter(workflowDTO ->
            workflowDTO.isDeleted()!=null && !workflowDTO.isDeleted()).collect(Collectors.toList());
        return workflowDTOS;
    }
}
