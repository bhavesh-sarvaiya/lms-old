package com.leave.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.leave.domain.LeaveAllocation;
import com.leave.service.LeaveAllocationService;
import com.leave.web.rest.util.HeaderUtil;
import com.leave.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LeaveAllocation.
 */
@RestController
@RequestMapping("/api")
public class LeaveAllocationResource {

    private final Logger log = LoggerFactory.getLogger(LeaveAllocationResource.class);

    private static final String ENTITY_NAME = "leaveAllocation";
        
    private final LeaveAllocationService leaveAllocationService;

    public LeaveAllocationResource(LeaveAllocationService leaveAllocationService) {
        this.leaveAllocationService = leaveAllocationService;
    }

    /**
     * POST  /leave-allocations : Create a new leaveAllocation.
     *
     * @param leaveAllocation the leaveAllocation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveAllocation, or with status 400 (Bad Request) if the leaveAllocation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-allocations")
    @Timed
    public ResponseEntity<LeaveAllocation> createLeaveAllocation(@RequestBody LeaveAllocation leaveAllocation) throws URISyntaxException {
        log.debug("REST request to save LeaveAllocation : {}", leaveAllocation);
        if (leaveAllocation.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new leaveAllocation cannot already have an ID")).body(null);
        }
        LeaveAllocation result = leaveAllocationService.save(leaveAllocation);
        return ResponseEntity.created(new URI("/api/leave-allocations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /leave-allocations : Updates an existing leaveAllocation.
     *
     * @param leaveAllocation the leaveAllocation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveAllocation,
     * or with status 400 (Bad Request) if the leaveAllocation is not valid,
     * or with status 500 (Internal Server Error) if the leaveAllocation couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-allocations")
    @Timed
    public ResponseEntity<LeaveAllocation> updateLeaveAllocation(@RequestBody LeaveAllocation leaveAllocation) throws URISyntaxException {
        log.debug("REST request to update LeaveAllocation : {}", leaveAllocation);
        if (leaveAllocation.getId() == null) {
            return createLeaveAllocation(leaveAllocation);
        }
        LeaveAllocation result = leaveAllocationService.save(leaveAllocation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaveAllocation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leave-allocations : get all the leaveAllocations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveAllocations in body
     */
    @GetMapping("/leave-allocations")
    @Timed
    public ResponseEntity<List<LeaveAllocation>> getAllLeaveAllocations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of LeaveAllocations");
        Page<LeaveAllocation> page = leaveAllocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leave-allocations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /leave-allocations/:id : get the "id" leaveAllocation.
     *
     * @param id the id of the leaveAllocation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveAllocation, or with status 404 (Not Found)
     */
    @GetMapping("/leave-allocations/{id}")
    @Timed
    public ResponseEntity<LeaveAllocation> getLeaveAllocation(@PathVariable Long id) {
        log.debug("REST request to get LeaveAllocation : {}", id);
        LeaveAllocation leaveAllocation = leaveAllocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(leaveAllocation));
    }

    /**
     * DELETE  /leave-allocations/:id : delete the "id" leaveAllocation.
     *
     * @param id the id of the leaveAllocation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leave-allocations/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaveAllocation(@PathVariable Long id) {
        log.debug("REST request to delete LeaveAllocation : {}", id);
        leaveAllocationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
