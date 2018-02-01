package com.leave.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.leave.domain.LeaveApplicationHistory;

import com.leave.repository.LeaveApplicationHistoryRepository;
import com.leave.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LeaveApplicationHistory.
 */
@RestController
@RequestMapping("/api")
public class LeaveApplicationHistoryResource {

    private final Logger log = LoggerFactory.getLogger(LeaveApplicationHistoryResource.class);

    private static final String ENTITY_NAME = "leaveApplicationHistory";
        
    private final LeaveApplicationHistoryRepository leaveApplicationHistoryRepository;

    public LeaveApplicationHistoryResource(LeaveApplicationHistoryRepository leaveApplicationHistoryRepository) {
        this.leaveApplicationHistoryRepository = leaveApplicationHistoryRepository;
    }

    /**
     * POST  /leave-application-histories : Create a new leaveApplicationHistory.
     *
     * @param leaveApplicationHistory the leaveApplicationHistory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveApplicationHistory, or with status 400 (Bad Request) if the leaveApplicationHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-application-histories")
    @Timed
    public ResponseEntity<LeaveApplicationHistory> createLeaveApplicationHistory(@Valid @RequestBody LeaveApplicationHistory leaveApplicationHistory) throws URISyntaxException {
        log.debug("REST request to save LeaveApplicationHistory : {}", leaveApplicationHistory);
        if (leaveApplicationHistory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new leaveApplicationHistory cannot already have an ID")).body(null);
        }
        LeaveApplicationHistory result = leaveApplicationHistoryRepository.save(leaveApplicationHistory);
        return ResponseEntity.created(new URI("/api/leave-application-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /leave-application-histories : Updates an existing leaveApplicationHistory.
     *
     * @param leaveApplicationHistory the leaveApplicationHistory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveApplicationHistory,
     * or with status 400 (Bad Request) if the leaveApplicationHistory is not valid,
     * or with status 500 (Internal Server Error) if the leaveApplicationHistory couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-application-histories")
    @Timed
    public ResponseEntity<LeaveApplicationHistory> updateLeaveApplicationHistory(@Valid @RequestBody LeaveApplicationHistory leaveApplicationHistory) throws URISyntaxException {
        log.debug("REST request to update LeaveApplicationHistory : {}", leaveApplicationHistory);
        if (leaveApplicationHistory.getId() == null) {
            return createLeaveApplicationHistory(leaveApplicationHistory);
        }
        LeaveApplicationHistory result = leaveApplicationHistoryRepository.save(leaveApplicationHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaveApplicationHistory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leave-application-histories : get all the leaveApplicationHistories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leaveApplicationHistories in body
     */
    @GetMapping("/leave-application-histories")
    @Timed
    public List<LeaveApplicationHistory> getAllLeaveApplicationHistories() {
        log.debug("REST request to get all LeaveApplicationHistories");
        List<LeaveApplicationHistory> leaveApplicationHistories = leaveApplicationHistoryRepository.findAll();
        return leaveApplicationHistories;
    }

    /**
     * GET  /leave-application-histories/:id : get the "id" leaveApplicationHistory.
     *
     * @param id the id of the leaveApplicationHistory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveApplicationHistory, or with status 404 (Not Found)
     */
    @GetMapping("/leave-application-histories/{id}")
    @Timed
    public ResponseEntity<LeaveApplicationHistory> getLeaveApplicationHistory(@PathVariable Long id) {
        log.debug("REST request to get LeaveApplicationHistory : {}", id);
        LeaveApplicationHistory leaveApplicationHistory = leaveApplicationHistoryRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(leaveApplicationHistory));
    }

    /**
     * DELETE  /leave-application-histories/:id : delete the "id" leaveApplicationHistory.
     *
     * @param id the id of the leaveApplicationHistory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leave-application-histories/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaveApplicationHistory(@PathVariable Long id) {
        log.debug("REST request to delete LeaveApplicationHistory : {}", id);
        leaveApplicationHistoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
