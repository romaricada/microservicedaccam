package mena.gov.bf.web.rest;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.service.CandidatLotService;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.CandidatLotDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.CandidatLot}.
 */
@RestController
@RequestMapping("/api")
public class CandidatLotResource {

    private final Logger log = LoggerFactory.getLogger( CandidatLotResource.class );

    private static final String ENTITY_NAME = "microservicedaccamCandidatLot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidatLotService candidatLotService;

    public CandidatLotResource(CandidatLotService candidatLotService) {
        this.candidatLotService = candidatLotService;
    }

    /**
     * {@code POST  /candidat-lots} : Create a new candidatLot.
     *
     * @param candidatLotDTO the candidatLotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidatLotDTO, or with status {@code 400 (Bad Request)} if the candidatLot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/candidat-lots")
    public ResponseEntity<CandidatLotDTO> createCandidatLot(@Valid @RequestBody CandidatLotDTO candidatLotDTO) throws URISyntaxException {
        log.debug( "REST request to save CandidatLot : {}", candidatLotDTO );
        if (candidatLotDTO.getId() != null) {
            throw new BadRequestAlertException( "A new candidatLot cannot already have an ID", ENTITY_NAME, "idexists" );
        }
        CandidatLotDTO result = candidatLotService.save( candidatLotDTO );
        return ResponseEntity.created( new URI( "/api/candidat-lots/" + result.getId() ) )
            .headers( HeaderUtil.createEntityCreationAlert( applicationName, true, ENTITY_NAME, result.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code PUT  /candidat-lots} : Updates an existing candidatLot.
     *
     * @param candidatLotDTO the candidatLotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidatLotDTO,
     * or with status {@code 400 (Bad Request)} if the candidatLotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidatLotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/candidat-lots")
    public ResponseEntity<CandidatLotDTO> updateCandidatLot(@Valid @RequestBody CandidatLotDTO candidatLotDTO) throws URISyntaxException {
        log.debug( "REST request to update CandidatLot : {}", candidatLotDTO );
        if (candidatLotDTO.getId() == null) {
            throw new BadRequestAlertException( "Invalid id", ENTITY_NAME, "idnull" );
        }
        CandidatLotDTO result = candidatLotService.save( candidatLotDTO );
        return ResponseEntity.ok()
            .headers( HeaderUtil.createEntityUpdateAlert( applicationName, true, ENTITY_NAME, candidatLotDTO.getId().toString() ) )
            .body( result );
    }

    @PutMapping("/candidat-lots/save-dembre")
    public ResponseEntity<CandidatLot> updateCandidatLot1(@Valid @RequestBody CandidatLot candidatLot) throws URISyntaxException {
        log.debug( "REST request to update CandidatLot : {}", candidatLot );
        if (candidatLot.getId() == null) {
            throw new BadRequestAlertException( "Invalid id", ENTITY_NAME, "idnull" );
        }
        CandidatLot result = candidatLotService.save1( candidatLot );
        return ResponseEntity.ok()
            .headers( HeaderUtil.createEntityUpdateAlert( applicationName, true, ENTITY_NAME, candidatLot.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code GET  /candidat-lots} : get all the candidatLots.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidatLots in body.
     */
    @GetMapping("/candidat-lots")
    public ResponseEntity<List<CandidatLotDTO>> getAllCandidatLots(Pageable pageable) {
        log.debug( "REST request to get a page of CandidatLots" );
        Page<CandidatLotDTO> page = candidatLotService.findAll( pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( ServletUriComponentsBuilder.fromCurrentRequest(), page );
        return ResponseEntity.ok().headers( headers ).body( page.getContent() );
    }

    /**
     * {@code GET  /candidat-lots/:id} : get the "id" candidatLot.
     *
     * @param id the id of the candidatLotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidatLotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidat-lots/{id}")
    public ResponseEntity<CandidatLotDTO> getCandidatLot(@PathVariable Long id) {
        log.debug( "REST request to get CandidatLot : {}", id );
        Optional<CandidatLotDTO> candidatLotDTO = candidatLotService.findOne( id );
        return ResponseUtil.wrapOrNotFound( candidatLotDTO );
    }

    @GetMapping("/candidat-lots/bylot")
    public ResponseEntity<CandidatLotDTO> getCandidatLotBylot(@RequestParam(name = "lotId") Long lotId) {
        log.debug( "REST request to get CandidatLot : {}", lotId );
        // CandidatLotDTO candidatLotDTO = candidatLotService.findbyCandidat(id );
        return ResponseEntity.ok(candidatLotService.findbyCandidat(lotId));
    }

    /**
     * {@code DELETE  /candidat-lots/:id} : delete the "id" candidatLot.
     *
     * @param id the id of the candidatLotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidat-lots/{id}")
    public ResponseEntity<Void> deleteCandidatLot(@PathVariable Long id) {
        log.debug( "REST request to delete CandidatLot : {}", id );
        candidatLotService.delete( id );
        return ResponseEntity.noContent().headers( HeaderUtil.createEntityDeletionAlert( applicationName, true, ENTITY_NAME, id.toString() ) ).build();
    }

    @GetMapping("/candidat-lots/all-candidat-by-activite")
    public ResponseEntity<List<CandidatLotDTO>> findAllByActivite(@RequestParam(name = "activiteId") Long activiteId) {
        return ResponseEntity.ok().body( candidatLotService.findAllCandidatByActivite( activiteId ) );
    }

    @GetMapping("/candidat-lots/all-candidat-by-lot")
    public ResponseEntity<List<CandidatLotDTO>> findAllByByLot(@RequestParam(name = "lotId") Long lotId) {
        return ResponseEntity.ok().body( candidatLotService.findAllCandidatByLot( lotId ) );
    }

    @GetMapping("/candidat-lots/find-all")
    public ResponseEntity<List<CandidatLotDTO>> findAllCandidatLot() {
        return ResponseEntity.ok().body( candidatLotService.findAllCandidatLot() );
    }

   /* @GetMapping("/candidat-lots/candidat-by-lots")
    public ResponseEntity<List<CandidatLotDTO>> findAllByActivite(@RequestParam(name = "lotId") Long lotId) {
        return ResponseEntity.ok().body(candidatLotService.findAllCandidatByLot(lotId));
    }*/

    @GetMapping("/candidat-lots/soumissionnaire-by-lot")
    public ResponseEntity<List<CandidatLotDTO>> findAllSoummissionnaireByLot(@RequestParam(name = "lotId") Long lotId) {
        return ResponseEntity.ok().body(candidatLotService.findSoumissionnaireByLot(lotId));
    }

    @PutMapping("/candidat-lots/updateListe")
    public ResponseEntity<List<CandidatLotDTO>> updateaAll(@RequestBody List<CandidatLotDTO> candidatLotDTOS1){
        return ResponseEntity.ok(candidatLotService.updateaAll(candidatLotDTOS1));
    }

    @PutMapping("/candidat-lots/active-attributaire")
    public ResponseEntity<CandidatLotDTO> activateAttributaire(@Valid @RequestBody CandidatLotDTO candidatLotDTO) {
        CandidatLotDTO result = candidatLotService.activateAttributaire(candidatLotDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/candidat-lots/attributaire-by-lot")
    public ResponseEntity<CandidatLotDTO> findAttributaireByLot(@RequestParam(name = "lotId") Long lotId, @RequestParam Long activiteId) {
        return ResponseEntity.ok().body(candidatLotService.findAttributaireByLot(lotId, activiteId));
    }

    @GetMapping("/candidat-lots/attributaire-by-lot-candidat")
    public ResponseEntity<CandidatLotDTO> findAttributaireByLotCandidat(@RequestParam(name = "lotId") Long lotId) {
        return ResponseEntity.ok().body(candidatLotService.findAttributaireByLotCandidat(lotId));
    }

    @GetMapping("/candidat-lots/find-attributaire")
    public ResponseEntity<CandidatLotDTO> findAttributaire(@RequestParam Long lotId, @RequestParam Long activiteId) {
        return ResponseEntity.ok(  ).body( candidatLotService.findAttributaireByLot(lotId,activiteId) );
    }
    @GetMapping("/candidat-lots/find-list-attributaire-by-lot")
    public ResponseEntity<List<CandidatLotDTO>> findlistAttributairebylot(@RequestParam Long activiteId, @RequestParam Long candidatId ) {
        List<CandidatLotDTO> result = candidatLotService.findlistAttributairebylot(activiteId, candidatId);
        return ResponseEntity.ok().body(result );
    }
    @GetMapping("/candidat-lots/find-list-lot-by-attributaire")
    public ResponseEntity<List<LotDTO>> findlistlotbyAttributaire(@RequestParam Long idLot ) {
        List<LotDTO> result = candidatLotService.findAllLotByCandidatLot(idLot);
        return ResponseEntity.ok().body(result );
    }

    @GetMapping("/candidat-lots/init-liste-lot")
    public ResponseEntity<List<CandidatLotDTO>> initLot(@RequestParam(name = "candidatId") Long candidatId,
                                                        @RequestParam(name = "avisDacId") Long avisDacId) {
        return ResponseEntity.ok().body(candidatLotService.initCandidatLot(candidatId, avisDacId));
    }

    @GetMapping("/candidat-lots/find-list-attributaire-by-activite")
    public ResponseEntity<List<CandidatLotDTO>> findlistAttributairebylot(@RequestParam Long avisDacId ) {
        //List<CandidatLotDTO> result = ;
        return ResponseEntity.ok().body(candidatLotService.findListeAttributaireByActivite(avisDacId) );
    }

    @GetMapping("/candidat-lots/find-Candidat-contrat")
    public ResponseEntity<List<CandidatLot>> findAllByLotIdAndDeletedIsFalseAndAttributaireIsNotNullAndAttributaireIsTrue(@RequestParam Long lotId ) {
        //List<CandidatLotDTO> result = ;
        return ResponseEntity.ok().body(candidatLotService.findAllByLotIdAndDeletedIsFalseAndAttributaireIsNotNullAndAttributaireIsTrue(lotId) );
    }

    @GetMapping("/candidat-lots/find-CandidatByCandidatId")
    public ResponseEntity<List<CandidatLotDTO>> findAllByCandidatId(@RequestParam Long candidatId ) {
        return ResponseEntity.ok().body(candidatLotService.findAllByCandidatId(candidatId) );
    }

    @PostMapping("/candidat-lots/all-candidat-lots")
    public ResponseEntity<List<CandidatLotDTO>> allCandidatLotList(List<CandidatLotDTO> candidatLots){
        return ResponseEntity.ok().body(candidatLotService.findAllList(candidatLots));
    }
}
