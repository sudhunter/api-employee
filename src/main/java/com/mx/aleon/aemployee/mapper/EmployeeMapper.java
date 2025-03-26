package com.mx.aleon.aemployee.mapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.mx.aleon.aemployee.entity.Employee;
import com.mx.aleon.aemployee.ex.ApiException;
import com.mx.aleon.aemployee.util.DateMapperUtil;
import com.mx.aleon.aemployee.vo.EmployeeVO;

@Mapper
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	/**
	 * Maps an Employee entity to an EmployeeResponse object.
	 *
	 * @param employee the Employee entity to be converted
	 * @return the mapped EmployeeResponse object
	 */
	@Mapping(target = "age", ignore = true)
	@Mapping(source = "job.title", target = "jobTitle") // Mapea el título del trabajo
	@Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd-MM-yyyy") // Formatea la fecha
	EmployeeVO employeeToEmployeeVO(Employee employee);

	/**
	 * Calculates the age of the employee after the mapping process is complete.
	 *
	 * @param employee   the source Employee entity containing the birth date
	 * @param employeeVO the target EmployeeVO where the calculated age is set
	 */
	@AfterMapping
	default void calculateAge(Employee employee, @MappingTarget EmployeeVO employeeVO) {
		if (employee.getBirthDate() != null) {

			LocalDate birthDate = new java.sql.Date(employee.getBirthDate().getTime()).toLocalDate();

	        int age = Period.between(birthDate, LocalDate.now()).getYears();
	        employeeVO.setAge(age); 
		}
	}

	/**
	 * Maps an EmployeeVO object to an Employee entity. Excludes the "id" field.
	 *
	 * @param employeeVO the EmployeeVO object to be converted
	 * @return the mapped Employee entity
	 */
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "stringToDate")
	@Mapping(source = "jobTitle", target = "job.title")
	Employee employeeVOToEmployeeWitoutId(EmployeeVO employeeVO);

	/**
	 * Maps an EmployeeVO object to an Employee entity.
	 *
	 * @param employeeVO the EmployeeVO object to be converted
	 * @return the mapped Employee entity
	 */
	@Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "stringToDate")
	@Mapping(source = "jobTitle", target = "job.title")
	Employee employeeVOToEmployee(EmployeeVO employeeVO);

	@Named("stringToDate")
	default Date stringToDate(String dateString) throws ApiException {
		return DateMapperUtil.toDate(dateString);
	}

}
