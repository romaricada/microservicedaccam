package mena.gov.bf.web.rest;

import mena.gov.bf.service.TypeCommissionService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.TypeCommissionDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.TypeCommission}.
 */
@RestController
@RequestMapping("/api")
public class TypeCommissionResource {

    private final Logger log = LoggerFactory.getLogger(TypeCommissionResource.class);

    private static final String ENTITY_NAME = "microservicedaccamTypeCommission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeCommissionService typeCommissionService;

    public TypeCommissionResource(TypeCommissionService typeCommissionService) {
        this.typeCommissionService = typeCommissionService;
    }

    /**
     * {@code POST  /type-commissions} : Create a new typeCommission.
     *
     * @param typeCommissionDTO the typeCommissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeCommissionDTO, or with status {@code 400 (Bad Request)} if the typeCommission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-commissions")
    public ResponseEntity<TypeCommissionDTO> createTypeCommission(@Valid @RequestBody TypeCommissionDTO typeCommissionDTO) throws URISyntaxException {
        log.debug("REST request to save TypeCommission : {}", typeCommissionDTO);
        if (typeCommissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeCommission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeCommissionDTO result = typeCommissionService.save(typeCommissionDTO);
        return ResponseEntity.created(new URI("/api/type-commissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-commissions} : Updates an existing typeCommission.
     *
     * @param typeCommissionDTO the typeCommissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeCommissionDTO,
     * or with status {@code 400 (Bad Request)} if the typeCommissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeCommissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-commissions")
    public ResponseEntity<TypeCommissionDTO> updateTypeCommission(@Valid @RequestBody TypeCommissionDTO typeCommissionDTO) throws URISyntaxException {
        log.debug("REST request to update TypeCommission : {}", typeCommissionDTO);
        if (typeCommissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        System.out.println("-----------------------------------------------------------------");
        TypeCommissionDTO result = typeCommissionService.save(typeCommissionDTO);
        System.out.println("-----------------------------------------------------------------"+ result);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeCommissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-commissions} : get all the typeCommissions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeCommissions in body.
     */
    @GetMapping("/type-commissions")
    public ResponseEntity<List<TypeCommissionDTO>> getAllTypeCommissions(Pageable pageable) {
        log.debug("REST request to get a page of TypeCommissions");
        Page<TypeCommissionDTO> page = typeCommissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-commissions/:id} : get the "id" typeCommission.
     *
     * @param id the id of the typeCommissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeCommissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-commissions/{id}")
    public ResponseEntity<TypeCommissionDTO> getTypeCommission(@PathVariable Long id) {
        log.debug("REST request to get TypeCommission : {}", id);
        Optional<TypeCommissionDTO> typeCommissionDTO = typeCommissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeCommissionDTO);
    }

    /**
     * {@code DELETE  /type-commissions/:id} : delete the "id" typeCommission.
     *
     * @param id the id of the typeCommissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-commissions/{id}")
    public ResponseEntity<Void> deleteTypeCommission(@PathVariable Long id) {
        log.debug("REST request to delete TypeCommission : {}", id);
        typeCommissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @GetMapping("/type-commissions/find-all")
    public ResponseEntity<List<TypeCommissionDTO>> findAllNaturePrestation() {
        return ResponseEntity.ok().body(typeCommissionService.findAlltypeCommission());
    }

    @GetMapping("/type-commissions/all-by-avis-dac")
    public ResponseEntity<List<TypeCommissionDTO>> listTypeCommissionByAvisDac(@RequestParam(name = "avisDacId") Long avisDacId) {
        return ResponseEntity.ok(  ).body( typeCommissionService.findAllTypeCommissionByAvisDac(avisDacId));
    }
}
