package mena.gov.bf.web.rest;

import mena.gov.bf.service.TacheEtapeService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.TacheEtapeDTO;

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

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.TacheEtape}.
 */
@RestController
@RequestMapping("/api")
public class TacheEtapeResource {

    private final Logger log = LoggerFactory.getLogger(TacheEtapeResource.class);

    private static final String ENTITY_NAME = "microservicedaccamTacheEtape";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TacheEtapeService tacheEtapeService;

    public TacheEtapeResource(TacheEtapeService tacheEtapeService) {
        this.tacheEtapeService = tacheEtapeService;
    }

    /**
     * {@code POST  /tache-etapes} : Create a new tacheEtape.
     *
     * @param tacheEtapeDTO the tacheEtapeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tacheEtapeDTO, or with status {@code 400 (Bad Request)} if the tacheEtape has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tache-etapes")
    public ResponseEntity<TacheEtapeDTO> createTacheEtape(@Valid @RequestBody TacheEtapeDTO tacheEtapeDTO) throws URISyntaxException {
        log.debug("REST request to save TacheEtape : {}", tacheEtapeDTO);
        if (tacheEtapeDTO.getId() != null) {
            throw new BadRequestAlertException("A new tacheEtape cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TacheEtapeDTO result = tacheEtapeService.save(tacheEtapeDTO);
        return ResponseEntity.created(new URI("/api/tache-etapes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tache-etapes} : Updates an existing tacheEtape.
     *
     * @param tacheEtapeDTO the tacheEtapeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tacheEtapeDTO,
     * or with status {@code 400 (Bad Request)} if the tacheEtapeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tacheEtapeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tache-etapes")
    public ResponseEntity<TacheEtapeDTO> updateTacheEtape(@Valid @RequestBody TacheEtapeDTO tacheEtapeDTO) throws URISyntaxException {
        log.debug("REST request to update TacheEtape : {}", tacheEtapeDTO);
        if (tacheEtapeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TacheEtapeDTO result = tacheEtapeService.save(tacheEtapeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tacheEtapeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tache-etapes} : get all the tacheEtapes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tacheEtapes in body.
     */
    @GetMapping("/tache-etapes")
    public ResponseEntity<List<TacheEtapeDTO>> getAllTacheEtapes(Pageable pageable) {
        log.debug("REST request to get a page of TacheEtapes");
        Page<TacheEtapeDTO> page = tacheEtapeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tache-etapes/:id} : get the "id" tacheEtape.
     *
     * @param id the id of the tacheEtapeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tacheEtapeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tache-etapes/{id}")
    public ResponseEntity<TacheEtapeDTO> getTacheEtape(@PathVariable Long id) {
        log.debug("REST request to get TacheEtape : {}", id);
        Optional<TacheEtapeDTO> tacheEtapeDTO = tacheEtapeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tacheEtapeDTO);
    }

    /**
     * {@code DELETE  /tache-etapes/:id} : delete the "id" tacheEtape.
     *
     * @param id the id of the tacheEtapeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tache-etapes/{id}")
    public ResponseEntity<Void> deleteTacheEtape(@PathVariable Long id) {
        log.debug("REST request to delete TacheEtape : {}", id);
        tacheEtapeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("tache-etapes/find-all")
    public ResponseEntity<List<TacheEtapeDTO>> findAllWithoutPage() {
        return ResponseEntity.ok(tacheEtapeService.filnAllWithoutPage());
    }

    @GetMapping("/tache-etapes/all-by-tache")
    public ResponseEntity<List<TacheEtapeDTO>> findTacheEtapeByTache(@RequestParam Long tacheId) {
        return ResponseEntity.ok(tacheEtapeService.findTacheEtapeByTache(tacheId));
    }

}
