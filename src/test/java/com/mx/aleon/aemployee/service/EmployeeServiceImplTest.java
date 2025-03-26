package com.mx.aleon.aemployee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mx.aleon.aemployee.entity.Employee;
import com.mx.aleon.aemployee.entity.Job;
import com.mx.aleon.aemployee.ex.ApiException;
import com.mx.aleon.aemployee.repository.EmployeeRepository;
import com.mx.aleon.aemployee.repository.JobRepository;
import com.mx.aleon.aemployee.service.impl.EmployeeServiceImpl;
import com.mx.aleon.aemployee.vo.EmployeeVO;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeVO employeeVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Juan");
        employee.setLastName("Silva");

        employeeVO = new EmployeeVO();
        employeeVO.setId(1L);
        employeeVO.setFirstName("Juan");
        employeeVO.setLastName("Silva");
        employeeVO.setBirthDate("03-02-2010");
        employeeVO.setJobTitle("Tester");
    }
    
    @Test
    void testInstances() {
    	assertNotNull(employeeRepository);
    	assertNotNull(jobRepository);
    }

    @Test
    void findByIdShouldReturnEmployeeWhenEmployeeExists() throws ApiException {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
      
        // Act
        EmployeeVO result = employeeService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
        assertEquals("Silva", result.getLastName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdShouldThrowApiExceptionWhenEmployeeDoesNotExist() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> employeeService.findById(1L));
        assertEquals("Employee not found with ID: 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
    }
    
    @Test
    void saveShouldSaveEmployeeWhenValidDataIsProvided() throws ApiException {
        // Arrange
        Job job = new Job();
        job.setTitle("Tester");
        when(jobRepository.findByTitle("Tester")).thenReturn(Optional.of(job));

        Employee savedEmployee = new Employee();
        savedEmployee.setId(1L);
        savedEmployee.setFirstName("Juan");
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        // Act
        EmployeeVO result = employeeService.save(employeeVO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(jobRepository, times(1)).findByTitle("Tester");
    }
    
    @Test
    void deleteByIdShouldDeleteEmployeeWhenEmployeeExists() throws ApiException {
        // Arrange
        when(employeeRepository.existsById(1L)).thenReturn(true);

        // Act
        employeeService.deleteById(1L);

        // Assert
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdShouldThrowApiExceptionWhenEmployeeDoesNotExist() {
        // Arrange
        when(employeeRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> employeeService.deleteById(1L));
        assertEquals("Unable to delete employee with ID: 1", exception.getMessage());
        verify(employeeRepository, never()).deleteById(anyLong());
    }
    
    @Test
    void findAllShouldReturnListOfEmployeesWhenEmployeesExist() throws ApiException {
        // Arrange
        List<Employee> mockEmployees = List.of(
            new Employee(1L, "Juan", "Silva", 'M', null, new Job()),
            new Employee(2L, "Ana", "Gómez", 'F', null, new Job())
        );

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // Act
        List<EmployeeVO> result = employeeService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Juan", result.get(0).getFirstName());
        assertEquals("Ana", result.get(1).getFirstName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findAllShouldThrowApiExceptionWhenNoEmployeesExist() {
        // Arrange
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> employeeService.findAll());
        assertEquals("No employees found.", exception.getMessage());
        verify(employeeRepository, times(1)).findAll();
    }
    
    @Test
    void updateShouldUpdateEmployeeWhenEmployeeExists() throws ApiException {
        // Arrange
        EmployeeVO updatedEmployeeVO = new EmployeeVO();
        updatedEmployeeVO.setId(1L);
        updatedEmployeeVO.setFirstName("Carlos");
        updatedEmployeeVO.setLastName("Silva");
        updatedEmployeeVO.setBirthDate("03-02-2010");
        updatedEmployeeVO.setJobTitle("Tester");

        Employee existingEmployee = new Employee(1L, "Juan", "Silva", 'M', null, new Job());
        Employee updatedEmployee = new Employee(1L, "Carlos", "Pérez", 'M', new Date(), new Job());

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
      
        // Act
        EmployeeVO result = employeeService.update(updatedEmployeeVO);

        // Assert
        assertNotNull(result);
        assertEquals("Carlos", result.getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateShouldThrowApiExceptionWhenEmployeeDoesNotExist() {
        // Arrange

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> employeeService.update(employeeVO));
        assertEquals("Employee not found with ID: 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }
    
    
}
