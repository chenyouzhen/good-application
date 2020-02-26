package com.siemens.dl.goods.web.rest;

import com.siemens.dl.goods.GoodApplicationApp;
import com.siemens.dl.goods.domain.Mapping;
import com.siemens.dl.goods.repository.MappingRepository;
import com.siemens.dl.goods.service.MappingService;
import com.siemens.dl.goods.service.dto.MappingDTO;
import com.siemens.dl.goods.service.mapper.MappingMapper;
import com.siemens.dl.goods.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.siemens.dl.goods.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MappingResource} REST controller.
 */
@SpringBootTest(classes = GoodApplicationApp.class)
public class MappingResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MappingRepository mappingRepository;

    @Autowired
    private MappingMapper mappingMapper;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMappingMockMvc;

    private Mapping mapping;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MappingResource mappingResource = new MappingResource(mappingService);
        this.restMappingMockMvc = MockMvcBuilders.standaloneSetup(mappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mapping createEntity(EntityManager em) {
        Mapping mapping = new Mapping()
            .name(DEFAULT_NAME)
            .deviceId(DEFAULT_DEVICE_ID)
            .description(DEFAULT_DESCRIPTION);
        return mapping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mapping createUpdatedEntity(EntityManager em) {
        Mapping mapping = new Mapping()
            .name(UPDATED_NAME)
            .deviceId(UPDATED_DEVICE_ID)
            .description(UPDATED_DESCRIPTION);
        return mapping;
    }

    @BeforeEach
    public void initTest() {
        mapping = createEntity(em);
    }

    @Test
    @Transactional
    public void createMapping() throws Exception {
        int databaseSizeBeforeCreate = mappingRepository.findAll().size();

        // Create the Mapping
        MappingDTO mappingDTO = mappingMapper.toDto(mapping);
        restMappingMockMvc.perform(post("/api/mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mappingDTO)))
            .andExpect(status().isCreated());

        // Validate the Mapping in the database
        List<Mapping> mappingList = mappingRepository.findAll();
        assertThat(mappingList).hasSize(databaseSizeBeforeCreate + 1);
        Mapping testMapping = mappingList.get(mappingList.size() - 1);
        assertThat(testMapping.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMapping.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testMapping.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mappingRepository.findAll().size();

        // Create the Mapping with an existing ID
        mapping.setId(1L);
        MappingDTO mappingDTO = mappingMapper.toDto(mapping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMappingMockMvc.perform(post("/api/mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mapping in the database
        List<Mapping> mappingList = mappingRepository.findAll();
        assertThat(mappingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDeviceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mappingRepository.findAll().size();
        // set the field null
        mapping.setDeviceId(null);

        // Create the Mapping, which fails.
        MappingDTO mappingDTO = mappingMapper.toDto(mapping);

        restMappingMockMvc.perform(post("/api/mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mappingDTO)))
            .andExpect(status().isBadRequest());

        List<Mapping> mappingList = mappingRepository.findAll();
        assertThat(mappingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMappings() throws Exception {
        // Initialize the database
        mappingRepository.saveAndFlush(mapping);

        // Get all the mappingList
        restMappingMockMvc.perform(get("/api/mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].deviceId").value(hasItem(DEFAULT_DEVICE_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getMapping() throws Exception {
        // Initialize the database
        mappingRepository.saveAndFlush(mapping);

        // Get the mapping
        restMappingMockMvc.perform(get("/api/mappings/{id}", mapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mapping.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.deviceId").value(DEFAULT_DEVICE_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingMapping() throws Exception {
        // Get the mapping
        restMappingMockMvc.perform(get("/api/mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMapping() throws Exception {
        // Initialize the database
        mappingRepository.saveAndFlush(mapping);

        int databaseSizeBeforeUpdate = mappingRepository.findAll().size();

        // Update the mapping
        Mapping updatedMapping = mappingRepository.findById(mapping.getId()).get();
        // Disconnect from session so that the updates on updatedMapping are not directly saved in db
        em.detach(updatedMapping);
        updatedMapping
            .name(UPDATED_NAME)
            .deviceId(UPDATED_DEVICE_ID)
            .description(UPDATED_DESCRIPTION);
        MappingDTO mappingDTO = mappingMapper.toDto(updatedMapping);

        restMappingMockMvc.perform(put("/api/mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mappingDTO)))
            .andExpect(status().isOk());

        // Validate the Mapping in the database
        List<Mapping> mappingList = mappingRepository.findAll();
        assertThat(mappingList).hasSize(databaseSizeBeforeUpdate);
        Mapping testMapping = mappingList.get(mappingList.size() - 1);
        assertThat(testMapping.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMapping.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testMapping.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMapping() throws Exception {
        int databaseSizeBeforeUpdate = mappingRepository.findAll().size();

        // Create the Mapping
        MappingDTO mappingDTO = mappingMapper.toDto(mapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMappingMockMvc.perform(put("/api/mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mapping in the database
        List<Mapping> mappingList = mappingRepository.findAll();
        assertThat(mappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMapping() throws Exception {
        // Initialize the database
        mappingRepository.saveAndFlush(mapping);

        int databaseSizeBeforeDelete = mappingRepository.findAll().size();

        // Delete the mapping
        restMappingMockMvc.perform(delete("/api/mappings/{id}", mapping.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mapping> mappingList = mappingRepository.findAll();
        assertThat(mappingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
