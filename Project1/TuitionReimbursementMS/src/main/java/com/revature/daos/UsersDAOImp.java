package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Users;
import com.revature.services.LoggingUtil;

public class UsersDAOImp implements UsersDAO
{
	private Connection conn = ConnectionFactory.getConnection();

	@Override
	public void createUser(Users e) 
	{
		try
		{
			LoggingUtil.debug("IN TRY!!!!!!");
			PreparedStatement stmt = conn.prepareStatement("insert into Users(Username, Pass_Word) values (?, ?)");
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
	public Users getUserByUsername(String username) 
	{
		Users e = null;
		String sql = "select * from Users where Username = " + "'"+ username + "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				e = new Users(rs.getString("username"), rs.getString("pass_word"));
				e.setuserID(rs.getInt("userID"));
				e.setPosition(rs.getString("positions"));
				e.setAvailablebalance(rs.getInt("availablebalance"));
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
	public Users getUserById(int Id) 
	{
		Users e = null;
		String sql = "select * from Users where userid = " + "'"+ Id + "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				
				e = new Users(rs.getString("username"), rs.getString("pass_word"));
				e.setuserID(rs.getInt("userID"));
				e.setPosition(rs.getString("positions"));
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
	public List<Users> getAllUsers() 
	{
		List<Users> users = new ArrayList<Users>();
		String sql = "select * from Users";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Users user = new Users(rs.getString("Username"), rs.getString("Pass_Word"));
				user.setuserID(rs.getInt("userID"));
				user.setPosition(rs.getString("positions"));
				users.add(user);
			}
			
		}
		catch(SQLException ex)
		{
			users = null;
			ex.printStackTrace();
		}
		return users;
	}
	public void Updatemoney(Users user)
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("update users " + 
					"set availablebalance = '"+user.getAvailablebalance()+"'"
							+ ", set pendingamount = '" +user.getPendingbalance() +"'"
									+ ", set awardamount = '" +user.getAwarded()+"'" + 
					" where userid = " + user.getuserID() + ";");
			LoggingUtil.debug("Before autocommit");
			conn.setAutoCommit(false);

			LoggingUtil.debug("Before execute");
			stmt.executeUpdate();
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
}
