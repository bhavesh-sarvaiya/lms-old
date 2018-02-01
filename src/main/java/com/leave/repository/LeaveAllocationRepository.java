package com.leave.repository;

import com.leave.domain.Employee;
import com.leave.domain.LeaveAllocation;
import com.leave.domain.LeaveType;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LeaveAllocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveAllocationRepository extends JpaRepository<LeaveAllocation,Long> {
	
	

}
