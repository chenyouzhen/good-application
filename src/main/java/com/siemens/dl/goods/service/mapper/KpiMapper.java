package com.siemens.dl.goods.service.mapper;


import com.siemens.dl.goods.domain.*;
import com.siemens.dl.goods.service.dto.KpiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Kpi} and its DTO {@link KpiDTO}.
 */
@Mapper(componentModel = "spring", uses = {FactoryMapper.class, ProductMapper.class})
public interface KpiMapper extends EntityMapper<KpiDTO, Kpi> {

    @Mapping(source = "factory.id", target = "factoryId")
    @Mapping(source = "product.id", target = "productId")
    KpiDTO toDto(Kpi kpi);

    @Mapping(source = "factoryId", target = "factory")
    @Mapping(source = "productId", target = "product")
    Kpi toEntity(KpiDTO kpiDTO);

    default Kpi fromId(Long id) {
        if (id == null) {
            return null;
        }
        Kpi kpi = new Kpi();
        kpi.setId(id);
        return kpi;
    }
}
