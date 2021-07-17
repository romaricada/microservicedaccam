package mena.gov.bf.web.rest;

import mena.gov.bf.service.CandidatService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.CandidatDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Candidat}.
 */
@RestController
@RequestMapping("/api")
public class CandidatResource {

    private final Logger log = LoggerFactory.getLogger(CandidatResource.class);

    private static final String ENTITY_NAME = "microservicedaccamCandidat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidatService candidatService;

    public CandidatResource(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    /**
     * {@code POST  /candidats} : Create a new candidat.
     *
     * @param candidatDTO the candidatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidatDTO, or with status {@code 400 (Bad Request)} if the candidat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/candidats")
    public ResponseEntity<CandidatDTO> createCandidat(@Valid @RequestBody CandidatDTO candidatDTO) throws URISyntaxException {
        log.debug("REST request to save Candidat : {}", candidatDTO);
        if (candidatDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CandidatDTO result = candidatService.save(candidatDTO);
        return ResponseEntity.created(new URI("/api/candidats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /candidats} : Updates an existing candidat.
     *
     * @param candidatDTO the candidatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidatDTO,
     * or with status {@code 400 (Bad Request)} if the candidatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/candidats")
    public ResponseEntity<CandidatDTO> updateCandidat(@Valid @RequestBody CandidatDTO candidatDTO) throws URISyntaxException {
        log.debug("REST request to update Candidat : {}", candidatDTO);
        if (candidatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CandidatDTO result = candidatService.save(candidatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, candidatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /candidats} : get all the candidats.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidats in body.
     */
    @GetMapping("/candidats/all")
    public ResponseEntity<List<CandidatDTO>> getAllCandidats(Pageable pageable) {
        log.debug("REST request to get a page of Candidats");
        Page<CandidatDTO> page = candidatService.findAllCandidat(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/candidats")
    public ResponseEntity<List<CandidatDTO>> getAllCandidats( @RequestParam(name = "activiteId") Long activiteId, Pageable pageable) {
        log.debug("REST request to get a page of Depouillements");
        Page<CandidatDTO> page = candidatService.findAll(pageable, activiteId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    // obtenir la liste des avis dac par id
    @GetMapping("/candidats/avidac")
    public ResponseEntity<List<CandidatDTO>> getAllCandidatsAvisDac( @RequestParam(name = "avisDacId") Long avisDacId, Pageable pageable) {
        log.debug("REST request to get a page of candidat ");
        Page<CandidatDTO> page = candidatService.findAllByAvisDacId(pageable, avisDacId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /candidats/:id} : get the "id" candidat.
     *
     * @param id the id of the candidatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidats/{id}")
    public ResponseEntity<CandidatDTO> getCandidat(@PathVariable Long id) {
        log.debug("REST request to get Candidat : {}", id);
        Optional<CandidatDTO> candidatDTO = candidatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(candidatDTO);
    }

    /**
     * {@code DELETE  /candidats/:id} : delete the "id" candidat.
     *
     * @param id the id of the candidatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidats/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable Long id) {
        log.debug("REST request to delete Candidat : {}", id);
        candidatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/candidats/updateListe")
    public ResponseEntity<List<CandidatDTO>> updateaAll(@RequestBody List<CandidatDTO> candidatDTOS1){
        return ResponseEntity.ok(candidatService.updateaAll(candidatDTOS1));
    }

    @GetMapping("/candidats/all-by-avis-dac")
    public ResponseEntity<List<CandidatDTO>> findAllByAvisDac(@RequestParam(name = "avisDacId") Long avisDacId) {
        return ResponseEntity.ok().body( candidatService.findAllCandidatByAvisDac( avisDacId ) );
    }

    @GetMapping("/candidats/all-show-by-avis-dac")
    public ResponseEntity<List<CandidatDTO>> findCandidatbyAvisdac(@RequestParam Long avisdacId) {
        return ResponseEntity.ok().body( candidatService.findAllbyAvisDacShow(avisdacId) );
    }

    /*@GetMapping("/candidats/all-with-lot")
    public ResponseEntity<List<CandidatDTO>> findAllCandidat( @RequestParam(name = "activiteId") Long activiteId) {
        return ResponseEntity.ok().body( candidatService.findAllCandidat(activiteId));
    }*/
}

