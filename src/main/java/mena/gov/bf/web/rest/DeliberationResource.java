package mena.gov.bf.web.rest;

import mena.gov.bf.service.DeliberationService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.DeliberationDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Deliberation}.
 */
@RestController
@RequestMapping("/api")
public class DeliberationResource {

    private final Logger log = LoggerFactory.getLogger(DeliberationResource.class);

    private static final String ENTITY_NAME = "microservicedaccamDeliberation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliberationService deliberationService;

    public DeliberationResource(DeliberationService deliberationService) {
        this.deliberationService = deliberationService;
    }

    /**
     * {@code POST  /deliberations} : Create a new deliberation.
     *
     * @param deliberationDTO the deliberationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliberationDTO, or with status {@code 400 (Bad Request)} if the deliberation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deliberations")
    public ResponseEntity<DeliberationDTO> createDeliberation(@Valid @RequestBody DeliberationDTO deliberationDTO) throws URISyntaxException {
        log.debug("REST request to save Deliberation : {}", deliberationDTO);
        if (deliberationDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliberation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliberationDTO result = deliberationService.save(deliberationDTO);
        return ResponseEntity.created(new URI("/api/deliberations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deliberations} : Updates an existing deliberation.
     *
     * @param deliberationDTO the deliberationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliberationDTO,
     * or with status {@code 400 (Bad Request)} if the deliberationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliberationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deliberations")
    public ResponseEntity<DeliberationDTO> updateDeliberation(@Valid @RequestBody DeliberationDTO deliberationDTO) throws URISyntaxException {
        log.debug("REST request to update Deliberation : {}", deliberationDTO);
        if (deliberationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliberationDTO result = deliberationService.save(deliberationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliberationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deliberations} : get all the deliberations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliberations in body.
     */
    @GetMapping("/deliberations")
    public ResponseEntity<List<DeliberationDTO>> getAllDeliberations(Pageable pageable, @RequestParam(name = "lotId") Long lotId) {
        log.debug("REST request to get a page of Deliberations");
        Page<DeliberationDTO> page = deliberationService.findAll(pageable, lotId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deliberations/:id} : get the "id" deliberation.
     *
     * @param id the id of the deliberationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliberationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deliberations/{id}")
    public ResponseEntity<DeliberationDTO> getDeliberation(@PathVariable Long id) {
        log.debug("REST request to get Deliberation : {}", id);
        Optional<DeliberationDTO> deliberationDTO = deliberationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliberationDTO);
    }

    /**
     * {@code DELETE  /deliberations/:id} : delete the "id" deliberation.
     *
     * @param id the id of the deliberationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deliberations/{id}")
    public ResponseEntity<Void> deleteDeliberation(@PathVariable Long id) {
        log.debug("REST request to delete Deliberation : {}", id);
        deliberationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/deliberations/deleteAllListe")
    public ResponseEntity<List<DeliberationDTO>> deleteAll(
        @RequestBody List<DeliberationDTO> deliberationDTOS) {
        return ResponseEntity.ok(deliberationService.deleteAllDeliberation(deliberationDTOS));
    }
    @PutMapping("/deliberations/active-deliberation")
    public ResponseEntity<DeliberationDTO> activateDeliberation(@Valid @RequestBody DeliberationDTO deliberationDTO) {
        DeliberationDTO result = deliberationService.activeDeliberation(deliberationDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/deliberations/active-deliberation-etatMarche")
    public ResponseEntity<DeliberationDTO> activateDeliberationetatMarche(@Valid @RequestBody DeliberationDTO deliberationDTO) {
        DeliberationDTO result = deliberationService.activeDeliberationEtatMarche(deliberationDTO);
        return ResponseEntity.ok().body(result);
    }
}

