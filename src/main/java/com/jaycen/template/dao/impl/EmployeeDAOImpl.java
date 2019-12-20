package com.jaycen.template.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jaycen.template.dao.models.EmployeeDAO;
import com.jaycen.template.errorHandlers.CustomSQLErrorCodeTranslator;
import com.jaycen.template.mapper.EmployeeRowMapper;
import com.jaycen.template.models.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate nameParameterJdbcTemplate;
	
	private SimpleJdbcInsert simpleJdbcInsert;
	
	private SimpleJdbcCall simpleJdbcCall;
	
	@Autowired
	public void setDataSource(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		final CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
		jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);
		
		nameParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("Employee");
	}

	@Override
	public int save(Employee employee) {
		return jdbcTemplate.update("INSERT INTO Employee VALUES(?,?,?)", employee.getId(), employee.getName(), employee.getRole());		
	}

	@Override
	public Employee getById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM Employee WHERE ID = ?", new Object[] {id}, new EmployeeRowMapper());
	}

	@Override
	public int update(Employee employee) {
		return jdbcTemplate.update("UPDATE Employee SET name=?, role=? WHERE id=?", employee.getName(), employee.getRole(), employee.getId());
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM Employee WHERE ID = ?", id);		
	}

	@Override
	public List<Employee> getAll() {
		return jdbcTemplate.query("SELECT * FROM Employee", new EmployeeRowMapper());
	}
	
	
	
	
	/*private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Employee employee) {
		String query = "insert into Employee (id, name, role) values (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employee.getId());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getRole());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee saved with id = " + employee.getId());
			} else {
				System.out.println("Employee save failed with id = " + employee.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Employee getById(int id) {
		String query = "select name, role from Employee where id = ?";
		Employee emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				emp = new Employee(id, rs.getString("name"), rs.getString("role"));
				System.out.println("Employee Found :: " + emp.toString());
			} else {
				System.out.println("No Employee found with id = " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emp;
	}

	@Override
	public void update(Employee employee) {
		String query = "update Employee set name=?, role=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getRole());
			ps.setInt(3, employee.getId());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee updated with id = " + employee.getId());
			} else {
				System.out.println("No Employee found with id = " + employee.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteById(int id) {
		String query = "delete from Employee where id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();

			if (out != 0) {
				System.out.println("Employee deleted with id = " + id);
			} else {
				System.out.println("No Employee found with id = " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

	@Override
	public List<Employee> getAll() {
		String query = "select id, name, role from Employee";
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("role"));
				empList.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}*/

}
