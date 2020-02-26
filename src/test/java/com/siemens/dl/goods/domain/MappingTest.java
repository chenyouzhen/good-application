package com.siemens.dl.goods.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.siemens.dl.goods.web.rest.TestUtil;

public class MappingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mapping.class);
        Mapping mapping1 = new Mapping();
        mapping1.setId(1L);
        Mapping mapping2 = new Mapping();
        mapping2.setId(mapping1.getId());
        assertThat(mapping1).isEqualTo(mapping2);
        mapping2.setId(2L);
        assertThat(mapping1).isNotEqualTo(mapping2);
        mapping1.setId(null);
        assertThat(mapping1).isNotEqualTo(mapping2);
    }
}
