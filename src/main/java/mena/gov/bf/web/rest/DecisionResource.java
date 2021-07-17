package mena.gov.bf.web.rest;

import mena.gov.bf.service.CandidatLotService;
import mena.gov.bf.service.DecisionService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.DecisionDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Decision}.
 */
@RestController
@RequestMapping("/api")
public class DecisionResource {

    private final Logger log = LoggerFactory.getLogger(DecisionResource.class);

    private static final String ENTITY_NAME = "microservicedaccamDecision";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DecisionService decisionService;

    public DecisionResource(DecisionService decisionService, CandidatLotService candidatLotService) {
        this.decisionService = decisionService;
    }

    /**
     * {@code POST  /decisions} : Create a new decision.
     *
     * @param decisionDTO the decisionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new decisionDTO, or with status {@code 400 (Bad Request)} if the decision has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/decisions")
    public ResponseEntity<DecisionDTO> createDecision(@Valid @RequestBody DecisionDTO decisionDTO) throws URISyntaxException {
        log.debug("REST request to save Decision : {}", decisionDTO);
        if (decisionDTO.getId() != null) {
            throw new BadRequestAlertException("A new decision cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DecisionDTO result = decisionService.save(decisionDTO);
        return ResponseEntity.created(new URI("/api/decisions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /decisions} : Updates an existing decision.
     *
     * @param decisionDTO the decisionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated decisionDTO,
     * or with status {@code 400 (Bad Request)} if the decisionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the decisionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/decisions")
    public ResponseEntity<DecisionDTO> updateDecision(@Valid @RequestBody DecisionDTO decisionDTO) throws URISyntaxException {
        log.debug("REST request to update Decision : {}", decisionDTO);
        if (decisionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DecisionDTO result = decisionService.save(decisionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, decisionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /decisions} : get all the decisions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of decisions in body.
     */
    @GetMapping("/decisions")
    public ResponseEntity<List<DecisionDTO>> getAllDecisions(Pageable pageable) {
        log.debug("REST request to get a page of Decisions");
        Page<DecisionDTO> page = decisionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /decisions/:id} : get the "id" decision.
     *
     * @param id the id of the decisionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the decisionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/decisions/{id}")
    public ResponseEntity<DecisionDTO> getDecision(@PathVariable Long id) {
        log.debug("REST request to get Decision : {}", id);
        Optional<DecisionDTO> decisionDTO = decisionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(decisionDTO);
    }

    /**
     * {@code DELETE  /decisions/:id} : delete the "id" decision.
     *
     * @param id the id of the decisionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/decisions/{id}")
    public ResponseEntity<Void> deleteDecision(@PathVariable Long id) {
        log.debug("REST request to delete Decision : {}", id);
        decisionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * get all decisions by reclamation.
     * @param reclamationId
     * @return {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @GetMapping("/decisions/all-decisions-by-reclamation")
    public ResponseEntity<List<DecisionDTO>> allDecisionsByReclamation (Long reclamationId) {
        return ResponseEntity.ok(  ).body( decisionService.findAllDecisionByReclamation(reclamationId));
    }

}
