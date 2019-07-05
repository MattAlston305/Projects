package com.revature.ImpUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.DAOs.LoggingUtil;
import com.revature.DAOs.OfferDAO;
import com.revature.Dealership.Cars;
import com.revature.Dealership.Customer;
import com.revature.Dealership.Offer;

public class OfferPostgresDAOImp implements OfferDAO 
{	
	Connection conn = ConnectionFactory.getConnection();
	
	@Override
	public void createOffer(Customer c, int carId, int offer) 
	{
		try 
		{
			PreparedStatement stmt = conn.prepareStatement("insert into offer(cu_id, cr_id, offer_price) values(?, ?, ?)");
			stmt.setInt(1, c.getCustomer_id());
			stmt.setInt(2, carId);
			stmt.setInt(3, offer);
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} 
		catch (SQLException e) 
		{
			try
			{
				conn.rollback();
			}
			catch(SQLException d)
			{
				d.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<Offer> getAllOffersbyCar(Cars car) 
	{
		List<Offer> offers = new ArrayList<Offer>();
		String sql = "select * from offer where cr_id = " + "'"+car.getCar_Id()+"'" + " and status != 'Rejected';";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				offers.add(new Offer(rs.getInt("offer_id"), rs.getInt("cu_id"), rs.getInt("cr_id"), rs.getInt("offer_price"), rs.getString("status")));
			}
			return offers;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Offer> getAllOffersforCustomer(Customer c) {
		List<Offer> offers = new ArrayList<Offer>();
		String sql = "select * from offer where cu_id = " + "'" + c.getCustomer_id() + "'" + " and status != 'Rejected';";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				offers.add(new Offer(rs.getInt("offer_id"), rs.getInt("cu_id"), rs.getInt("cr_id"), rs.getInt("offer_price"), rs.getString("status")));
			}
			return offers;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
			
		}
	}
	
	public Offer getOfferbyCarandCustomer(Cars car, Customer c)
	{
		Offer off = null;
		String sql = "select * from offer where cu_id = " + "'" + c.getCustomer_id() + "'" + " and cr_id = " + "'" + car.getCar_Id() + "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				off = new Offer(rs.getInt("offer_id"), rs.getInt("cu_id"), rs.getInt("cr_id"), rs.getInt("offer_price"), rs.getString("status"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return off;
	}
	
	public void AcceptOffer(int car_id, int customer_id)
	{
		try 
		{
			PreparedStatement stmt = conn.prepareStatement("update offer set status = 'Accepted' where cr_id = " + "'" + car_id +"'" + " and " + " cu_id = " + "'" + customer_id + "'");
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch (SQLException e) 
		{
			try
			{
				conn.rollback();
				LoggingUtil.error("Offer not accepted");
			}
			catch(SQLException d)
			{
				d.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void RejectOffer(int car_id, int customer_id)
	{
		try 
		{
			PreparedStatement stmt = conn.prepareStatement("update offer set status = 'Rejected' where cr_id = " + "'" + car_id +"'" + " and " + " cu_id = " + "'" + customer_id + "'");
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch (SQLException e) 
		{
			try
			{
				conn.rollback();
				LoggingUtil.error("Offer not accepted");
			}
			catch(SQLException d)
			{
				d.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
