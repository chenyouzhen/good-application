package com.siemens.dl.goods.service.impl;

import com.siemens.dl.goods.service.MappingService;
import com.siemens.dl.goods.domain.Mapping;
import com.siemens.dl.goods.repository.MappingRepository;
import com.siemens.dl.goods.service.dto.MappingDTO;
import com.siemens.dl.goods.service.mapper.MappingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Mapping}.
 */
@Service
@Transactional
public class MappingServiceImpl implements MappingService {

    private final Logger log = LoggerFactory.getLogger(MappingServiceImpl.class);

    private final MappingRepository mappingRepository;

    private final MappingMapper mappingMapper;

    public MappingServiceImpl(MappingRepository mappingRepository, MappingMapper mappingMapper) {
        this.mappingRepository = mappingRepository;
        this.mappingMapper = mappingMapper;
    }

    /**
     * Save a mapping.
     *
     * @param mappingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MappingDTO save(MappingDTO mappingDTO) {
        log.debug("Request to save Mapping : {}", mappingDTO);
        Mapping mapping = mappingMapper.toEntity(mappingDTO);
        mapping = mappingRepository.save(mapping);
        return mappingMapper.toDto(mapping);
    }

    /**
     * Get all the mappings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MappingDTO> findAll() {
        log.debug("Request to get all Mappings");
        return mappingRepository.findAll().stream()
            .map(mappingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mapping by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MappingDTO> findOne(Long id) {
        log.debug("Request to get Mapping : {}", id);
        return mappingRepository.findById(id)
            .map(mappingMapper::toDto);
    }

    /**
     * Delete the mapping by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mapping : {}", id);
        mappingRepository.deleteById(id);
    }
}
