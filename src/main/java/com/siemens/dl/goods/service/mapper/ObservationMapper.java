package com.siemens.dl.goods.service.mapper;


import com.siemens.dl.goods.domain.*;
import com.siemens.dl.goods.service.dto.ObservationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Observation} and its DTO {@link ObservationDTO}.
 */
@Mapper(componentModel = "spring", uses = {AssemblyLineMapper.class, MappingMapper.class})
public interface ObservationMapper extends EntityMapper<ObservationDTO, Observation> {

    @Mapping(source = "assemblyLine.id", target = "assemblyLineId")
    @Mapping(source = "mapping.id", target = "mappingId")
    ObservationDTO toDto(Observation observation);

    @Mapping(source = "assemblyLineId", target = "assemblyLine")
    @Mapping(source = "mappingId", target = "mapping")
    Observation toEntity(ObservationDTO observationDTO);

    default Observation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Observation observation = new Observation();
        observation.setId(id);
        return observation;
    }
}
