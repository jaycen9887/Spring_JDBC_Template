package com.jaycen.template.dao.models;

import java.util.List;

import com.jaycen.template.models.Employee;

public interface EmployeeDAO {
	
	public int save(Employee employee);
	public Employee getById(int id);
	public int update(Employee employee);
	public int deleteById(int id);
	public List<Employee> getAll();

}
