package com.siemens.dl.goods.service.impl;

import com.siemens.dl.goods.service.AssemblyLineService;
import com.siemens.dl.goods.domain.AssemblyLine;
import com.siemens.dl.goods.repository.AssemblyLineRepository;
import com.siemens.dl.goods.service.dto.AssemblyLineDTO;
import com.siemens.dl.goods.service.mapper.AssemblyLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AssemblyLine}.
 */
@Service
@Transactional
public class AssemblyLineServiceImpl implements AssemblyLineService {

    private final Logger log = LoggerFactory.getLogger(AssemblyLineServiceImpl.class);

    private final AssemblyLineRepository assemblyLineRepository;

    private final AssemblyLineMapper assemblyLineMapper;

    public AssemblyLineServiceImpl(AssemblyLineRepository assemblyLineRepository, AssemblyLineMapper assemblyLineMapper) {
        this.assemblyLineRepository = assemblyLineRepository;
        this.assemblyLineMapper = assemblyLineMapper;
    }

    /**
     * Save a assemblyLine.
     *
     * @param assemblyLineDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AssemblyLineDTO save(AssemblyLineDTO assemblyLineDTO) {
        log.debug("Request to save AssemblyLine : {}", assemblyLineDTO);
        AssemblyLine assemblyLine = assemblyLineMapper.toEntity(assemblyLineDTO);
        assemblyLine = assemblyLineRepository.save(assemblyLine);
        return assemblyLineMapper.toDto(assemblyLine);
    }

    /**
     * Get all the assemblyLines.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AssemblyLineDTO> findAll() {
        log.debug("Request to get all AssemblyLines");
        return assemblyLineRepository.findAll().stream()
            .map(assemblyLineMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one assemblyLine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AssemblyLineDTO> findOne(Long id) {
        log.debug("Request to get AssemblyLine : {}", id);
        return assemblyLineRepository.findById(id)
            .map(assemblyLineMapper::toDto);
    }

    /**
     * Delete the assemblyLine by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AssemblyLine : {}", id);
        assemblyLineRepository.deleteById(id);
    }
}
