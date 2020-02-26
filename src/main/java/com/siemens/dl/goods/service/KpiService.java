package com.siemens.dl.goods.service;

import com.siemens.dl.goods.service.dto.KpiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.siemens.dl.goods.domain.Kpi}.
 */
public interface KpiService {

    /**
     * Save a kpi.
     *
     * @param kpiDTO the entity to save.
     * @return the persisted entity.
     */
    KpiDTO save(KpiDTO kpiDTO);

    /**
     * Get all the kpis.
     *
     * @return the list of entities.
     */
    List<KpiDTO> findAll();

    /**
     * Get the "id" kpi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KpiDTO> findOne(Long id);

    /**
     * Delete the "id" kpi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
