package mena.gov.bf.web.rest;

import mena.gov.bf.service.WorkflowService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.WorkflowDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.Workflow}.
 */
@RestController
@RequestMapping("/api")
public class WorkflowResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowResource.class);

    private static final String ENTITY_NAME = "microservicedaccamWorkflow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkflowService workflowService;

    public WorkflowResource(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * {@code POST  /workflows} : Create a new workflow.
     *
     * @param workflowDTO the workflowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workflowDTO, or with status {@code 400 (Bad Request)} if the workflow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workflows")
    public ResponseEntity<WorkflowDTO> createWorkflow(@Valid @RequestBody WorkflowDTO workflowDTO) throws URISyntaxException {
        log.debug("REST request to save Workflow : {}", workflowDTO);
        if (workflowDTO.getId() != null) {
            throw new BadRequestAlertException("A new workflow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkflowDTO result = workflowService.save(workflowDTO);
        return ResponseEntity.created(new URI("/api/workflows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /workflows} : Updates an existing workflow.
     *
     * @param workflowDTO the workflowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowDTO,
     * or with status {@code 400 (Bad Request)} if the workflowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workflowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workflows")
    public ResponseEntity<WorkflowDTO> updateWorkflow(@Valid @RequestBody WorkflowDTO workflowDTO) throws URISyntaxException {
        log.debug("REST request to update Workflow : {}", workflowDTO);
        if (workflowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkflowDTO result = workflowService.save(workflowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workflowDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /workflows} : get all the workflows.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workflows in body.
     */
    @GetMapping("/workflows")
    public ResponseEntity<List<WorkflowDTO>> getAllWorkflows(Pageable pageable) {
        log.debug("REST request to get a page of Workflows");
        Page<WorkflowDTO> page = workflowService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workflows/:id} : get the "id" workflow.
     *
     * @param id the id of the workflowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workflowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workflows/{id}")
    public ResponseEntity<WorkflowDTO> getWorkflow(@PathVariable Long id) {
        log.debug("REST request to get Workflow : {}", id);
        Optional<WorkflowDTO> workflowDTO = workflowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workflowDTO);
    }

    /**
     * {@code DELETE  /workflows/:id} : delete the "id" workflow.
     *
     * @param id the id of the workflowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workflows/{id}")
    public ResponseEntity<Void> deleteWorkflow(@PathVariable Long id) {
        log.debug("REST request to delete Workflow : {}", id);
        workflowService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/workflow/updateListe")
    public ResponseEntity<List<WorkflowDTO>> updateAall(@RequestBody List<WorkflowDTO> workflowDTOS){
        return ResponseEntity.ok(workflowService.updateAall(workflowDTOS));
    }

}
