package com.leave.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.leave.domain.Employee;
import com.leave.domain.LeaveApplication;
import com.leave.domain.LeaveBalance;
import com.leave.domain.User;
import com.leave.repository.EmployeeRepository;
import com.leave.repository.LeaveApplicationRepository;
import com.leave.repository.LeaveBalanceRepository;
import com.leave.repository.UserRepository;
import com.leave.security.SecurityUtils;
import com.leave.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Period;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LeaveApplication.
 */
@RestController
@RequestMapping("/api")
public class LeaveApplicationResource {

    private final Logger log = LoggerFactory.getLogger(LeaveApplicationResource.class);

    private static final String ENTITY_NAME = "leaveApplication";
        
    private final LeaveApplicationRepository leaveApplicationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    public LeaveApplicationResource(LeaveApplicationRepository leaveApplicationRepository) {
        this.leaveApplicationRepository = leaveApplicationRepository;
    }

    /**
     * POST  /leave-applications : Create a new leaveApplication.
     *
     * @param leaveApplication the leaveApplication to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveApplication, or with status 400 (Bad Request) if the leaveApplication has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-applications")
    @Timed
    public ResponseEntity<LeaveApplication> createLeaveApplication(@Valid @RequestBody LeaveApplication leaveApplication) throws URISyntaxException {
        log.debug("REST request to save LeaveApplication : {}", leaveApplication);
        if (leaveApplication.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new leaveApplication cannot already have an ID")).body(null);
        }
        System.out.println("ok");
        
        User user=userRepository.findByLogin(SecurityUtils.getCurrentUserLogin());
        Employee employee=employeeRepository.findByUser(user);
        List<Object[]> list=leaveApplicationRepository.getMaxNoOfDays(leaveApplication.getLeaveType().getCode(), employee.isTeachingstaff(),employee.isGranted(), employee.isCanHaveVacation());
        Iterator<Object[]> i=list.iterator();
        Object[] maxNoOfDay=null;
        if(i.hasNext())
        	maxNoOfDay=i.next();
        leaveApplication.setAppliedBy(employee);
     	LeaveBalance leaveBalance=leaveBalanceRepository.findByEmployeeAndLeaveType(employee, leaveApplication.getLeaveType());
     	/*if(leaveApplication.getNoOfDays() <= leaveBalance.getNoOfLeave())
     	{
     		 // leave balance
     		System.out.println("Not eligible for this leave\nReason:Out of Leave balance you have "+leaveBalance.getNoOfLeave() + " leave balance");
     		return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "Not eligible for this leave\nReason:Out of Leave balance you have "+leaveBalance.getNoOfLeave() + " leave balance")).body(null);
     	}*/
     	if(maxNoOfDay==null)
     	{
     		System.out.println("No max no of day rule available for this type of employee in LEAVE_TYPE_DAYS_MAPPING table");
     		return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "maxdaynorules", "No max no of day rule available for this type of employee in LEAVE_TYPE_DAYS_MAPPING table")).body(null);
     	}
     	/*if(!(leaveApplication.getNoOfDays() <= Double.parseDouble(noOfDay.toString())))
     	{
     		System.out.println("Out of maximum day requested");
     		return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "Out of maximum day requested")).body(null);
     	}*/
     	
     	 LeaveApplication result = leaveApplicationRepository.save(leaveApplication);
       
        
        // joint
        // gender
        // leave balance
        // validity
        return ResponseEntity.created(new URI("/api/leave-applications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
        
    }

    /**
     * PUT  /leave-applications : Updates an existing leaveApplication.
     *
     * @param leaveApplication the leaveApplication to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveApplication,
     * or with status 400 (Bad Request) if the leaveApplication is not valid,
     * or with status 500 (Internal Server Error) if the leaveApplication couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-applications")
    @Timed
    public ResponseEntity<LeaveApplication> updateLeaveApplication(@Valid @RequestBody LeaveApplication leaveApplication) throws URISyntaxException {
        log.debug("REST request to update LeaveApplication : {}", leaveApplication);
        if (leaveApplication.getId() == null) {
            return createLeaveApplication(leaveApplication);
        }
        LeaveApplication result = leaveApplicationRepository.save(leaveApplication);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaveApplication.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leave-applications : get all the leaveApplications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leaveApplications in body
     */
    @GetMapping("/leave-applications")
    @Timed
    public List<LeaveApplication> getAllLeaveApplications() {
        log.debug("REST request to get all LeaveApplications");
        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findAll();
        return leaveApplications;
    }

    /**
     * GET  /leave-applications/:id : get the "id" leaveApplication.
     *
     * @param id the id of the leaveApplication to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveApplication, or with status 404 (Not Found)
     */
    @GetMapping("/leave-applications/{id}")
    @Timed
    public ResponseEntity<LeaveApplication> getLeaveApplication(@PathVariable Long id) {
        log.debug("REST request to get LeaveApplication : {}", id);
        LeaveApplication leaveApplication = leaveApplicationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(leaveApplication));
    }

    /**
     * DELETE  /leave-applications/:id : delete the "id" leaveApplication.
     *
     * @param id the id of the leaveApplication to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leave-applications/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaveApplication(@PathVariable Long id) {
        log.debug("REST request to delete LeaveApplication : {}", id);
        leaveApplicationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/leave-application/calculateNoOfDays")
    @Timed
    public double calculateNoOfDays(@RequestBody LeaveApplication leaveApplication) {
        log.debug("REST request to get calculateNoOfDays");
        double noOfDays = 0.0d;
        Period intervalPeriod = Period.between(leaveApplication.getDateFrom(), leaveApplication.getEndDate());
        noOfDays=(double)intervalPeriod.getDays();
//        System.out.print("bhavesh -->"+noOfDays);
        //calculate no of days based on to and from date from leaveapplication
        return noOfDays;
    }
}
