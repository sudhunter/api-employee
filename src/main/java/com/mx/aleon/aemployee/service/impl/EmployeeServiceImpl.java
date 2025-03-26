package com.mx.aleon.aemployee.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.aleon.aemployee.entity.Employee;
import com.mx.aleon.aemployee.entity.Job;
import com.mx.aleon.aemployee.ex.ApiException;
import com.mx.aleon.aemployee.mapper.EmployeeMapper;
import com.mx.aleon.aemployee.repository.EmployeeRepository;
import com.mx.aleon.aemployee.repository.JobRepository;
import com.mx.aleon.aemployee.service.EmployeeService;
import com.mx.aleon.aemployee.vo.EmployeeVO;

/**
 * Implementation of the EmployeeService interface. This service handles
 * operations related to employees, including finding, saving, and deleting
 * employee records.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String EMPLOYEE_NOT_FOUND_WITH_ID = "Employee not found with ID: ";

	/**
	 * Logger instance used to log information and errors during service execution.
	 */
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	/**
	 * Repository to interact with the Employee database.
	 */
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Repository to interact with the Job database.
	 */
	@Autowired
	private JobRepository jobRepository;

	private static final EmployeeMapper mapper = EmployeeMapper.INSTANCE;

	/**
	 * Finds an employee by their unique identifier.
	 *
	 * @param id the unique identifier of the employee to find
	 * @return ResponseEmployee object containing employee details
	 * @throws ApiException if an error occurs during the process
	 */
	@Override
	public EmployeeVO findById(Long id) throws ApiException {
		log.info("Finding employee with ID: {}", id);
		return employeeRepository.findById(id).map(mapper::employeeToEmployeeVO)
				.orElseThrow(() -> new ApiException(-1, EMPLOYEE_NOT_FOUND_WITH_ID + id));
	}

	/**
	 * Retrieves all employees from the database.
	 *
	 * @return a list of ResponseEmployee objects
	 * @throws ApiException if an error occurs during the process
	 */
	@Override
	public List<EmployeeVO> findAll() throws ApiException {
		try {
			log.info("Fetching all employees.");
			List<Employee> employees = employeeRepository.findAll();

			if (employees.isEmpty()) {
				throw new ApiException(-1, "No employees found.");
			}
			return employees.stream().map(mapper::employeeToEmployeeVO).collect(Collectors.toList());
		} catch(ApiException e) {
			throw e;
		} catch (Exception e) {
			log.error("An error occurred while fetching employees.", e);
			throw new ApiException(-1, "Unable to fetch employee list.");
		}
	}

	/**
	 * Saves a new employee or updates an existing one in the database.
	 *
	 * @param employee the RequestEmployee object containing employee details
	 * @return ResponseEmployee object containing saved employee details
	 * @throws ApiException if an error occurs during the save process
	 */
	@Override
	public EmployeeVO save(EmployeeVO employeeVO) throws ApiException {
		try {
			log.info("Saving employee: {}", employeeVO);

			Employee employee = mapper.employeeVOToEmployeeWitoutId(employeeVO);

			Job job = jobRepository.findByTitle(employeeVO.getJobTitle()).orElseGet(() -> {
				log.info("Job not found with title: {}. Creating a new Job entry.", employeeVO.getJobTitle());
				Job newJob = new Job();
				newJob.setTitle(employeeVO.getJobTitle());
				newJob.setDescription("Default description for job: " + employeeVO.getJobTitle());
				newJob.setBaseSalary(0.0);
				return jobRepository.save(newJob);
			});

			employee.setJob(job);

			Employee savedEmployee = employeeRepository.save(employee);
			EmployeeVO response = mapper.employeeToEmployeeVO(savedEmployee);

			log.info("Employee saved successfully with ID: {}", savedEmployee.getId());

			return response;

		} catch (Exception e) {
			log.error("An error occurred while saving the employee.", e);
			throw new ApiException(-1, "Unable to save employee details.");
		}
	}

	/**
	 * Deletes an employee by their unique identifier.
	 *
	 * @param id the unique identifier of the employee to delete
	 * @throws ApiException if an error occurs during the delete process
	 */
	@Override
	public void deleteById(Long id) throws ApiException {
		try {
			log.info("Deleting employee with ID: {}", id);
			if (!employeeRepository.existsById(id)) {
				throw new NoSuchElementException(EMPLOYEE_NOT_FOUND_WITH_ID + id);
			}
			employeeRepository.deleteById(id);

			log.info("Employee with ID: {} deleted successfully.", id);
		} catch (Exception e) {
			log.error("An unexpected error occurred while deleting the employee with ID: {}", id, e);
			throw new ApiException(-1, "Unable to delete employee with ID: " + id);
		}
	}

	/**
	 * Updates an existing employee with the provided details.
	 * 
	 * @param employee the EmployeeVO containing updated details
	 * @return the updated EmployeeVO object
	 * @throws ApiException if the employee is not found or an error occurs
	 */
	@Override
	public EmployeeVO update(EmployeeVO employeeVO) throws ApiException {
	    try {
	        log.info("Updating employee with ID: {}", employeeVO.getId());
	        
	        Employee employee = mapper.employeeVOToEmployee(employeeVO);

	        Employee existingEmployee = employeeRepository.findById(employee.getId())
	                .orElseThrow(() -> new ApiException(-1, EMPLOYEE_NOT_FOUND_WITH_ID + employee.getId()));

	        existingEmployee.setFirstName(employee.getFirstName());
	        existingEmployee.setLastName(employee.getLastName());
	        existingEmployee.setGender(employee.getGender());
	        existingEmployee.setBirthDate(employee.getBirthDate());

	        Job job = jobRepository.findByTitle(employeeVO.getJobTitle())
	                .orElseGet(() -> {
	                    Job newJob = new Job();
	                    newJob.setTitle(employeeVO.getJobTitle());
	                    return jobRepository.save(newJob);
	                });

	        existingEmployee.setJob(job);

	        Employee updatedEmployee = employeeRepository.save(existingEmployee);

	        return mapper.employeeToEmployeeVO(updatedEmployee);
	    } catch(ApiException e) {
	    	throw e;
	    } catch (Exception e) {
	        log.error("Error updating employee with ID: {}", employeeVO.getId(), e);
	        throw new ApiException(-1, "Unable to update employee details.");
	    }
	}
}
