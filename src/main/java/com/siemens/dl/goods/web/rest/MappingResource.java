package com.siemens.dl.goods.web.rest;

import com.siemens.dl.goods.service.MappingService;
import com.siemens.dl.goods.web.rest.errors.BadRequestAlertException;
import com.siemens.dl.goods.service.dto.MappingDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.siemens.dl.goods.domain.Mapping}.
 */
@RestController
@RequestMapping("/api")
public class MappingResource {

    private final Logger log = LoggerFactory.getLogger(MappingResource.class);

    private static final String ENTITY_NAME = "mapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MappingService mappingService;

    public MappingResource(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    /**
     * {@code POST  /mappings} : Create a new mapping.
     *
     * @param mappingDTO the mappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mappingDTO, or with status {@code 400 (Bad Request)} if the mapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mappings")
    public ResponseEntity<MappingDTO> createMapping(@Valid @RequestBody MappingDTO mappingDTO) throws URISyntaxException {
        log.debug("REST request to save Mapping : {}", mappingDTO);
        if (mappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new mapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MappingDTO result = mappingService.save(mappingDTO);
        return ResponseEntity.created(new URI("/api/mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mappings} : Updates an existing mapping.
     *
     * @param mappingDTO the mappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mappingDTO,
     * or with status {@code 400 (Bad Request)} if the mappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mappings")
    public ResponseEntity<MappingDTO> updateMapping(@Valid @RequestBody MappingDTO mappingDTO) throws URISyntaxException {
        log.debug("REST request to update Mapping : {}", mappingDTO);
        if (mappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MappingDTO result = mappingService.save(mappingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mappings} : get all the mappings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mappings in body.
     */
    @GetMapping("/mappings")
    public List<MappingDTO> getAllMappings() {
        log.debug("REST request to get all Mappings");
        return mappingService.findAll();
    }

    /**
     * {@code GET  /mappings/:id} : get the "id" mapping.
     *
     * @param id the id of the mappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mappings/{id}")
    public ResponseEntity<MappingDTO> getMapping(@PathVariable Long id) {
        log.debug("REST request to get Mapping : {}", id);
        Optional<MappingDTO> mappingDTO = mappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mappingDTO);
    }

    /**
     * {@code DELETE  /mappings/:id} : delete the "id" mapping.
     *
     * @param id the id of the mappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mappings/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable Long id) {
        log.debug("REST request to delete Mapping : {}", id);
        mappingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
