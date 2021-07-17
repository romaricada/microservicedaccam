package mena.gov.bf.web.rest;

import mena.gov.bf.service.LotService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.LotDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Lot}.
 */
@RestController
@RequestMapping("/api")
public class LotResource {

    private final Logger log = LoggerFactory.getLogger( LotResource.class );

    private static final String ENTITY_NAME = "microservicedaccamLot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LotService lotService;

    public LotResource(LotService lotService) {
        this.lotService = lotService;
    }

    /**
     * {@code POST  /lots} : Create a new lot.
     *
     * @param lotDTO the lotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lotDTO, or with status {@code 400 (Bad Request)} if the lot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lots")
    public ResponseEntity<LotDTO> createLot(@Valid @RequestBody LotDTO lotDTO) throws URISyntaxException {
        log.debug( "REST request to save Lot : {}", lotDTO );
        if (lotDTO.getId() != null) {
            throw new BadRequestAlertException( "A new lot cannot already have an ID", ENTITY_NAME, "idexists" );
        }
        LotDTO result = lotService.save( lotDTO );
        return ResponseEntity.created( new URI( "/api/lots/" + result.getId() ) )
            .headers( HeaderUtil.createEntityCreationAlert( applicationName, true, ENTITY_NAME, result.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code PUT  /lots} : Updates an existing lot.
     *
     * @param lotDTO the lotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lotDTO,
     * or with status {@code 400 (Bad Request)} if the lotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lots")
    public ResponseEntity<LotDTO> updateLot(@Valid @RequestBody LotDTO lotDTO) throws URISyntaxException {
        log.debug( "REST request to update Lot : {}", lotDTO );
        if (lotDTO.getId() == null) {
            throw new BadRequestAlertException( "Invalid id", ENTITY_NAME, "idnull" );
        }
        LotDTO result = lotService.save( lotDTO );
        return ResponseEntity.ok()
            .headers( HeaderUtil.createEntityUpdateAlert( applicationName, true, ENTITY_NAME, lotDTO.getId().toString() ) )
            .body( result );
    }

    @PutMapping("/lots/deleteAll")
    public ResponseEntity<LotDTO> updateLotAll(@Valid @RequestBody LotDTO lotDTO) throws URISyntaxException {
        log.debug( "REST request to update Lot : {}", lotDTO );
        if (lotDTO.getId() == null) {
            throw new BadRequestAlertException( "Invalid id", ENTITY_NAME, "idnull" );
        }
        LotDTO result = lotService.save( lotDTO );
        return ResponseEntity.ok()
            .headers( HeaderUtil.createEntityUpdateAlert( applicationName, true, ENTITY_NAME, lotDTO.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code GET  /lots} : get all the lots.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lots in body.
     */
    @GetMapping("/lots")
    public ResponseEntity<List<LotDTO>> getAllLots(Pageable pageable) {
        log.debug( "REST request to get a page of Lots" );
        Page<LotDTO> page = lotService.findAll( pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( ServletUriComponentsBuilder.fromCurrentRequest(), page );
        return ResponseEntity.ok().headers( headers ).body( page.getContent() );
    }

    /**
     * {@code GET  /lots/:id} : get the "id" lot.
     *
     * @param id the id of the lotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lots/{id}")
    public ResponseEntity<LotDTO> getLot(@PathVariable Long id) {
        log.debug( "REST request to get Lot : {}", id );
        Optional<LotDTO> lotDTO = lotService.findOne( id );
        return ResponseUtil.wrapOrNotFound( lotDTO );
    }

    /**
     * {@code DELETE  /lots/:id} : delete the "id" lot.
     *
     * @param id the id of the lotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lots/{id}")
    public ResponseEntity<Void> deleteLot(@PathVariable Long id) {
        log.debug( "REST request to delete Lot : {}", id );
        lotService.delete( id );
        return ResponseEntity.noContent().headers( HeaderUtil.createEntityDeletionAlert( applicationName, true, ENTITY_NAME, id.toString() ) ).build();
    }

    @PutMapping("/lots/deleteAllListe")
    public ResponseEntity<List<LotDTO>> deleteAll(
        @RequestBody List<LotDTO> lotDTOS) {
        return ResponseEntity.ok( lotService.deleteAll( lotDTOS ) );
    }


    @GetMapping("/lots/all-by-avis-dac")
    public ResponseEntity<List<LotDTO>> findLotByAvisDac(@RequestParam Long avisDacId) {
        return ResponseEntity.ok().body( lotService.findLotByAvisDac( avisDacId ) );
    }

    @GetMapping("/lots/activite-id")
    public ResponseEntity<List<LotDTO>> findAllByActiviteWithCandidat(@RequestParam(name = "activiteId") Long activiteId, @RequestParam(name = "status") boolean status) {
        return ResponseEntity.ok().body( lotService.findAllLotByActiviteWithCandidat( activiteId, status ) );
    }

    @PutMapping("/lots/status-update")
    public ResponseEntity<Void> updateLotStatus(@Valid @RequestBody LotDTO lotDTO) {
        log.debug( "REST request to change status Lot : {}", lotDTO );
        lotService.changeStatusLot( lotDTO );
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lots/lotAll")
    public ResponseEntity<List<LotDTO>> findAllLot(@RequestParam(name = "lotId") Long lotId) {
        return ResponseEntity.ok().body(lotService.findAllLot(lotId));
    }
    @GetMapping("/lots/lotAllInOne")
    public ResponseEntity<List<LotDTO>> findAllInOneLot(@RequestParam(name = "lotId") Long lotId) {
        return ResponseEntity.ok().body(lotService.findOneLot(lotId));
    }
    @GetMapping("/lots/lotAllByAvisDac")
    public ResponseEntity<List<LotDTO>> findLotByAvisDac1(@RequestParam Long avisDacId) {
        return ResponseEntity.ok().body(lotService.findLotByAvisDac(avisDacId));
    }
}
