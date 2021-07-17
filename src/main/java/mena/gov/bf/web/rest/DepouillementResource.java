package mena.gov.bf.web.rest;

import mena.gov.bf.service.DepouillementService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.DepouillementDTO;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.Depouillement}.
 */
@RestController
@RequestMapping("/api")
public class DepouillementResource {

    private final Logger log = LoggerFactory.getLogger(DepouillementResource.class);

    private static final String ENTITY_NAME = "microservicedaccamDepouillement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepouillementService depouillementService;

    public DepouillementResource(DepouillementService depouillementService) {
        this.depouillementService = depouillementService;
    }

    /**
     * {@code POST  /depouillements} : Create a new depouillement.
     *
     * @param depouillementDTO the depouillementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depouillementDTO, or with status {@code 400 (Bad Request)} if the depouillement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/depouillements")
    public ResponseEntity<DepouillementDTO> createDepouillement(@Valid @RequestBody DepouillementDTO depouillementDTO) throws URISyntaxException, IOException {
        if (depouillementDTO.getId() != null) {
            throw new BadRequestAlertException("A new depouillement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepouillementDTO result = depouillementService.save(depouillementDTO);
        return ResponseEntity.created(new URI("/api/depouillements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /depouillements} : Updates an existing depouillement.
     *
     * @param depouillementDTO the depouillementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depouillementDTO,
     * or with status {@code 400 (Bad Request)} if the depouillementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depouillementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/depouillements")
    public ResponseEntity<DepouillementDTO> updateDepouillement(@Valid @RequestBody DepouillementDTO depouillementDTO) throws URISyntaxException, IOException {
        log.debug("REST request to update Depouillement : {}", depouillementDTO);
        if (depouillementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepouillementDTO result = depouillementService.save(depouillementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, depouillementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /depouillements} : get all the depouillements.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depouillements in body.
     */
    @GetMapping("/depouillements")
    public ResponseEntity<List<DepouillementDTO>> getAllDepouillements( @RequestParam(name = "avisDacId") Long avisDacId, Pageable pageable) {
        log.debug("REST request to get a page of Depouillements");
        Page<DepouillementDTO> page = depouillementService.findAll(pageable, avisDacId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /depouillements/:id} : get the "id" depouillement.
     *
     * @param id the id of the depouillementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depouillementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/depouillements/{id}")
    public ResponseEntity<DepouillementDTO> getDepouillement(@PathVariable Long id) {
        log.debug("REST request to get Depouillement : {}", id);
        Optional<DepouillementDTO> depouillementDTO = depouillementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depouillementDTO);
    }

    /**
     * {@code DELETE  /depouillements/:id} : delete the "id" depouillement.
     *
     * @param id the id of the depouillementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/depouillements/{id}")
    public ResponseEntity<Void> deleteDepouillement(@PathVariable Long id) {
        log.debug("REST request to delete Depouillement : {}", id);
        depouillementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
