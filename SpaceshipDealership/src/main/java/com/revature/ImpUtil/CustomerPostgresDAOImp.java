package com.revature.ImpUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.DAOs.CustomerDAO;
import com.revature.DAOs.LoggingUtil;
import com.revature.Dealership.Customer;

public class CustomerPostgresDAOImp implements CustomerDAO 
{
	private static Connection conn = ConnectionFactory.getConnection();
	@Override
	public void createCustomer(Customer c) 
	{
		try
		{
			LoggingUtil.debug("IN TRY!!!!!!");
			PreparedStatement stmt = conn.prepareStatement("insert into customers(Username, pass_word) values (?, ?)");
			LoggingUtil.debug("after statement");
			stmt.setString(1, c.getUsername());
			LoggingUtil.debug("after part1");
			stmt.setString(2, c.getPassword());
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
	public Customer getCustomerbyID(int id) 
	{
		Customer c = null;
		String sql = "select * from customers where customer_id = " + "'"+id+"'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				c = new Customer(rs.getString("Username"), rs.getString("pass_word"));
				c.setCustomer_id(rs.getInt("customer_id"));
			}
		}
		catch(SQLException e)
		{
			c = null;
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Customer getCustomerbyUsername(String username) 
	{
		Customer c = null;
		String sql = "select * from customers where Username = " + "'" + username+ "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				c = new Customer(rs.getString("Username"), rs.getString("pass_word"));
				c.setCustomer_id(rs.getInt("customer_id"));
			}
		}
		catch(SQLException e)
		{
			c = null;
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Customer> getAllCustomers() 
	{
		List<Customer> Clist = new ArrayList<Customer>();
		String sql = "select * from customers";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Clist.add(new Customer(rs.getString("Username"), rs.getString("pass_word")));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return Clist;
	}
}
