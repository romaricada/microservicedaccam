package mena.gov.bf.web.rest;

import mena.gov.bf.service.InstitutionFinanciereService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.InstitutionFinanciereDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.InstitutionFinanciere}.
 */
@RestController
@RequestMapping("/api")
public class InstitutionFinanciereResource {

    private final Logger log = LoggerFactory.getLogger(InstitutionFinanciereResource.class);

    private static final String ENTITY_NAME = "microservicedaccamInstitutionFinanciere";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstitutionFinanciereService institutionFinanciereService;

    public InstitutionFinanciereResource(InstitutionFinanciereService institutionFinanciereService) {
        this.institutionFinanciereService = institutionFinanciereService;
    }

    /**
     * {@code POST  /institution-financieres} : Create a new institutionFinanciere.
     *
     * @param institutionFinanciereDTO the institutionFinanciereDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new institutionFinanciereDTO, or with status {@code 400 (Bad Request)} if the institutionFinanciere has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/institution-financieres")
    public ResponseEntity<InstitutionFinanciereDTO> createInstitutionFinanciere(@RequestBody InstitutionFinanciereDTO institutionFinanciereDTO) throws URISyntaxException {
        log.debug("REST request to save InstitutionFinanciere : {}", institutionFinanciereDTO);
        if (institutionFinanciereDTO.getId() != null) {
            throw new BadRequestAlertException("A new institutionFinanciere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstitutionFinanciereDTO result = institutionFinanciereService.save(institutionFinanciereDTO);
        return ResponseEntity.created(new URI("/api/institution-financieres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /institution-financieres} : Updates an existing institutionFinanciere.
     *
     * @param institutionFinanciereDTO the institutionFinanciereDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated institutionFinanciereDTO,
     * or with status {@code 400 (Bad Request)} if the institutionFinanciereDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the institutionFinanciereDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/institution-financieres")
    public ResponseEntity<InstitutionFinanciereDTO> updateInstitutionFinanciere(@RequestBody InstitutionFinanciereDTO institutionFinanciereDTO) throws URISyntaxException {
        log.debug("REST request to update InstitutionFinanciere : {}", institutionFinanciereDTO);
        if (institutionFinanciereDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstitutionFinanciereDTO result = institutionFinanciereService.save(institutionFinanciereDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, institutionFinanciereDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /institution-financieres} : get all the institutionFinancieres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of institutionFinancieres in body.
     */
    @GetMapping("/institution-financieres")
    public List<InstitutionFinanciereDTO> getAllInstitutionFinancieres() {
        log.debug("REST request to get all InstitutionFinancieres");
        return institutionFinanciereService.findAll();
    }

    /**
     * {@code GET  /institution-financieres/:id} : get the "id" institutionFinanciere.
     *
     * @param id the id of the institutionFinanciereDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the institutionFinanciereDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/institution-financieres/{id}")
    public ResponseEntity<InstitutionFinanciereDTO> getInstitutionFinanciere(@PathVariable Long id) {
        log.debug("REST request to get InstitutionFinanciere : {}", id);
        Optional<InstitutionFinanciereDTO> institutionFinanciereDTO = institutionFinanciereService.findOne(id);
        return ResponseUtil.wrapOrNotFound(institutionFinanciereDTO);
    }

    @GetMapping("/institution-financieres/institutionFinancieres")
    public ResponseEntity<List<InstitutionFinanciereDTO>> getInstitution() {
        log.debug("REST request to get InstitutionFinanciere : {}");
        return ResponseEntity.ok().body(institutionFinanciereService.findInstitutionBancaire());
    }

    /**
     * {@code DELETE  /institution-financieres/:id} : delete the "id" institutionFinanciere.
     *
     * @param id the id of the institutionFinanciereDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/institution-financieres/{id}")
    public ResponseEntity<Void> deleteInstitutionFinanciere(@PathVariable Long id) {
        log.debug("REST request to delete InstitutionFinanciere : {}", id);

        institutionFinanciereService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
