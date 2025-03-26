package com.mx.aleon.aemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.aleon.aemployee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	

}
