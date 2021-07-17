package mena.gov.bf.web.rest;

import mena.gov.bf.service.PieceCandidatService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.PieceCandidatDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.PieceCandidat}.
 */
@RestController
@RequestMapping("/api")
public class PieceCandidatResource {

    private final Logger log = LoggerFactory.getLogger(PieceCandidatResource.class);

    private static final String ENTITY_NAME = "microservicedaccamPieceCandidat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PieceCandidatService pieceCandidatService;

    public PieceCandidatResource(PieceCandidatService pieceCandidatService) {
        this.pieceCandidatService = pieceCandidatService;
    }

    /**
     * {@code POST  /piece-candidats} : Create a new pieceCandidat.
     *
     * @param pieceCandidatDTO the pieceCandidatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pieceCandidatDTO, or with status {@code 400 (Bad Request)} if the pieceCandidat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/piece-candidats")
    public ResponseEntity<PieceCandidatDTO> createPieceCandidat(@Valid @RequestBody PieceCandidatDTO pieceCandidatDTO) throws URISyntaxException {
        log.debug("REST request to save PieceCandidat : {}", pieceCandidatDTO);
        if (pieceCandidatDTO.getId() != null) {
            throw new BadRequestAlertException("A new pieceCandidat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PieceCandidatDTO result = pieceCandidatService.save(pieceCandidatDTO);
        return ResponseEntity.created(new URI("/api/piece-candidats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /piece-candidats} : Updates an existing pieceCandidat.
     *
     * @param pieceCandidatDTO the pieceCandidatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pieceCandidatDTO,
     * or with status {@code 400 (Bad Request)} if the pieceCandidatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pieceCandidatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/piece-candidats")
    public ResponseEntity<PieceCandidatDTO> updatePieceCandidat(@Valid @RequestBody PieceCandidatDTO pieceCandidatDTO) throws URISyntaxException {
        log.debug("REST request to update PieceCandidat : {}", pieceCandidatDTO);
        if (pieceCandidatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PieceCandidatDTO result = pieceCandidatService.save(pieceCandidatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pieceCandidatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /piece-candidats} : get all the pieceCandidats.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pieceCandidats in body.
     */
    @GetMapping("/piece-candidats")
    public ResponseEntity<List<PieceCandidatDTO>> getAllPieceCandidats(Pageable pageable) {
        log.debug("REST request to get a page of PieceCandidats");
        Page<PieceCandidatDTO> page = pieceCandidatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /piece-candidats/:id} : get the "id" pieceCandidat.
     *
     * @param id the id of the pieceCandidatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pieceCandidatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/piece-candidats/{id}")
    public ResponseEntity<PieceCandidatDTO> getPieceCandidat(@PathVariable Long id) {
        log.debug("REST request to get PieceCandidat : {}", id);
        Optional<PieceCandidatDTO> pieceCandidatDTO = pieceCandidatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pieceCandidatDTO);
    }

    /**
     * {@code DELETE  /piece-candidats/:id} : delete the "id" pieceCandidat.
     *
     * @param id the id of the pieceCandidatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/piece-candidats/{id}")
    public ResponseEntity<Void> deletePieceCandidat(@PathVariable Long id) {
        log.debug("REST request to delete PieceCandidat : {}", id);
        pieceCandidatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/piece-candidats/init-piece-candidat")
    public ResponseEntity<List<PieceCandidatDTO>> initPieceCandidat() {
        return ResponseEntity.ok(  ).body(  pieceCandidatService.initPieceCandidat());
    }
}
