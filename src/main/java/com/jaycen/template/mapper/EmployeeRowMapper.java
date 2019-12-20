package com.jaycen.template.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jaycen.template.models.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("role"));
		return employee;
	}

}
