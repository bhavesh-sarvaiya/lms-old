package com.leave.web.rest;

import com.leave.LeaveManagementSystemApp;

import com.leave.domain.LeaveApplicationHistory;
import com.leave.repository.LeaveApplicationHistoryRepository;
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

import com.leave.domain.enumeration.Status;
/**
 * Test class for the LeaveApplicationHistoryResource REST controller.
 *
 * @see LeaveApplicationHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeaveManagementSystemApp.class)
public class LeaveApplicationHistoryResourceIntTest {

    private static final Status DEFAULT_STATUS = Status.APPROVED;
    private static final Status UPDATED_STATUS = Status.REJECTED;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ACTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private LeaveApplicationHistoryRepository leaveApplicationHistoryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLeaveApplicationHistoryMockMvc;

    private LeaveApplicationHistory leaveApplicationHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LeaveApplicationHistoryResource leaveApplicationHistoryResource = new LeaveApplicationHistoryResource(leaveApplicationHistoryRepository);
        this.restLeaveApplicationHistoryMockMvc = MockMvcBuilders.standaloneSetup(leaveApplicationHistoryResource)
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
    public static LeaveApplicationHistory createEntity(EntityManager em) {
        LeaveApplicationHistory leaveApplicationHistory = new LeaveApplicationHistory()
            .status(DEFAULT_STATUS)
            .comment(DEFAULT_COMMENT)
            .actionDate(DEFAULT_ACTION_DATE);
        return leaveApplicationHistory;
    }

    @Before
    public void initTest() {
        leaveApplicationHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createLeaveApplicationHistory() throws Exception {
        int databaseSizeBeforeCreate = leaveApplicationHistoryRepository.findAll().size();

        // Create the LeaveApplicationHistory
        restLeaveApplicationHistoryMockMvc.perform(post("/api/leave-application-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveApplicationHistory)))
            .andExpect(status().isCreated());

        // Validate the LeaveApplicationHistory in the database
        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        LeaveApplicationHistory testLeaveApplicationHistory = leaveApplicationHistoryList.get(leaveApplicationHistoryList.size() - 1);
        assertThat(testLeaveApplicationHistory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLeaveApplicationHistory.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testLeaveApplicationHistory.getActionDate()).isEqualTo(DEFAULT_ACTION_DATE);
    }

    @Test
    @Transactional
    public void createLeaveApplicationHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leaveApplicationHistoryRepository.findAll().size();

        // Create the LeaveApplicationHistory with an existing ID
        leaveApplicationHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaveApplicationHistoryMockMvc.perform(post("/api/leave-application-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveApplicationHistory)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = leaveApplicationHistoryRepository.findAll().size();
        // set the field null
        leaveApplicationHistory.setStatus(null);

        // Create the LeaveApplicationHistory, which fails.

        restLeaveApplicationHistoryMockMvc.perform(post("/api/leave-application-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveApplicationHistory)))
            .andExpect(status().isBadRequest());

        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommentIsRequired() throws Exception {
        int databaseSizeBeforeTest = leaveApplicationHistoryRepository.findAll().size();
        // set the field null
        leaveApplicationHistory.setComment(null);

        // Create the LeaveApplicationHistory, which fails.

        restLeaveApplicationHistoryMockMvc.perform(post("/api/leave-application-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveApplicationHistory)))
            .andExpect(status().isBadRequest());

        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = leaveApplicationHistoryRepository.findAll().size();
        // set the field null
        leaveApplicationHistory.setActionDate(null);

        // Create the LeaveApplicationHistory, which fails.

        restLeaveApplicationHistoryMockMvc.perform(post("/api/leave-application-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveApplicationHistory)))
            .andExpect(status().isBadRequest());

        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLeaveApplicationHistories() throws Exception {
        // Initialize the database
        leaveApplicationHistoryRepository.saveAndFlush(leaveApplicationHistory);

        // Get all the leaveApplicationHistoryList
        restLeaveApplicationHistoryMockMvc.perform(get("/api/leave-application-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaveApplicationHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].actionDate").value(hasItem(DEFAULT_ACTION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getLeaveApplicationHistory() throws Exception {
        // Initialize the database
        leaveApplicationHistoryRepository.saveAndFlush(leaveApplicationHistory);

        // Get the leaveApplicationHistory
        restLeaveApplicationHistoryMockMvc.perform(get("/api/leave-application-histories/{id}", leaveApplicationHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(leaveApplicationHistory.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.actionDate").value(DEFAULT_ACTION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLeaveApplicationHistory() throws Exception {
        // Get the leaveApplicationHistory
        restLeaveApplicationHistoryMockMvc.perform(get("/api/leave-application-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLeaveApplicationHistory() throws Exception {
        // Initialize the database
        leaveApplicationHistoryRepository.saveAndFlush(leaveApplicationHistory);
        int databaseSizeBeforeUpdate = leaveApplicationHistoryRepository.findAll().size();

        // Update the leaveApplicationHistory
        LeaveApplicationHistory updatedLeaveApplicationHistory = leaveApplicationHistoryRepository.findOne(leaveApplicationHistory.getId());
        updatedLeaveApplicationHistory
            .status(UPDATED_STATUS)
            .comment(UPDATED_COMMENT)
            .actionDate(UPDATED_ACTION_DATE);

        restLeaveApplicationHistoryMockMvc.perform(put("/api/leave-application-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLeaveApplicationHistory)))
            .andExpect(status().isOk());

        // Validate the LeaveApplicationHistory in the database
        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeUpdate);
        LeaveApplicationHistory testLeaveApplicationHistory = leaveApplicationHistoryList.get(leaveApplicationHistoryList.size() - 1);
        assertThat(testLeaveApplicationHistory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLeaveApplicationHistory.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testLeaveApplicationHistory.getActionDate()).isEqualTo(UPDATED_ACTION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLeaveApplicationHistory() throws Exception {
        int databaseSizeBeforeUpdate = leaveApplicationHistoryRepository.findAll().size();

        // Create the LeaveApplicationHistory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLeaveApplicationHistoryMockMvc.perform(put("/api/leave-application-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaveApplicationHistory)))
            .andExpect(status().isCreated());

        // Validate the LeaveApplicationHistory in the database
        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLeaveApplicationHistory() throws Exception {
        // Initialize the database
        leaveApplicationHistoryRepository.saveAndFlush(leaveApplicationHistory);
        int databaseSizeBeforeDelete = leaveApplicationHistoryRepository.findAll().size();

        // Get the leaveApplicationHistory
        restLeaveApplicationHistoryMockMvc.perform(delete("/api/leave-application-histories/{id}", leaveApplicationHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LeaveApplicationHistory> leaveApplicationHistoryList = leaveApplicationHistoryRepository.findAll();
        assertThat(leaveApplicationHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaveApplicationHistory.class);
        LeaveApplicationHistory leaveApplicationHistory1 = new LeaveApplicationHistory();
        leaveApplicationHistory1.setId(1L);
        LeaveApplicationHistory leaveApplicationHistory2 = new LeaveApplicationHistory();
        leaveApplicationHistory2.setId(leaveApplicationHistory1.getId());
        assertThat(leaveApplicationHistory1).isEqualTo(leaveApplicationHistory2);
        leaveApplicationHistory2.setId(2L);
        assertThat(leaveApplicationHistory1).isNotEqualTo(leaveApplicationHistory2);
        leaveApplicationHistory1.setId(null);
        assertThat(leaveApplicationHistory1).isNotEqualTo(leaveApplicationHistory2);
    }
}
