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
import com.leave.domain.LeaveType;
import com.leave.domain.User;
import com.leave.repository.EmployeeRepository;
import com.leave.repository.LeaveTypeRepository;
import com.leave.repository.UserRepository;
import com.leave.security.SecurityUtils;
import com.leave.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing LeaveType.
 */
@RestController
@RequestMapping("/api")
public class LeaveTypeResource {

	private final Logger log = LoggerFactory.getLogger(LeaveTypeResource.class);

	private static final String ENTITY_NAME = "leaveType";

	private final LeaveTypeRepository leaveTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public LeaveTypeResource(LeaveTypeRepository leaveTypeRepository) {
		this.leaveTypeRepository = leaveTypeRepository;
	}

	/**
	 * POST /leave-types : Create a new leaveType.
	 *
	 * @param leaveType
	 *            the leaveType to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new leaveType, or with status 400 (Bad Request) if the leaveType
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/leave-types")
	@Timed
	public ResponseEntity<LeaveType> createLeaveType(@Valid @RequestBody LeaveType leaveType)
			throws URISyntaxException {
		log.debug("REST request to save LeaveType : {}", leaveType);
		if (leaveType.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new leaveType cannot already have an ID"))
					.body(null);
		}
		LeaveType result = leaveTypeRepository.save(leaveType);
		return ResponseEntity.created(new URI("/api/leave-types/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /leave-types : Updates an existing leaveType.
	 *
	 * @param leaveType
	 *            the leaveType to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         leaveType, or with status 400 (Bad Request) if the leaveType is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         leaveType couldnt be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/leave-types")
	@Timed
	public ResponseEntity<LeaveType> updateLeaveType(@Valid @RequestBody LeaveType leaveType)
			throws URISyntaxException {
		log.debug("REST request to update LeaveType : {}", leaveType);
		if (leaveType.getId() == null) {
			return createLeaveType(leaveType);
		}
		LeaveType result = leaveTypeRepository.save(leaveType);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaveType.getId().toString())).body(result);
	}

	/**
	 * GET /leave-types : get all the leaveTypes.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         leaveTypes in body
	 */
	@GetMapping("/leave-types")
	@Timed
	public List<LeaveType> getAllLeaveTypes() {
		log.debug("REST request to get all LeaveTypes");
		User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin());

		Employee employee = employeeRepository.findByUser(user);
		if (employee == null) {
			System.out.println("User is not Employee");
			return null;	
		}
		// granted or non granted
		List<LeaveType> leaveTypes;
		if (employee.isGranted())
			leaveTypes = leaveTypeRepository.findAll();
		else
			leaveTypes = leaveTypeRepository.findByCode("CL");
		return leaveTypes;
	}

	/**
	 * GET /leave-types/:id : get the "id" leaveType.
	 *
	 * @param id
	 *            the id of the leaveType to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         leaveType, or with status 404 (Not Found)
	 */
	@GetMapping("/leave-types/{id}")
	@Timed
	public ResponseEntity<LeaveType> getLeaveType(@PathVariable Long id) {
		log.debug("REST request to get LeaveType : {}", id);
		LeaveType leaveType = leaveTypeRepository.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(leaveType));
	}

	/**
	 * DELETE /leave-types/:id : delete the "id" leaveType.
	 *
	 * @param id
	 *            the id of the leaveType to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/leave-types/{id}")
	@Timed
	public ResponseEntity<Void> deleteLeaveType(@PathVariable Long id) {
		log.debug("REST request to delete LeaveType : {}", id);
		leaveTypeRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
