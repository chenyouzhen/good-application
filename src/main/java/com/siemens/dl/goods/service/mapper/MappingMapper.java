package com.siemens.dl.goods.service.mapper;


import com.siemens.dl.goods.domain.*;
import com.siemens.dl.goods.service.dto.MappingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mapping} and its DTO {@link MappingDTO}.
 */
@Mapper(componentModel = "spring", uses = {AssemblyLineMapper.class})
public interface MappingMapper extends EntityMapper<MappingDTO, Mapping> {

    @Mapping(source = "assemblyLine.id", target = "assemblyLineId")
    MappingDTO toDto(Mapping mapping);

    @Mapping(target = "observations", ignore = true)
    @Mapping(target = "removeObservation", ignore = true)
    @Mapping(source = "assemblyLineId", target = "assemblyLine")
    Mapping toEntity(MappingDTO mappingDTO);

    default Mapping fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mapping mapping = new Mapping();
        mapping.setId(id);
        return mapping;
    }
}
