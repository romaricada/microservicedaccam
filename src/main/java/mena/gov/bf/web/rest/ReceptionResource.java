package mena.gov.bf.web.rest;

import mena.gov.bf.service.ReceptionService;
import mena.gov.bf.service.dto.AvisDacDTO;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.ReceptionDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Reception}.
 */
@RestController
@RequestMapping("/api")
public class ReceptionResource {

    private final Logger log = LoggerFactory.getLogger(ReceptionResource.class);

    private static final String ENTITY_NAME = "microservicedaccamReception";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceptionService receptionService;

    public ReceptionResource(ReceptionService receptionService) {
        this.receptionService = receptionService;
    }

    /**
     * {@code POST  /receptions} : Create a new reception.
     *
     * @param receptionDTO the receptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receptionDTO, or with status {@code 400 (Bad Request)} if the reception has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receptions")
    public ResponseEntity<ReceptionDTO> createReception(@Valid @RequestBody ReceptionDTO receptionDTO) throws URISyntaxException {
        log.debug("REST request to save Reception : {}", receptionDTO);
        if (receptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new reception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceptionDTO result = receptionService.save(receptionDTO);
        return ResponseEntity.created(new URI("/api/receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /receptions} : Updates an existing reception.
     *
     * @param receptionDTO the receptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receptionDTO,
     * or with status {@code 400 (Bad Request)} if the receptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the receptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/receptions")
    public ResponseEntity<ReceptionDTO> updateReception(@Valid @RequestBody ReceptionDTO receptionDTO) throws URISyntaxException {
        log.debug("REST request to update Reception : {}", receptionDTO);
        if (receptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReceptionDTO result = receptionService.save(receptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, receptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /receptions} : get all the receptions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receptions in body.
     */
    @GetMapping("/receptions")
    public ResponseEntity<List<ReceptionDTO>> getAllReceptions(Pageable pageable) {
        log.debug("REST request to get a page of Receptions");
        Page<ReceptionDTO> page = receptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /receptions/:id} : get the "id" reception.
     *
     * @param id the id of the receptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receptions/{id}")
    public ResponseEntity<ReceptionDTO> getReception(@PathVariable Long id) {
        log.debug("REST request to get Reception : {}", id);
        Optional<ReceptionDTO> receptionDTO = receptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(receptionDTO);
    }

    /**
     * {@code DELETE  /receptions/:id} : delete the "id" reception.
     *
     * @param id the id of the receptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receptions/{id}")
    public ResponseEntity<Void> deleteReception(@PathVariable Long id) {
        log.debug("REST request to delete Reception : {}", id);
        receptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/receptions/updateListe")
    public ResponseEntity<List<ReceptionDTO>> updateAall(@RequestBody List<ReceptionDTO> receptionDTOS){
        return ResponseEntity.ok(receptionService.updateAall(receptionDTOS));
    }


   /*@GetMapping("/receptions/all-by-AVISDACAndExercice")
    public ResponseEntity<List<ReceptionDTO>> findReceptionByNumeroDac(@RequestParam Long exerciceId ,@RequestParam Long avisDacId) {
        return ResponseEntity.ok(  ).body( receptionService.findReceptionByNumeroDac(exerciceId,avisDacId));

}*/
   @GetMapping("/receptions/all-by-Exercice")
    public ResponseEntity<List<ReceptionDTO>> findReceptionByExercice(@RequestParam Long exerciceId) {
        return ResponseEntity.ok(  ).body( receptionService.findReceptionByExercice(exerciceId));

    }

    @GetMapping("/receptions/all-by-avis-dac")
    public ResponseEntity<List<ReceptionDTO>> findReceptionByAvisdac(@RequestParam Long avisDacId) {
        return ResponseEntity.ok( ).body( receptionService.findRecpetionByDac(avisDacId));

    }




}
