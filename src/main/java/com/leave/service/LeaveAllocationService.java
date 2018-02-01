package com.leave.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.logback.LevelRemappingAppender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leave.domain.Employee;
import com.leave.domain.LeaveAllocation;
import com.leave.domain.LeaveBalance;
import com.leave.repository.EmployeeRepository;
import com.leave.repository.LeaveAllocationRepository;
import com.leave.repository.LeaveBalanceRepository;

/**
 * Service Implementation for managing LeaveAllocation.
 */
@Service
@Transactional
public class LeaveAllocationService {

    private final Logger log = LoggerFactory.getLogger(LeaveAllocationService.class);
    
    private final LeaveAllocationRepository leaveAllocationRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;

    public LeaveAllocationService(LeaveAllocationRepository leaveAllocationRepository) {
        this.leaveAllocationRepository = leaveAllocationRepository;
    }

    /**
     * Save a leaveAllocation.
     *
     * @param leaveAllocation the entity to save
     * @return the persisted entity
     */
    public LeaveAllocation save(LeaveAllocation leaveAllocation) {
        log.debug("Request to save LeaveAllocation : {}", leaveAllocation);
        
        
        if(leaveAllocation.isCanHaveVacation()==null)
        	leaveAllocation.setCanHaveVacation(false);

        
        if(leaveAllocation.isGranted()==null)
        	leaveAllocation.setGranted(false);

        if(leaveAllocation.isTeaching()==null)
        	leaveAllocation.setTeaching(false);
        //Get all the employees based on flag teaching, canhavevacation, granted
        List<Employee> employees = employeeRepository.findByTeachingstaffAndGrantedAndCanHaveVacation(leaveAllocation.isTeaching(), leaveAllocation.isGranted(), leaveAllocation.isCanHaveVacation());
        
        //loop through employees
        LeaveBalance leaveBalance;
        Iterator<Employee> e=employees.iterator();
        Employee emp;

       
        while(e.hasNext())
        {
        	emp=e.next();
        	leaveBalance = leaveBalanceRepository.findByEmployeeAndLeaveType(emp, leaveAllocation.getLeaveType());
        	if(leaveBalance==null)
        	{
        		leaveBalance=new LeaveBalance();
        		leaveBalance.setEmployee(emp);
        		leaveBalance.setLeaveType(leaveAllocation.getLeaveType());
        		leaveBalance.setNoOfLeave(leaveAllocation.getNoOfLeaves());
        		leaveBalanceRepository.save(leaveBalance);
        	}
        	else
        	{
        		leaveBalance.setNoOfLeave(leaveBalance.getNoOfLeave()+leaveAllocation.getNoOfLeaves());
        		leaveBalanceRepository.save(leaveBalance);
        	}
        }
        //find leavebalance object of employee based on leave type and employee, create new if none is found
        
        //update leavebalance object by adding noofleaves for that leavebalance object
        
        LeaveAllocation result = leaveAllocationRepository.save(leaveAllocation);
        return result;
    }

    /**
     *  Get all the leaveAllocations.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LeaveAllocation> findAll(Pageable pageable) {
        log.debug("Request to get all LeaveAllocations");
        Page<LeaveAllocation> result = leaveAllocationRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one leaveAllocation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public LeaveAllocation findOne(Long id) {
        log.debug("Request to get LeaveAllocation : {}", id);
        LeaveAllocation leaveAllocation = leaveAllocationRepository.findOne(id);
        return leaveAllocation;
    }

    /**
     *  Delete the  leaveAllocation by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LeaveAllocation : {}", id);
        leaveAllocationRepository.delete(id);
    }
}
