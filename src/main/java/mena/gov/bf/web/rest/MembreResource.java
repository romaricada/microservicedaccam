package mena.gov.bf.web.rest;

import mena.gov.bf.service.MembreService;
import mena.gov.bf.service.dto.WorkflowDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.MembreDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Membre}.
 */
@RestController
@RequestMapping("/api")
public class MembreResource {

    private final Logger log = LoggerFactory.getLogger(MembreResource.class);

    private static final String ENTITY_NAME = "microservicedaccamMembre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MembreService membreService;

    public MembreResource(MembreService membreService) {
        this.membreService = membreService;
    }

    /**
     * {@code POST  /membres} : Create a new membre.
     *
     * @param membreDTO the membreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new membreDTO, or with status {@code 400 (Bad Request)} if the membre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/membres")
    public ResponseEntity<MembreDTO> createMembre(@Valid @RequestBody MembreDTO membreDTO) throws URISyntaxException {
        log.debug("REST request to save Membre : {}", membreDTO);
        if (membreDTO.getId() != null) {
            throw new BadRequestAlertException("A new membre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MembreDTO result = membreService.save(membreDTO);
        return ResponseEntity.created(new URI("/api/membres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /membres} : Updates an existing membre.
     *
     * @param membreDTO the membreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membreDTO,
     * or with status {@code 400 (Bad Request)} if the membreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the membreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/membres")
    public ResponseEntity<MembreDTO> updateMembre(@Valid @RequestBody MembreDTO membreDTO) throws URISyntaxException {
        log.debug("REST request to update Membre : {}", membreDTO);
        if (membreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MembreDTO result = membreService.save(membreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, membreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /membres} : get all the membres.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of membres in body.
     */
    @GetMapping("/membres")
    public ResponseEntity<List<MembreDTO>> getAllMembres(Pageable pageable) {
        log.debug("REST request to get a page of Membres");
        Page<MembreDTO> page = membreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /membres/:id} : get the "id" membre.
     *
     * @param id the id of the membreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the membreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/membres/{id}")
    public ResponseEntity<MembreDTO> getMembre(@PathVariable Long id) {
        log.debug("REST request to get Membre : {}", id);
        Optional<MembreDTO> membreDTO = membreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(membreDTO);
    }

    /**
     * {@code DELETE  /membres/:id} : delete the "id" membre.
     *
     * @param id the id of the membreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/membres/{id}")
    public ResponseEntity<Void> deleteMembre(@PathVariable Long id) {
        log.debug("REST request to delete Membre : {}", id);
        membreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/membres/updateListe")
    public ResponseEntity<List<MembreDTO>> updateAall(@RequestBody List<MembreDTO> membreDTOS){
        return ResponseEntity.ok(membreService.updateAall(membreDTOS));
    }
    @GetMapping("/membres/membre-by-unite")
    public ResponseEntity<List<MembreDTO>> findAllByUnite(@RequestParam Long uniteId){
        return ResponseEntity.ok(membreService.findMembreByUnite(uniteId));
    }

    @GetMapping("/membres/membre-by-type-commission")
    public ResponseEntity<List<MembreDTO>> findListMembreByTypeCommission(@RequestParam Long typeCommissionId){
        return ResponseEntity.ok(membreService.findMembreByTypeCommission(typeCommissionId));
    }

    @GetMapping("/membres/find-all")
    public ResponseEntity<List<MembreDTO>> findAllWithoutPage() {
        return ResponseEntity.ok(membreService.findAllMembreWithoutPage());
    }

}
