package com.revature.ImpUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import com.revature.DAOs.LoggingUtil;
import com.revature.DAOs.PaymentDAO;
import com.revature.Dealership.Cars;

public class PaymentPostgresDAOImp implements PaymentDAO {

	Connection conn = ConnectionFactory.getConnection();
	@Override
	public void newPayment(Cars c, int payment) 
	{
		try 
		{
			PreparedStatement stmt = conn.prepareStatement("insert into payment(amount, c_id) values(?, ?)");
			stmt.setInt(1, payment);
			stmt.setInt(2, c.getCar_Id());
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
	public int getPaymentbyCar(Cars c) 
	{
		int payment = 0;
		String sql = "select * from payment where c_id = " + "'"+c.getCar_Id()+"'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				payment = rs.getInt("amount");	
			}
			return payment;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getMonthlyPaymentbyCar(Cars c) 
	{
		int payment = 0;
		String sql = "select * from payment where c_id = " + "'"+c.getCar_Id()+"'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				payment = rs.getInt("amount")/12;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return payment;
	}

}
