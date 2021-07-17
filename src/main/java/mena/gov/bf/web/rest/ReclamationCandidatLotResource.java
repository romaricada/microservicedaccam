package mena.gov.bf.web.rest;

import mena.gov.bf.service.ReclamationCandidatLotService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.ReclamationCandidatLotDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.ReclamationCandidatLot}.
 */
@RestController
@RequestMapping("/api")
public class ReclamationCandidatLotResource {

    private final Logger log = LoggerFactory.getLogger(ReclamationCandidatLotResource.class);

    private static final String ENTITY_NAME = "microservicedaccamReclamationCandidatLot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReclamationCandidatLotService reclamationCandidatLotService;

    public ReclamationCandidatLotResource(ReclamationCandidatLotService reclamationCandidatLotService) {
        this.reclamationCandidatLotService = reclamationCandidatLotService;
    }

    /**
     * {@code POST  /reclamation-candidat-lots} : Create a new reclamationCandidatLot.
     *
     * @param reclamationCandidatLotDTO the reclamationCandidatLotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reclamationCandidatLotDTO, or with status {@code 400 (Bad Request)} if the reclamationCandidatLot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reclamation-candidat-lots")
    public ResponseEntity<ReclamationCandidatLotDTO> createReclamationCandidatLot(@RequestBody ReclamationCandidatLotDTO reclamationCandidatLotDTO) throws URISyntaxException {
        log.debug("REST request to save ReclamationCandidatLot : {}", reclamationCandidatLotDTO);
        if (reclamationCandidatLotDTO.getId() != null) {
            throw new BadRequestAlertException("A new reclamationCandidatLot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReclamationCandidatLotDTO result = reclamationCandidatLotService.save(reclamationCandidatLotDTO);
        return ResponseEntity.created(new URI("/api/reclamation-candidat-lots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reclamation-candidat-lots} : Updates an existing reclamationCandidatLot.
     *
     * @param reclamationCandidatLotDTO the reclamationCandidatLotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reclamationCandidatLotDTO,
     * or with status {@code 400 (Bad Request)} if the reclamationCandidatLotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reclamationCandidatLotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reclamation-candidat-lots")
    public ResponseEntity<ReclamationCandidatLotDTO> updateReclamationCandidatLot(@RequestBody ReclamationCandidatLotDTO reclamationCandidatLotDTO) throws URISyntaxException {
        log.debug("REST request to update ReclamationCandidatLot : {}", reclamationCandidatLotDTO);
        if (reclamationCandidatLotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReclamationCandidatLotDTO result = reclamationCandidatLotService.save(reclamationCandidatLotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reclamationCandidatLotDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reclamation-candidat-lots} : get all the reclamationCandidatLots.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reclamationCandidatLots in body.
     */
    @GetMapping("/reclamation-candidat-lots")
    public ResponseEntity<List<ReclamationCandidatLotDTO>> getAllReclamationCandidatLots(Pageable pageable) {
        log.debug("REST request to get a page of ReclamationCandidatLots");
        Page<ReclamationCandidatLotDTO> page = reclamationCandidatLotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reclamation-candidat-lots/:id} : get the "id" reclamationCandidatLot.
     *
     * @param id the id of the reclamationCandidatLotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reclamationCandidatLotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reclamation-candidat-lots/{id}")
    public ResponseEntity<ReclamationCandidatLotDTO> getReclamationCandidatLot(@PathVariable Long id) {
        log.debug("REST request to get ReclamationCandidatLot : {}", id);
        Optional<ReclamationCandidatLotDTO> reclamationCandidatLotDTO = reclamationCandidatLotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reclamationCandidatLotDTO);
    }

    /**
     * {@code DELETE  /reclamation-candidat-lots/:id} : delete the "id" reclamationCandidatLot.
     *
     * @param id the id of the reclamationCandidatLotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reclamation-candidat-lots/{id}")
    public ResponseEntity<Void> deleteReclamationCandidatLot(@PathVariable Long id) {
        log.debug("REST request to delete ReclamationCandidatLot : {}", id);
        reclamationCandidatLotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PutMapping ("/reclamation-candidat-lots/init-reclamation-candidat-lot")
    public ResponseEntity<List<ReclamationCandidatLotDTO>> initReclamationCandidatLot(@Valid @RequestBody List<Long> lotId) {
        return ResponseEntity.ok().body(reclamationCandidatLotService.initReclamationCandidatLot2(lotId));
    }
}
