package com.mx.aleon.aemployee.service;

import java.util.List;

import com.mx.aleon.aemployee.ex.ApiException;
import com.mx.aleon.aemployee.vo.EmployeeVO;

public interface EmployeeService {

	public EmployeeVO findById(Long id) throws ApiException;

	public List<EmployeeVO> findAll() throws ApiException;

	public EmployeeVO save(EmployeeVO employee) throws ApiException;
	
	public EmployeeVO update(EmployeeVO employee) throws ApiException;

	public void deleteById(Long id) throws ApiException;

}
