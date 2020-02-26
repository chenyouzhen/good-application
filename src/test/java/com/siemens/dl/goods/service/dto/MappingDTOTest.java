package com.siemens.dl.goods.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.siemens.dl.goods.web.rest.TestUtil;

public class MappingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MappingDTO.class);
        MappingDTO mappingDTO1 = new MappingDTO();
        mappingDTO1.setId(1L);
        MappingDTO mappingDTO2 = new MappingDTO();
        assertThat(mappingDTO1).isNotEqualTo(mappingDTO2);
        mappingDTO2.setId(mappingDTO1.getId());
        assertThat(mappingDTO1).isEqualTo(mappingDTO2);
        mappingDTO2.setId(2L);
        assertThat(mappingDTO1).isNotEqualTo(mappingDTO2);
        mappingDTO1.setId(null);
        assertThat(mappingDTO1).isNotEqualTo(mappingDTO2);
    }
}
