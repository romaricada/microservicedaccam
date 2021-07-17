package mena.gov.bf.web.rest;

import mena.gov.bf.domain.TacheEtape;
import mena.gov.bf.domain.enumeration.TypeTache;
import mena.gov.bf.service.TacheService;
import mena.gov.bf.service.dto.MembreCommissionDTO;
import mena.gov.bf.service.dto.TacheEtapeDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.TacheDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.hibernate.annotations.Parameter;
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
 * REST controller for managing {@link mena.gov.bf.domain.Tache}.
 */
@RestController
@RequestMapping("/api")
public class TacheResource {

    private final Logger log = LoggerFactory.getLogger(TacheResource.class);

    private static final String ENTITY_NAME = "microservicedaccamTache";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TacheService tacheService;

    public TacheResource(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    /**
     * {@code POST  /taches} : Create a new tache.
     *
     * @param tacheDTO the tacheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tacheDTO, or with status {@code 400 (Bad Request)} if the tache has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/taches")
    public ResponseEntity<TacheDTO> createTache(@Valid @RequestBody TacheDTO tacheDTO) throws URISyntaxException {
        log.debug("REST request to save Tache : {}", tacheDTO);
        if (tacheDTO.getId() != null) {
            throw new BadRequestAlertException("A new tache cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TacheDTO result = tacheService.createTache(tacheDTO);
        return ResponseEntity.created(new URI("/api/taches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /taches} : Updates an existing tache.
     *
     * @param tacheDTO the tacheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tacheDTO,
     * or with status {@code 400 (Bad Request)} if the tacheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tacheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    /*****************/
    @PutMapping("/taches")
    public ResponseEntity<TacheDTO> updateTache(@Valid @RequestBody TacheDTO tacheDTO) throws URISyntaxException {
        log.debug("REST request to update Tache : {}", tacheDTO);
        if (tacheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TacheDTO result = tacheService.createTache(tacheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tacheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /taches} : get all the taches.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taches in body.
     */
    @GetMapping("/taches")
    public ResponseEntity<List<TacheDTO>> getAllTaches(Pageable pageable) {
        log.debug("REST request to get a page of Taches");
        Page<TacheDTO> page = tacheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /taches/:id} : get the "id" tache.
     *
     * @param id the id of the tacheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tacheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/taches/{id}")
    public ResponseEntity<TacheDTO> getTache(@PathVariable Long id) {
        log.debug("REST request to get Tache : {}", id);
        Optional<TacheDTO> tacheDTO = tacheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tacheDTO);
    }

    /**
     * {@code DELETE  /taches/:id} : delete the "id" tache.
     *
     * @param id the id of the tacheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/taches/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        log.debug("REST request to delete Tache : {}", id);
        tacheService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     *
     * @param membreCommissions
     * @param action
     * @param tacheId
     * @return
     */
    @PutMapping("/taches/add_or_remove_membre")
    public ResponseEntity<TacheDTO> addOrRemoveMembreFromTache (
        @RequestBody List<MembreCommissionDTO> membreCommissions,
        @RequestParam Boolean action, @RequestParam Long tacheId) {
        TacheDTO result = tacheService.addOrRemoveMembreCommissFromTache(membreCommissions, action, tacheId);
        return ResponseEntity.ok(result);
    }

    /**
     *
     * @param objectId
     * @return
     */
    @GetMapping("/taches/find-by-objectId")
    public ResponseEntity<Optional<TacheDTO>> findByObject(@RequestParam String objectId) {
        return ResponseEntity.ok(tacheService.findTacheByObjectId(objectId));
    }

    /*@GetMapping("/taches/find-by-objectId-and-type-tache")
    public ResponseEntity<List<TacheDTO>> findAllByObjectIdAndTypeTache(@RequestParam String objectId, TypeTache typeTache) {
        return ResponseEntity.ok(tacheService.findAllByObjectIdAndTypeTache(objectId, typeTache));
    }*/

    @GetMapping("/taches/find-all")
    public ResponseEntity<List<TacheDTO>> findAlltache() {
        return ResponseEntity.ok().body(tacheService.findAlltache());
    }

    /**
     *
     * @param id
     * @param etat
     * @param crieteria
     * @return
     */
    @GetMapping("/taches/find-tache-by-criteria")
    public ResponseEntity<List<TacheDTO>> findByCriteria(@RequestParam Long id, @RequestParam String etat, @RequestParam String crieteria) {
        List<TacheDTO> result = tacheService.findTacheByCriteria(id, etat, crieteria);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/taches/calcule-date-fin-delai")
    public ResponseEntity<TacheEtapeDTO> calculateDateByDelai(@RequestBody TacheEtapeDTO tacheEtape) {
        return ResponseEntity.ok(tacheService.getDatefinByDelait(tacheEtape));
    }
    @PutMapping("/taches/updateListe")
    public ResponseEntity<List<TacheDTO>> updateAall(@RequestBody List<TacheDTO> tacheDTOS) {
        return ResponseEntity.ok(tacheService.updateAll(tacheDTOS));
    }
}

