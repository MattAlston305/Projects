package com.revature.ImpUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.DAOs.EmployeeDAO;
import com.revature.DAOs.LoggingUtil;
import com.revature.Dealership.Employee;

public class EmployeePostgresqlDAOImp implements EmployeeDAO 
{
	private static Connection conn = ConnectionFactory.getConnection();
	
	@Override
	public void createEmployee(Employee e) 
	{
		try
		{
			LoggingUtil.debug("IN TRY!!!!!!");
			PreparedStatement stmt = conn.prepareStatement("insert into employees(Username, pass_word) values (?, ?)");
			LoggingUtil.debug("after statement");
			stmt.setString(1, e.getUsername());
			LoggingUtil.debug("after part1");
			stmt.setString(2, e.getPassword());
			LoggingUtil.debug("after part2");

			LoggingUtil.debug("Before autocommit");
			conn.setAutoCommit(false);

			LoggingUtil.debug("Before execute");
			stmt.execute();
			LoggingUtil.debug("After execute");
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException ex)
		{
			try
			{
				LoggingUtil.error("SOMETHING BAD");
				conn.rollback();
			}
			catch(SQLException exc)
			{
				exc.printStackTrace();
			}
			ex.printStackTrace();
		}
	}

	@Override
	public Employee getEmployeebyId(int Id) 
	{
		Employee e = null;
		String sql = "select * from employees where employee_id = " + "'"+ Id + "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				e = new Employee(rs.getString("username"), rs.getString("pass_word"));
			}
			
		}
		catch(SQLException ex)
		{
			e = null;
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee getEmployeebyName(String name) 
	{
		Employee emp = null;
		String sql = "select * from employees where Username = " + "'" + name+"'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				emp = new Employee(rs.getString("username"), rs.getString("pass_word"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		List<Employee> Elist = new ArrayList<Employee>();
		String sql = "select * from employees";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Elist.add(new Employee(rs.getString("username"), rs.getString("pass_word")));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Elist;
	}

}
