package mena.gov.bf.web.rest;

import mena.gov.bf.service.TacheWorkflowService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.TacheWorkflowDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.TacheWorkflow}.
 */
@RestController
@RequestMapping("/api")
public class TacheWorkflowResource {

    private final Logger log = LoggerFactory.getLogger(TacheWorkflowResource.class);

    private static final String ENTITY_NAME = "microservicedaccamTacheWorkflow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TacheWorkflowService tacheWorkflowService;

    public TacheWorkflowResource(TacheWorkflowService tacheWorkflowService) {
        this.tacheWorkflowService = tacheWorkflowService;
    }

    /**
     * {@code POST  /tache-workflows} : Create a new tacheWorkflow.
     *
     * @param tacheWorkflowDTO the tacheWorkflowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tacheWorkflowDTO, or with status {@code 400 (Bad Request)} if the tacheWorkflow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tache-workflows")
    public ResponseEntity<TacheWorkflowDTO> createTacheWorkflow(@Valid @RequestBody TacheWorkflowDTO tacheWorkflowDTO) throws URISyntaxException {
        log.debug("REST request to save TacheWorkflow : {}", tacheWorkflowDTO);
        if (tacheWorkflowDTO.getId() != null) {
            throw new BadRequestAlertException("A new tacheWorkflow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TacheWorkflowDTO result = tacheWorkflowService.save(tacheWorkflowDTO);
        return ResponseEntity.created(new URI("/api/tache-workflows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tache-workflows} : Updates an existing tacheWorkflow.
     *
     * @param tacheWorkflowDTO the tacheWorkflowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tacheWorkflowDTO,
     * or with status {@code 400 (Bad Request)} if the tacheWorkflowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tacheWorkflowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tache-workflows")
    public ResponseEntity<TacheWorkflowDTO> updateTacheWorkflow(@Valid @RequestBody TacheWorkflowDTO tacheWorkflowDTO) throws URISyntaxException {
        log.debug("REST request to update TacheWorkflow : {}", tacheWorkflowDTO);
        if (tacheWorkflowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TacheWorkflowDTO result = tacheWorkflowService.save(tacheWorkflowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tacheWorkflowDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tache-workflows} : get all the tacheWorkflows.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tacheWorkflows in body.
     */
    @GetMapping("/tache-workflows")
    public ResponseEntity<List<TacheWorkflowDTO>> getAllTacheWorkflows(Pageable pageable) {
        log.debug("REST request to get a page of TacheWorkflows");
        Page<TacheWorkflowDTO> page = tacheWorkflowService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tache-workflows/:id} : get the "id" tacheWorkflow.
     *
     * @param id the id of the tacheWorkflowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tacheWorkflowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tache-workflows/{id}")
    public ResponseEntity<TacheWorkflowDTO> getTacheWorkflow(@PathVariable Long id) {
        log.debug("REST request to get TacheWorkflow : {}", id);
        Optional<TacheWorkflowDTO> tacheWorkflowDTO = tacheWorkflowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tacheWorkflowDTO);
    }

    /**
     * {@code DELETE  /tache-workflows/:id} : delete the "id" tacheWorkflow.
     *
     * @param id the id of the tacheWorkflowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tache-workflows/{id}")
    public ResponseEntity<Void> deleteTacheWorkflow(@PathVariable Long id) {
        log.debug("REST request to delete TacheWorkflow : {}", id);
        tacheWorkflowService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
