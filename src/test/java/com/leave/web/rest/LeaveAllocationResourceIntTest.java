package com.leave.web.rest;

import com.leave.LeaveManagementSystemApp;

import com.leave.domain.LeaveAllocation;
import com.leave.repository.LeaveAllocationRepository;
import com.leave.service.LeaveAllocationService;
import com.leave.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LeaveAllocationResource REST controller.
 *
 * @see LeaveAllocationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeaveManagementSystemApp.class)
public class LeaveAllocationResourceIntTest {

    private static final Boolean DEFAULT_TEACHING = false;
    private static final Boolean UPDATED_TEACHING = true;

    private static final Boolean DEFAULT_CAN_HAVE_VACATION = false;
    private static final Boolean UPDATED_CAN_HAVE_VACATION = true;

    private static final Boolean DEFAULT_GRANTED = false;
    private static final Boolean UPDATED_GRANTED = true;

    private static final Double DEFAULT_NO_OF_LEAVES = 1D;
    private static final Double UPDATED_NO_OF_LEAVES = 2D;

    private static final LocalDate DEFAULT_ALLOCATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ALLOCATION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private LeaveAllocationRepository leaveAllocationRepository;

    @Autowired
    private LeaveAllocationService leaveAllocationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLeaveAllocationMockMvc;

    private LeaveAllocation leaveAllocation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LeaveAllocationResource leaveAllocationResource = new LeaveAllocationResource(leaveAllocationService);
        this.restLeaveAllocationMockMvc = MockMvcBuilders.standaloneSetup(leaveAllocationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaveAllocation createEntity(EntityManager em) {
        LeaveAllocation leaveAllocation = new LeaveAllocation()
            .teaching(DEFAULT_TEACHING)
            .canHaveVacation(DEFAULT_CAN_HAVE_VACATION)
            .granted(DEFAULT_GRANTED)
            .noOfLeaves(DEFAULT_NO_OF_LEAVES)
            .allocationDate(DEFAULT_ALLOCATION_DATE);
        return leaveAllocation;
    }

    @Before
    public void initTest() {
        leaveAllocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createLeaveAllocation() throws Exception {
        int databaseSizeBeforeCreate = leaveAllocationRepository.findAll().size();

        // Create the LeaveAllocation
        restLeaveAllocationMockMvc.perform(post("/api/leave-allocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveAllocation)))
            .andExpect(status().isCreated());

        // Validate the LeaveAllocation in the database
        List<LeaveAllocation> leaveAllocationList = leaveAllocationRepository.findAll();
        assertThat(leaveAllocationList).hasSize(databaseSizeBeforeCreate + 1);
        LeaveAllocation testLeaveAllocation = leaveAllocationList.get(leaveAllocationList.size() - 1);
        assertThat(testLeaveAllocation.isTeaching()).isEqualTo(DEFAULT_TEACHING);
        assertThat(testLeaveAllocation.isCanHaveVacation()).isEqualTo(DEFAULT_CAN_HAVE_VACATION);
        assertThat(testLeaveAllocation.isGranted()).isEqualTo(DEFAULT_GRANTED);
        assertThat(testLeaveAllocation.getNoOfLeaves()).isEqualTo(DEFAULT_NO_OF_LEAVES);
        assertThat(testLeaveAllocation.getAllocationDate()).isEqualTo(DEFAULT_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void createLeaveAllocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leaveAllocationRepository.findAll().size();

        // Create the LeaveAllocation with an existing ID
        leaveAllocation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaveAllocationMockMvc.perform(post("/api/leave-allocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveAllocation)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<LeaveAllocation> leaveAllocationList = leaveAllocationRepository.findAll();
        assertThat(leaveAllocationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLeaveAllocations() throws Exception {
        // Initialize the database
        leaveAllocationRepository.saveAndFlush(leaveAllocation);

        // Get all the leaveAllocationList
        restLeaveAllocationMockMvc.perform(get("/api/leave-allocations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaveAllocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].teaching").value(hasItem(DEFAULT_TEACHING.booleanValue())))
            .andExpect(jsonPath("$.[*].canHaveVacation").value(hasItem(DEFAULT_CAN_HAVE_VACATION.booleanValue())))
            .andExpect(jsonPath("$.[*].granted").value(hasItem(DEFAULT_GRANTED.booleanValue())))
            .andExpect(jsonPath("$.[*].noOfLeaves").value(hasItem(DEFAULT_NO_OF_LEAVES.doubleValue())))
            .andExpect(jsonPath("$.[*].allocationDate").value(hasItem(DEFAULT_ALLOCATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getLeaveAllocation() throws Exception {
        // Initialize the database
        leaveAllocationRepository.saveAndFlush(leaveAllocation);

        // Get the leaveAllocation
        restLeaveAllocationMockMvc.perform(get("/api/leave-allocations/{id}", leaveAllocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(leaveAllocation.getId().intValue()))
            .andExpect(jsonPath("$.teaching").value(DEFAULT_TEACHING.booleanValue()))
            .andExpect(jsonPath("$.canHaveVacation").value(DEFAULT_CAN_HAVE_VACATION.booleanValue()))
            .andExpect(jsonPath("$.granted").value(DEFAULT_GRANTED.booleanValue()))
            .andExpect(jsonPath("$.noOfLeaves").value(DEFAULT_NO_OF_LEAVES.doubleValue()))
            .andExpect(jsonPath("$.allocationDate").value(DEFAULT_ALLOCATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLeaveAllocation() throws Exception {
        // Get the leaveAllocation
        restLeaveAllocationMockMvc.perform(get("/api/leave-allocations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLeaveAllocation() throws Exception {
        // Initialize the database
        leaveAllocationService.save(leaveAllocation);

        int databaseSizeBeforeUpdate = leaveAllocationRepository.findAll().size();

        // Update the leaveAllocation
        LeaveAllocation updatedLeaveAllocation = leaveAllocationRepository.findOne(leaveAllocation.getId());
        updatedLeaveAllocation
            .teaching(UPDATED_TEACHING)
            .canHaveVacation(UPDATED_CAN_HAVE_VACATION)
            .granted(UPDATED_GRANTED)
            .noOfLeaves(UPDATED_NO_OF_LEAVES)
            .allocationDate(UPDATED_ALLOCATION_DATE);

        restLeaveAllocationMockMvc.perform(put("/api/leave-allocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLeaveAllocation)))
            .andExpect(status().isOk());

        // Validate the LeaveAllocation in the database
        List<LeaveAllocation> leaveAllocationList = leaveAllocationRepository.findAll();
        assertThat(leaveAllocationList).hasSize(databaseSizeBeforeUpdate);
        LeaveAllocation testLeaveAllocation = leaveAllocationList.get(leaveAllocationList.size() - 1);
        assertThat(testLeaveAllocation.isTeaching()).isEqualTo(UPDATED_TEACHING);
        assertThat(testLeaveAllocation.isCanHaveVacation()).isEqualTo(UPDATED_CAN_HAVE_VACATION);
        assertThat(testLeaveAllocation.isGranted()).isEqualTo(UPDATED_GRANTED);
        assertThat(testLeaveAllocation.getNoOfLeaves()).isEqualTo(UPDATED_NO_OF_LEAVES);
        assertThat(testLeaveAllocation.getAllocationDate()).isEqualTo(UPDATED_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLeaveAllocation() throws Exception {
        int databaseSizeBeforeUpdate = leaveAllocationRepository.findAll().size();

        // Create the LeaveAllocation

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLeaveAllocationMockMvc.perform(put("/api/leave-allocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveAllocation)))
            .andExpect(status().isCreated());

        // Validate the LeaveAllocation in the database
        List<LeaveAllocation> leaveAllocationList = leaveAllocationRepository.findAll();
        assertThat(leaveAllocationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLeaveAllocation() throws Exception {
        // Initialize the database
        leaveAllocationService.save(leaveAllocation);

        int databaseSizeBeforeDelete = leaveAllocationRepository.findAll().size();

        // Get the leaveAllocation
        restLeaveAllocationMockMvc.perform(delete("/api/leave-allocations/{id}", leaveAllocation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LeaveAllocation> leaveAllocationList = leaveAllocationRepository.findAll();
        assertThat(leaveAllocationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaveAllocation.class);
        LeaveAllocation leaveAllocation1 = new LeaveAllocation();
        leaveAllocation1.setId(1L);
        LeaveAllocation leaveAllocation2 = new LeaveAllocation();
        leaveAllocation2.setId(leaveAllocation1.getId());
        assertThat(leaveAllocation1).isEqualTo(leaveAllocation2);
        leaveAllocation2.setId(2L);
        assertThat(leaveAllocation1).isNotEqualTo(leaveAllocation2);
        leaveAllocation1.setId(null);
        assertThat(leaveAllocation1).isNotEqualTo(leaveAllocation2);
    }
}
