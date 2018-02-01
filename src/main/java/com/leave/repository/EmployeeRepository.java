package com.leave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leave.domain.Employee;
import com.leave.domain.User;


/**
 * Spring Data JPA repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	List<Employee> findByTeachingstaffAndGrantedAndCanHaveVacation(Boolean teachingStaff,Boolean granted, Boolean canHaveVacation);

	Employee findByUser(User user);
}
