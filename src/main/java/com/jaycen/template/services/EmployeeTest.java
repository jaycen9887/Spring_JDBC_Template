package com.jaycen.template.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaycen.template.dao.models.EmployeeDAO;
import com.jaycen.template.models.Employee;

@Service
public class EmployeeTest {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@PostConstruct
	public void EmployeeTest() {
		
		Employee emp1 = new Employee(10000003, "Cindy", "CFO");
		
		int output = employeeDAO.save(emp1);
		
		if(output != 0) {
			System.out.println("Employee " + emp1.getName() + " was saved successfully with id: " + emp1.getId());
		}
		
	}
}
