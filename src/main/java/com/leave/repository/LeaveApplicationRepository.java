package com.leave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leave.domain.LeaveApplication;


/**
 * Spring Data JPA repository for the LeaveApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Long> {

	@Query(value=" SELECT MAX_NO_OF_DAYS FROM LEAVE_TYPE_DAYS_MAPPING WHERE LEAVE_TYPE_CODE=?1 AND TEACHING=?2 AND GRANTED=?3 AND CAN_HAVE_VACATION=?4",nativeQuery=true)
	List<Object[]> getMaxNoOfDays(String typeCode, boolean teaching, boolean granted, boolean canHaveVacation);
	
	//findTop10ByLastnameOrderByFirstnameAsc(String lastname);
	//findTop1ByApplicationDateDesc();
}
