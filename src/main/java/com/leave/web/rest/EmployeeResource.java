package com.leave.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.leave.domain.Employee;
import com.leave.domain.User;
import com.leave.repository.EmployeeRepository;
import com.leave.service.MailService;
import com.leave.service.UserService;
import com.leave.service.dto.UserDTO;
import com.leave.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Employee.
 */
@RestController
@RequestMapping("/api")
public class EmployeeResource {

	private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

	private static final String ENTITY_NAME = "employee";

	private final EmployeeRepository employeeRepository;

	@Autowired
	UserService userService;

	@Autowired
	MailService mailService;

	public EmployeeResource(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/**
	 * POST /employees : Create a new employee.
	 *
	 * @param employee
	 *            the employee to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new employee, or with status 400 (Bad Request) if the employee
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/employees")
	@Timed
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) throws URISyntaxException {
		log.debug("REST request to save Employee : {}", employee);
		if (employee.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new employee cannot already have an ID"))
					.body(null);
		}

		// System.out.println("sfs"+employee.isCanHaveVacation());
		if (employee.isCanHaveVacation() == null)
			employee.setCanHaveVacation(false);

		if (employee.isGranted() == null)
			employee.setGranted(false);

		if (employee.isTeachingstaff() == null)
			employee.setTeachingstaff(false);

		User user = employee.getUser();
		UserDTO userDTO = new UserDTO(user);
		userDTO.setLogin(user.getEmail());
		userDTO.getAuthorities().add("ROLE_USER");

		mailService.sendCreationEmail(user);
		employee.setUser(user);
		Employee result = employeeRepository.save(employee);
		return ResponseEntity.created(new URI("/api/employees/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /employees : Updates an existing employee.
	 *
	 * @param employee
	 *            the employee to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         employee, or with status 400 (Bad Request) if the employee is not
	 *         valid, or with status 500 (Internal Server Error) if the employee
	 *         couldnt be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/employees")
	@Timed
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) throws URISyntaxException {
		log.debug("REST request to update Employee : {}", employee);
		if (employee.getId() == null) {
			return createEmployee(employee);
		}
		Employee result = employeeRepository.save(employee);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employee.getId().toString()))
				.body(result);
	}

	/**
	 * GET /employees : get all the employees.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of employees
	 *         in body
	 */
	@GetMapping("/employees")
	@Timed
	public List<Employee> getAllEmployees() {
		log.debug("REST request to get all Employees");
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	/**
	 * GET /employees/:id : get the "id" employee.
	 *
	 * @param id
	 *            the id of the employee to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         employee, or with status 404 (Not Found)
	 */
	@GetMapping("/employees/{id}")
	@Timed
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		log.debug("REST request to get Employee : {}", id);
		Employee employee = employeeRepository.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(employee));
	}

	/**
	 * DELETE /employees/:id : delete the "id" employee.
	 *
	 * @param id
	 *            the id of the employee to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/employees/{id}")
	@Timed
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		log.debug("REST request to delete Employee : {}", id);
		employeeRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
