package com.leave.repository;

import com.leave.domain.Employee;
import com.leave.domain.LeaveAllocation;
import com.leave.domain.LeaveBalance;
import com.leave.domain.LeaveType;
import com.leave.domain.User;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LeaveBalance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long> {

	LeaveBalance findByEmployeeAndLeaveType(Employee employee, LeaveType leaveType);

	List<LeaveBalance> findByEmployee(Employee employee);
	
}
