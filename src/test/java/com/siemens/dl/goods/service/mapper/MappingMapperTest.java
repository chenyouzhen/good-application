package com.siemens.dl.goods.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MappingMapperTest {

    private MappingMapper mappingMapper;

    @BeforeEach
    public void setUp() {
        mappingMapper = new MappingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mappingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mappingMapper.fromId(null)).isNull();
    }
}
