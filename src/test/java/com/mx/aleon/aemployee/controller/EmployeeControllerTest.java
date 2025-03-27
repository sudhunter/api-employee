package com.mx.aleon.aemployee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mx.aleon.aemployee.ex.ApiException;
import com.mx.aleon.aemployee.service.EmployeeService;
import com.mx.aleon.aemployee.vo.EmployeeVO;
import com.mx.aleon.aemployee.vo.GenericResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeVO mockEmployeeVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock data
        mockEmployeeVO = new EmployeeVO();
        mockEmployeeVO.setId(1L);
        mockEmployeeVO.setFirstName("Juan");
        mockEmployeeVO.setLastName("Silva");
        mockEmployeeVO.setJobTitle("Tester");
        mockEmployeeVO.setBirthDate("02-03-2010");
    }

    @Test
    void getByIdShouldReturnEmployeeWhenEmployeeExists() throws ApiException {
        // Arrange
        when(employeeService.findById(1L)).thenReturn(mockEmployeeVO);

        // Act
        ResponseEntity<GenericResponse> response = employeeController.getById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockEmployeeVO, response.getBody().getData());
        verify(employeeService, times(1)).findById(1L);
    }

  
    
    @Test
    void getAllEmployeesShouldReturnListWhenEmployeesExist() throws ApiException {
        // Arrange
        List<EmployeeVO> mockEmployeeList = List.of(
        		mockEmployeeVO,
        		mockEmployeeVO
        );

        when(employeeService.findAll()).thenReturn(mockEmployeeList);

        // Act
        ResponseEntity<GenericResponse> response = employeeController.getAllEmployees();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, ((List<?>) response.getBody().getData()).size());
        verify(employeeService, times(1)).findAll();
    }

   
    
    @Test
    void createEmployeeShouldReturnCreatedEmployeeWhenValidDataIsProvided() throws ApiException {
        // Arrange
        EmployeeVO inputEmployeeVO = new EmployeeVO();
        inputEmployeeVO.setFirstName("Juan");
        inputEmployeeVO.setLastName("Silva");
        inputEmployeeVO.setJobTitle("Tester");

        EmployeeVO savedEmployeeVO = new EmployeeVO();
        savedEmployeeVO.setId(1L);
        savedEmployeeVO.setFirstName("Juan");
        savedEmployeeVO.setLastName("Silva");
        savedEmployeeVO.setJobTitle("Tester");

        when(employeeService.save(inputEmployeeVO)).thenReturn(savedEmployeeVO);

        // Act
        ResponseEntity<GenericResponse> response = employeeController.createEmployee(inputEmployeeVO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedEmployeeVO, response.getBody().getData());
        verify(employeeService, times(1)).save(inputEmployeeVO);
    }

    
    @Test
    void createEmployeeMultipleShouldReturnCreatedEmployeesWhenValidDataIsProvided() throws ApiException {
        // Arrange: Datos de entrada (una lista de EmployeeVOs)
        List<EmployeeVO> inputEmployees = List.of(
            new EmployeeVO(1L, "Juan", "Silva", "M", null, "Tester"),
            new EmployeeVO(2L, "Ana", "Gómez", "F", null, "Developer")
        );

        List<EmployeeVO> savedEmployees = List.of(
            new EmployeeVO(1L, "Juan", "Silva", "M", null, "Tester"),
            new EmployeeVO(2L, "Ana", "Gómez", "F", null, "Developer")
        );

        // Mock del servicio para cada empleado
        when(employeeService.save(inputEmployees.get(0))).thenReturn(savedEmployees.get(0));
        when(employeeService.save(inputEmployees.get(1))).thenReturn(savedEmployees.get(1));

        // Act: Invoca el método del controlador
        ResponseEntity<GenericResponse> response = employeeController.createEmployee(inputEmployees);

        // Assert: Verifica la respuesta
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, ((List<?>) response.getBody().getData()).size()); // Verifica que se hayan creado dos empleados
        verify(employeeService, times(2)).save(any(EmployeeVO.class)); // Verifica que el servicio fue llamado dos veces
    }
    

    @Test
    void updateEmployeeShouldReturnUpdatedEmployeeWhenValidDataIsProvided() throws ApiException {
        // Arrange
        EmployeeVO inputEmployeeVO = new EmployeeVO(1L, "Carlos", "Pérez", "M", null, "Manager");
        EmployeeVO updatedEmployeeVO = new EmployeeVO(1L, "Carlos", "Pérez", "M", null, "Manager");

        when(employeeService.update(inputEmployeeVO)).thenReturn(updatedEmployeeVO);

        // Act
        ResponseEntity<GenericResponse> response = employeeController.updateEmployee(inputEmployeeVO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedEmployeeVO, response.getBody().getData()); // Verifica el objeto actualizado
        verify(employeeService, times(1)).update(inputEmployeeVO); // Verifica que se llame al servicio
    }
    
    @Test
    void deleteEmployeeShouldReturnSuccessWhenEmployeeExists() throws ApiException {
        // Arrange
        Long employeeId = 1L;

        // No se necesita un retorno, ya que deleteById es void
        doNothing().when(employeeService).deleteById(employeeId);

        // Act
        ResponseEntity<GenericResponse> response = employeeController.deleteEmployee(employeeId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getData()); // No se espera un cuerpo de datos para este caso
        verify(employeeService, times(1)).deleteById(employeeId); // Verifica que se llame al servicio
    }
   
}
