package com.siemens.dl.goods.service;

import com.siemens.dl.goods.service.dto.MappingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.siemens.dl.goods.domain.Mapping}.
 */
public interface MappingService {

    /**
     * Save a mapping.
     *
     * @param mappingDTO the entity to save.
     * @return the persisted entity.
     */
    MappingDTO save(MappingDTO mappingDTO);

    /**
     * Get all the mappings.
     *
     * @return the list of entities.
     */
    List<MappingDTO> findAll();

    /**
     * Get the "id" mapping.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MappingDTO> findOne(Long id);

    /**
     * Delete the "id" mapping.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
