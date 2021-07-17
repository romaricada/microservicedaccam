package mena.gov.bf.web.rest;

import mena.gov.bf.service.CandidatCautionLotService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.CandidatCautionLotDTO;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.CandidatCautionLot}.
 */
@RestController
@RequestMapping("/api")
public class CandidatCautionLotResource {

    private final Logger log = LoggerFactory.getLogger(CandidatCautionLotResource.class);

    private static final String ENTITY_NAME = "microservicedaccamCandidatCautionLot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidatCautionLotService candidatCautionLotService;

    public CandidatCautionLotResource(CandidatCautionLotService candidatCautionLotService) {
        this.candidatCautionLotService = candidatCautionLotService;
    }

    /**
     * {@code POST  /candidat-caution-lots} : Create a new candidatCautionLot.
     *
     * @param candidatCautionLotDTO the candidatCautionLotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidatCautionLotDTO, or with status {@code 400 (Bad Request)} if the candidatCautionLot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/candidat-caution-lots")
    public ResponseEntity<CandidatCautionLotDTO> createCandidatCautionLot(@RequestBody CandidatCautionLotDTO candidatCautionLotDTO) throws URISyntaxException {
        log.debug("REST request to save CandidatCautionLot : {}", candidatCautionLotDTO);
        if (candidatCautionLotDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidatCautionLot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CandidatCautionLotDTO result = candidatCautionLotService.save(candidatCautionLotDTO);
        return ResponseEntity.created(new URI("/api/candidat-caution-lots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /candidat-caution-lots} : Updates an existing candidatCautionLot.
     *
     * @param candidatCautionLotDTO the candidatCautionLotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidatCautionLotDTO,
     * or with status {@code 400 (Bad Request)} if the candidatCautionLotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidatCautionLotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/candidat-caution-lots")
    public ResponseEntity<CandidatCautionLotDTO> updateCandidatCautionLot(@RequestBody CandidatCautionLotDTO candidatCautionLotDTO) throws URISyntaxException {
        log.debug("REST request to update CandidatCautionLot : {}", candidatCautionLotDTO);
        if (candidatCautionLotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CandidatCautionLotDTO result = candidatCautionLotService.save(candidatCautionLotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, candidatCautionLotDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /candidat-caution-lots} : get all the candidatCautionLots.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidatCautionLots in body.
     */
    @GetMapping("/candidat-caution-lots")
    public ResponseEntity<List<CandidatCautionLotDTO>> getAllCandidatCautionLots(Pageable pageable) {
        log.debug("REST request to get a page of CandidatCautionLots");
        Page<CandidatCautionLotDTO> page = candidatCautionLotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /candidat-caution-lots/:id} : get the "id" candidatCautionLot.
     *
     * @param id the id of the candidatCautionLotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidatCautionLotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidat-caution-lots/{id}")
    public ResponseEntity<CandidatCautionLotDTO> getCandidatCautionLot(@PathVariable Long id) {
        log.debug("REST request to get CandidatCautionLot : {}", id);
        Optional<CandidatCautionLotDTO> candidatCautionLotDTO = candidatCautionLotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(candidatCautionLotDTO);
    }

    /**
     * {@code DELETE  /candidat-caution-lots/:id} : delete the "id" candidatCautionLot.
     *
     * @param id the id of the candidatCautionLotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidat-caution-lots/{id}")
    public ResponseEntity<Void> deleteCandidatCautionLot(@PathVariable Long id) {
        log.debug("REST request to delete CandidatCautionLot : {}", id);
        candidatCautionLotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/candidat-caution-lots/candidatCautionLot-by-candidatLot")
    public  ResponseEntity<List<CandidatCautionLotDTO>> getCautioncandidatLotByCandidatLot(@RequestParam Long candidatLotId){
        List<CandidatCautionLotDTO> candidatCautionLotDTOS = candidatCautionLotService.findCautionCandidatLot(candidatLotId);
        return ResponseEntity.ok().body(candidatCautionLotDTOS);
    }
}
