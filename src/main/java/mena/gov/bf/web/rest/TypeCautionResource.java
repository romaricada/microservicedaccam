package mena.gov.bf.web.rest;

import mena.gov.bf.service.TypeCautionService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.TypeCautionDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.TypeCaution}.
 */
@RestController
@RequestMapping("/api")
public class TypeCautionResource {

    private final Logger log = LoggerFactory.getLogger(TypeCautionResource.class);

    private static final String ENTITY_NAME = "microservicedaccamTypeCaution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeCautionService typeCautionService;

    public TypeCautionResource(TypeCautionService typeCautionService) {
        this.typeCautionService = typeCautionService;
    }

    /**
     * {@code POST  /type-cautions} : Create a new typeCaution.
     *
     * @param typeCautionDTO the typeCautionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeCautionDTO, or with status {@code 400 (Bad Request)} if the typeCaution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-cautions")
    public ResponseEntity<TypeCautionDTO> createTypeCaution(@Valid @RequestBody TypeCautionDTO typeCautionDTO) throws URISyntaxException {
        log.debug("REST request to save TypeCaution : {}", typeCautionDTO);
        if (typeCautionDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeCaution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeCautionDTO result = typeCautionService.save(typeCautionDTO);
        return ResponseEntity.created(new URI("/api/type-cautions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-cautions} : Updates an existing typeCaution.
     *
     * @param typeCautionDTO the typeCautionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeCautionDTO,
     * or with status {@code 400 (Bad Request)} if the typeCautionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeCautionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-cautions")
    public ResponseEntity<TypeCautionDTO> updateTypeCaution(@Valid @RequestBody TypeCautionDTO typeCautionDTO) throws URISyntaxException {
        log.debug("REST request to update TypeCaution : {}", typeCautionDTO);
        if (typeCautionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeCautionDTO result = typeCautionService.save(typeCautionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeCautionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-cautions} : get all the typeCautions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeCautions in body.
     */
    @GetMapping("/type-cautions")
    public ResponseEntity<List<TypeCautionDTO>> getAllTypeCautions(Pageable pageable) {
        log.debug("REST request to get a page of TypeCautions");
        Page<TypeCautionDTO> page = typeCautionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-cautions/:id} : get the "id" typeCaution.
     *
     * @param id the id of the typeCautionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeCautionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-cautions/{id}")
    public ResponseEntity<TypeCautionDTO> getTypeCaution(@PathVariable Long id) {
        log.debug("REST request to get TypeCaution : {}", id);
        Optional<TypeCautionDTO> typeCautionDTO = typeCautionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeCautionDTO);
    }

    /**
     * {@code DELETE  /type-cautions/:id} : delete the "id" typeCaution.
     *
     * @param id the id of the typeCautionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-cautions/{id}")
    public ResponseEntity<Void> deleteTypeCaution(@PathVariable Long id) {
        log.debug("REST request to delete TypeCaution : {}", id);
        typeCautionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
