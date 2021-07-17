package mena.gov.bf.web.rest;

import mena.gov.bf.service.DelaiMessageService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.DelaiMessageDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.DelaiMessage}.
 */
@RestController
@RequestMapping("/api")
public class DelaiMessageResource {

    private final Logger log = LoggerFactory.getLogger(DelaiMessageResource.class);

    private static final String ENTITY_NAME = "microservicedaccamDelaiMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DelaiMessageService delaiMessageService;

    public DelaiMessageResource(DelaiMessageService delaiMessageService) {
        this.delaiMessageService = delaiMessageService;
    }

    /**
     * {@code POST  /delai-messages} : Create a new delaiMessage.
     *
     * @param delaiMessageDTO the delaiMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new delaiMessageDTO, or with status {@code 400 (Bad Request)} if the delaiMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delai-messages")
    public ResponseEntity<DelaiMessageDTO> createDelaiMessage(@Valid @RequestBody DelaiMessageDTO delaiMessageDTO) throws URISyntaxException {
        log.debug("REST request to save DelaiMessage : {}", delaiMessageDTO);
        if (delaiMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new delaiMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DelaiMessageDTO result = delaiMessageService.save(delaiMessageDTO);
        return ResponseEntity.created(new URI("/api/delai-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delai-messages} : Updates an existing delaiMessage.
     *
     * @param delaiMessageDTO the delaiMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated delaiMessageDTO,
     * or with status {@code 400 (Bad Request)} if the delaiMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the delaiMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delai-messages")
    public ResponseEntity<DelaiMessageDTO> updateDelaiMessage(@Valid @RequestBody DelaiMessageDTO delaiMessageDTO) throws URISyntaxException {
        log.debug("REST request to update DelaiMessage : {}", delaiMessageDTO);
        if (delaiMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DelaiMessageDTO result = delaiMessageService.save(delaiMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, delaiMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /delai-messages} : get all the delaiMessages.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of delaiMessages in body.
     */
    @GetMapping("/delai-messages")
    public ResponseEntity<List<DelaiMessageDTO>> getAllDelaiMessages(Pageable pageable) {
        log.debug("REST request to get a page of DelaiMessages");
        Page<DelaiMessageDTO> page = delaiMessageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delai-messages/:id} : get the "id" delaiMessage.
     *
     * @param id the id of the delaiMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the delaiMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delai-messages/{id}")
    public ResponseEntity<DelaiMessageDTO> getDelaiMessage(@PathVariable Long id) {
        log.debug("REST request to get DelaiMessage : {}", id);
        Optional<DelaiMessageDTO> delaiMessageDTO = delaiMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(delaiMessageDTO);
    }

    /**
     * {@code DELETE  /delai-messages/:id} : delete the "id" delaiMessage.
     *
     * @param id the id of the delaiMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delai-messages/{id}")
    public ResponseEntity<Void> deleteDelaiMessage(@PathVariable Long id) {
        log.debug("REST request to delete DelaiMessage : {}", id);
        delaiMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/delai-messages/type")
    public ResponseEntity<List<DelaiMessageDTO>> findByType() {
        return ResponseEntity.ok(delaiMessageService.findByType());
    }

    @GetMapping("/delai-messages/message")
    public ResponseEntity<List<DelaiMessageDTO>> findByMessage() {
        return ResponseEntity.ok(delaiMessageService.findByMessage());
    }

    @GetMapping("/delai-messages/all")
    public ResponseEntity<List<DelaiMessageDTO>> getAllDelaiMessage() {

        return ResponseEntity.ok(delaiMessageService.getAllDelaiMassage());
    }
}
