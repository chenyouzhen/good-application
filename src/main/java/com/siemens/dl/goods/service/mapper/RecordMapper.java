package com.siemens.dl.goods.service.mapper;


import com.siemens.dl.goods.domain.*;
import com.siemens.dl.goods.service.dto.RecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Record} and its DTO {@link RecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {AssemblyLineMapper.class})
public interface RecordMapper extends EntityMapper<RecordDTO, Record> {

    @Mapping(source = "assemblyLine.id", target = "assemblyLineId")
    RecordDTO toDto(Record record);

    @Mapping(source = "assemblyLineId", target = "assemblyLine")
    Record toEntity(RecordDTO recordDTO);

    default Record fromId(Long id) {
        if (id == null) {
            return null;
        }
        Record record = new Record();
        record.setId(id);
        return record;
    }
}
