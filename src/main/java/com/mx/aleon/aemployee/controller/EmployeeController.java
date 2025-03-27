package com.mx.aleon.aemployee.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.aleon.aemployee.service.EmployeeService;
import com.mx.aleon.aemployee.util.TokenUtil;
import com.mx.aleon.aemployee.vo.EmployeeVO;
import com.mx.aleon.aemployee.vo.GenericResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management API", description = "API for managing employees")
public class EmployeeController {

	private static final String CREATE_EMPLOYEE_INIT = "createEmployee INIT";
	private static final String CREATE_EMPLOYEE_END = "createEmployee END";

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Retrieve an employee by their ID", description = "Fetches the details of a specific employee based on their unique identifier", responses = {
			@ApiResponse(responseCode = "200", description = "Employee found successfully"),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@GetMapping("/{id}")
	public ResponseEntity<GenericResponse> getById(@PathVariable Long id) {
		GenericResponse response = new GenericResponse(TokenUtil.generateSecureTokenTransaction());
		log.info("getById INIT");
		response.setData(employeeService.findById(id));
		log.info("getById END");
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Retrieve all employees", description = "Fetches a list of all employees in the system", responses = {
			@ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "No employees found") })
	@GetMapping
	public ResponseEntity<GenericResponse> getAllEmployees() {
		GenericResponse response = new GenericResponse(TokenUtil.generateSecureTokenTransaction());
		log.info("getAllEmployees INIT");
		response.setData(employeeService.findAll());
		log.info("getAllEmployees END");
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Create a new employee", description = "Adds a new employee to the system", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Employee details", required = true), responses = {
			@ApiResponse(responseCode = "201", description = "Employee created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@PostMapping("/single")
	public ResponseEntity<GenericResponse> createEmployee(@RequestBody EmployeeVO employee) {
		GenericResponse response = new GenericResponse(TokenUtil.generateSecureTokenTransaction());
		log.info(CREATE_EMPLOYEE_INIT);
		response.setData(employeeService.save(employee));
		log.info(CREATE_EMPLOYEE_END);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Operation(summary = "Create multiple employees", description = "Adds multiple employees to the system", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "List Employees", required = true), responses = {
			@ApiResponse(responseCode = "201", description = "Employees created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@PostMapping("/multiple")
	public ResponseEntity<GenericResponse> createEmployee(@RequestBody List<EmployeeVO> employees) {
		GenericResponse response = new GenericResponse(TokenUtil.generateSecureTokenTransaction());
		List<EmployeeVO> createListEmployee = new ArrayList<>();
		log.info(CREATE_EMPLOYEE_INIT);
		for (EmployeeVO employee : employees) {
			createListEmployee.add(employeeService.save(employee));
		}
		response.setData(createListEmployee);
		log.info(CREATE_EMPLOYEE_END);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Operation(summary = "Update an employee", description = "Updates the details of an existing employee", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated employee details", required = true), responses = {
			@ApiResponse(responseCode = "200", description = "Employee updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@PutMapping
	public ResponseEntity<GenericResponse> updateEmployee(@RequestBody EmployeeVO employedVO) {
		GenericResponse response = new GenericResponse(TokenUtil.generateSecureTokenTransaction());
		log.info("updateEmployee INIT");
		response.setData(employeeService.update(employedVO));
		log.info("updateEmployee END");
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Delete an employee", description = "Deletes an employee based on their ID", responses = {
			@ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid ID provided") })
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericResponse> deleteEmployee(@PathVariable Long id) {
		GenericResponse response = new GenericResponse(TokenUtil.generateSecureTokenTransaction());
		log.info("deleteEmployee INIT");
		employeeService.deleteById(id);
		log.info("deleteEmployee END");
		return ResponseEntity.ok(response);

	}

}