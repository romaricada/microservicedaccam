package mena.gov.bf.web.rest;

import mena.gov.bf.service.ReclamationService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.ReclamationDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Reclamation}.
 */
@RestController
@RequestMapping("/api")
public class ReclamationResource {

    private final Logger log = LoggerFactory.getLogger(ReclamationResource.class);

    private static final String ENTITY_NAME = "microservicedaccamReclamation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReclamationService reclamationService;

    public ReclamationResource(ReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

    /**
     * {@code POST  /reclamations} : Create a new reclamation.
     *
     * @param reclamationDTO the reclamationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reclamationDTO, or with status {@code 400 (Bad Request)} if the reclamation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reclamations")
    public ResponseEntity<ReclamationDTO> createReclamation(@Valid @RequestBody ReclamationDTO reclamationDTO) throws URISyntaxException {
        log.debug("REST request to save Reclamation : {}", reclamationDTO);
        if (reclamationDTO.getId() != null) {
            throw new BadRequestAlertException("A new reclamation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReclamationDTO result = reclamationService.save(reclamationDTO);
        return ResponseEntity.created(new URI("/api/reclamations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reclamations} : Updates an existing reclamation.
     *
     * @param reclamationDTO the reclamationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reclamationDTO,
     * or with status {@code 400 (Bad Request)} if the reclamationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reclamationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reclamations")
    public ResponseEntity<ReclamationDTO> updateReclamation(@Valid @RequestBody ReclamationDTO reclamationDTO) throws URISyntaxException {
        log.debug("REST request to update Reclamation : {}", reclamationDTO);
        if (reclamationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReclamationDTO result = reclamationService.save(reclamationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reclamationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reclamations} : get all the reclamations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reclamations in body.
     */
    @GetMapping("/reclamations")
    public ResponseEntity<List<ReclamationDTO>> getAllReclamations(Pageable pageable) {
        log.debug("REST request to get a page of Reclamations");
        Page<ReclamationDTO> page = reclamationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reclamations/:id} : get the "id" reclamation.
     *
     * @param id the id of the reclamationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reclamationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reclamations/{id}")
    public ResponseEntity<ReclamationDTO> getReclamation(@PathVariable Long id) {
        log.debug("REST request to get Reclamation : {}", id);
        Optional<ReclamationDTO> reclamationDTO = reclamationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reclamationDTO);
    }

    /**
     * {@code DELETE  /reclamations/:id} : delete the "id" reclamation.
     *
     * @param id the id of the reclamationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reclamations/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        log.debug("REST request to delete Reclamation : {}", id);
        reclamationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/reclamations/all-by-lot")
    public ResponseEntity<List<ReclamationDTO>> findReclamationByLot(@RequestParam Long lotId) {
        return ResponseEntity.ok().body(reclamationService.findReclamationByLot(lotId));
    }

    @GetMapping("/reclamations/all-by-avis-dac")
    public ResponseEntity<List<ReclamationDTO>> findReclamationByavisDac(@RequestParam Long avisDacId) {
        return ResponseEntity.ok().body(reclamationService.findReclamationByavisDac(avisDacId));
    }

    @PutMapping("/reclamations/updateListe")
    public ResponseEntity<List<ReclamationDTO>> updateAall(@RequestBody List<ReclamationDTO> reclamationDTOS1) {
        return ResponseEntity.ok(reclamationService.updateAall(reclamationDTOS1));
    }
}
