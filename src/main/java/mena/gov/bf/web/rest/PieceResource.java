package mena.gov.bf.web.rest;

import mena.gov.bf.service.PieceService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.PieceDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Piece}.
 */
@RestController
@RequestMapping("/api")
public class PieceResource {

    private final Logger log = LoggerFactory.getLogger(PieceResource.class);

    private static final String ENTITY_NAME = "microservicedaccamPiece";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PieceService pieceService;

    public PieceResource(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    /**
     * {@code POST  /pieces} : Create a new piece.
     *
     * @param pieceDTO the pieceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pieceDTO, or with status {@code 400 (Bad Request)} if the piece has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pieces")
    public ResponseEntity<PieceDTO> createPiece(@Valid @RequestBody PieceDTO pieceDTO) throws URISyntaxException {
        log.debug("REST request to save Piece : {}", pieceDTO);
        if (pieceDTO.getId() != null) {
            throw new BadRequestAlertException("A new piece cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PieceDTO result = pieceService.save(pieceDTO);
        return ResponseEntity.created(new URI("/api/pieces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pieces} : Updates an existing piece.
     *
     * @param pieceDTO the pieceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pieceDTO,
     * or with status {@code 400 (Bad Request)} if the pieceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pieceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pieces")
    public ResponseEntity<PieceDTO> updatePiece(@Valid @RequestBody PieceDTO pieceDTO) throws URISyntaxException {
        log.debug("REST request to update Piece : {}", pieceDTO);
        if (pieceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PieceDTO result = pieceService.save(pieceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pieceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pieces} : get all the pieces.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pieces in body.
     */
    @GetMapping("/pieces")
    public ResponseEntity<List<PieceDTO>> getAllPieces(Pageable pageable) {
        log.debug("REST request to get a page of Pieces");
        Page<PieceDTO> page = pieceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pieces/:id} : get the "id" piece.
     *
     * @param id the id of the pieceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pieceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pieces/{id}")
    public ResponseEntity<PieceDTO> getPiece(@PathVariable Long id) {
        log.debug("REST request to get Piece : {}", id);
        Optional<PieceDTO> pieceDTO = pieceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pieceDTO);
    }

    /**
     * {@code DELETE  /pieces/:id} : delete the "id" piece.
     *
     * @param id the id of the pieceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pieces/{id}")
    public ResponseEntity<Void> deletePiece(@PathVariable Long id) {
        log.debug("REST request to delete Piece : {}", id);
        pieceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/pieces/updateListe")
    public ResponseEntity<List<PieceDTO>> updateAall(@RequestBody List<PieceDTO> pieceDTOS){
        return ResponseEntity.ok(pieceService.updateAall(pieceDTOS));
    }
}
