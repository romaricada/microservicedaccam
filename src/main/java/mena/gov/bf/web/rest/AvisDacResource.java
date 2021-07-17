package mena.gov.bf.web.rest;

import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.AvisDacDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//import javax.xml.soap.Name;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import mena.gov.bf.service.AvisDacService;

/**
 * REST controller for managing {@link mena.gov.bf.domain.Candidat}.
 */
@RestController
@RequestMapping("/api")
public class AvisDacResource {

    private final Logger log = LoggerFactory.getLogger(AvisDacResource.class);

    private static final String ENTITY_NAME = "microservicedaccamCandidat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AvisDacService avisDacService;

    public AvisDacResource(AvisDacService avisDacService) {
        this.avisDacService = avisDacService;
    }

    /**
     * {@code POST  /avis-dacs} : Create a new candidat.
     *
     * @param avisDacDTO the avisDacDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and
     * with body the new avisDacDTO, or with status {@code 400 (Bad Request)} if
     * the candidat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/avis-dacs")
    public ResponseEntity<AvisDacDTO> createCandidat(@Valid @RequestBody AvisDacDTO avisDacDTO) throws URISyntaxException {
        log.debug("REST request to save Candidat : {}", avisDacDTO);
        if (avisDacDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvisDacDTO result = avisDacService.save(avisDacDTO);
        return ResponseEntity.created(new URI("/api/avis-dacs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /avis-dacs} : Updates an existing avisDac.
     *
     * @param avisDacDTO the avisDacDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body the updated avisDacDTO, or with status {@code 400 (Bad Request)} if
     * the avisDacDTO is not valid, or with status
     * {@code 500 (Internal Server Error)} if the avisDacDTO couldn't be
     * updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/avis-dacs")
    public ResponseEntity<AvisDacDTO> updateCandidat(@Valid @RequestBody AvisDacDTO avisDacDTO) throws URISyntaxException {
        log.debug("REST request to update Candidat : {}", avisDacDTO);
        if (avisDacDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvisDacDTO result = avisDacService.save(avisDacDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avisDacDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /avis-dacs} : get all the avis-dacs.
     *
     * @param activiteId
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the
     * list of avis-dacs in body.
     */
    /*@GetMapping("/avis-dacs")
    public ResponseEntity<List<AvisDacDTO>> getAllCandidats(Pageable pageable) {
        log.debug("REST request to get a page of Candidats");
        Page<AvisDacDTO> page = avisDacService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }*/
    @GetMapping("/avis-dacs")
    public ResponseEntity<List<AvisDacDTO>> getAllCandidats(@RequestParam(name = "activiteId") Long activiteId) {
        log.debug("REST request to get a page of Depouillements");
        return ResponseEntity.ok().body(avisDacService.findAll(activiteId));
    }

    /**
     * {@code GET  /avis-dacs/:id} : get the "id" candidat.
     *
     * @param id the id of the avisDacDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body the avisDacDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/avis-dacs/{id}")
    public ResponseEntity<AvisDacDTO> getCandidat(@PathVariable Long id) {
        log.debug("REST request to get Candidat : {}", id);
        Optional<AvisDacDTO> avisDacDTO = avisDacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avisDacDTO);
    }

    /**
     * {@code DELETE  /avis-dacs/:id} : delete the "id" candidat.
     *
     * @param id the id of the avisDacDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/avis-dacs/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable Long id) {
        log.debug("REST request to delete AvisDac : {}", id);
        avisDacService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/avis-dacs/all-by-exercice")
    public ResponseEntity<List<AvisDacDTO>> listAvisDacByExercice(@RequestParam(name = "exerciceId") Long exerciceId) {
        return ResponseEntity.ok(  ).body( avisDacService.listAvisDacByExercice( exerciceId));
    }

    @GetMapping("/avis-dacs/all-by-activite")
    public ResponseEntity<List<AvisDacDTO>> listAvisDacByActivite(@RequestParam(name = "activiteId") Long activiteId){
        return ResponseEntity.ok(   ).body(avisDacService.listAvisDacByActivite(activiteId));
    }

    @PutMapping("/avis-dacs/updateListe")
    public ResponseEntity<List<AvisDacDTO>> updateaAll(@RequestBody List<AvisDacDTO> avisDacDTOS){
        return ResponseEntity.ok(avisDacService.updateAll(avisDacDTOS));
    }


}
