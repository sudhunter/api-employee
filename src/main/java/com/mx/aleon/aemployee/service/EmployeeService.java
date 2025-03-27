package com.mx.aleon.aemployee.service;

import java.util.List;

import com.mx.aleon.aemployee.vo.EmployeeVO;

public interface EmployeeService {

	public EmployeeVO findById(Long id);

	public List<EmployeeVO> findAll();

	public EmployeeVO save(EmployeeVO employee);
	
	public EmployeeVO update(EmployeeVO employee);

	public void deleteById(Long id);

}
