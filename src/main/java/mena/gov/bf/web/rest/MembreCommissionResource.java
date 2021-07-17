package mena.gov.bf.web.rest;

import mena.gov.bf.domain.Membre;
import mena.gov.bf.service.MembreCommissionService;
import mena.gov.bf.service.dto.MembreDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.MembreCommissionDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.MembreCommission}.
 */
@RestController
@RequestMapping("/api")
public class MembreCommissionResource {

    private final Logger log = LoggerFactory.getLogger(MembreCommissionResource.class);

    private static final String ENTITY_NAME = "microservicedaccamMembreCommission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MembreCommissionService membreCommissionService;

    public MembreCommissionResource(MembreCommissionService membreCommissionService) {
        this.membreCommissionService = membreCommissionService;
    }

    /**
     * {@code POST  /membre-commissions} : Create a new membreCommission.
     *
     * @param membreCommissionDTO the membreCommissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new membreCommissionDTO, or with status {@code 400 (Bad Request)} if the membreCommission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/membre-commissions")
    public ResponseEntity<MembreCommissionDTO> createMembreCommission(@Valid @RequestBody MembreCommissionDTO membreCommissionDTO) throws URISyntaxException {
        log.debug("REST request to save MembreCommission : {}", membreCommissionDTO);
        if (membreCommissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new membreCommission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MembreCommissionDTO result = membreCommissionService.save(membreCommissionDTO);
        return ResponseEntity.created(new URI("/api/membre-commissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /membre-commissions} : Updates an existing membreCommission.
     *
     * @param membreCommissionDTO the membreCommissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membreCommissionDTO,
     * or with status {@code 400 (Bad Request)} if the membreCommissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the membreCommissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/membre-commissions")
    public ResponseEntity<MembreCommissionDTO> updateMembreCommission(@Valid @RequestBody MembreCommissionDTO membreCommissionDTO) throws URISyntaxException {
        log.debug("REST request to update MembreCommission : {}", membreCommissionDTO);
        if (membreCommissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MembreCommissionDTO result = membreCommissionService.save(membreCommissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, membreCommissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /membre-commissions} : get all the membreCommissions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of membreCommissions in body.
     */
    @GetMapping("/membre-commissions")
    public ResponseEntity<List<MembreCommissionDTO>> getAllMembreCommissions(Pageable pageable) {
        log.debug("REST request to get a page of MembreCommissions");
        Page<MembreCommissionDTO> page = membreCommissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /membre-commissions/:id} : get the "id" membreCommission.
     *
     * @param id the id of the membreCommissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the membreCommissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/membre-commissions/{id}")
    public ResponseEntity<MembreCommissionDTO> getMembreCommission(@PathVariable Long id) {
        log.debug("REST request to get MembreCommission : {}", id);
        Optional<MembreCommissionDTO> membreCommissionDTO = membreCommissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(membreCommissionDTO);
    }

    /**
     * {@code DELETE  /membre-commissions/:id} : delete the "id" membreCommission.
     *
     * @param id the id of the membreCommissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/membre-commissions/{id}")
    public ResponseEntity<Void> deleteMembreCommission(@PathVariable Long id) {
        log.debug("REST request to delete MembreCommission : {}", id);
        membreCommissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/membre-commissions/delete-all")
    public ResponseEntity<List<MembreCommissionDTO>> updateAall(
        @RequestBody List<MembreCommissionDTO> membreCommissionDTOS) {
        return ResponseEntity.ok(membreCommissionService.updateAall(membreCommissionDTOS));
    }


    @GetMapping("/membre-commissions/all-by-type")
    public ResponseEntity<List<MembreDTO>> getMembreByTypeCommission(@RequestParam Long typeCommissionId) {
        return ResponseEntity.ok(membreCommissionService.findMembreByTypeCommission(typeCommissionId));
    }




    @GetMapping("/membre-commissions/find-by-activite-and-type-com")
    public ResponseEntity<List<MembreCommissionDTO>> findAllByAvisDac(
        @RequestParam Long activiteId, @RequestParam Long typeCommissId) {
        return ResponseEntity.ok(membreCommissionService.findAllByAvisDac(activiteId, typeCommissId));
    }

    @GetMapping("/membre-commissions/find-by-avis-dac-and-type-com")
    public ResponseEntity<List<MembreCommissionDTO>> findAllByAvisDacAndTypecommission(
        @RequestParam Long avisDacId, @RequestParam Long typeCommissId) {
        return ResponseEntity.ok(membreCommissionService.findAllByAvisDac(avisDacId, typeCommissId));
    }

    /**
     *
     * @param activiteId
     * @param typeCommiId
     * @param tacheId
     * @param isAffected
     * @return
     */
    @GetMapping("/membre-commissions/affected_or_note_affected")
    public ResponseEntity<List<MembreCommissionDTO>> membreAffectedOrnoteAffectedToTache(
        @RequestParam Long activiteId,
        @RequestParam Long typeCommiId,
        @RequestParam Long tacheId,
        @RequestParam Boolean isAffected
    ) {
        return ResponseEntity.ok(membreCommissionService.membreCommissAffectedToTache(activiteId, typeCommiId, tacheId, isAffected));
    }

    @GetMapping("/membre-commissions/membreid")
    public ResponseEntity<List<MembreCommissionDTO>> getMembreCommissionByMembre(@RequestParam(name = "membreId") Long membreId) {
        return ResponseEntity.ok(membreCommissionService.getMembreCommissionByMemebre(membreId));
    }
}
