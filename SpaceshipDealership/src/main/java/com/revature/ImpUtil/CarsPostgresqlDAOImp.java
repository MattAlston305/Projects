package com.revature.ImpUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.DAOs.CarsDAO;
import com.revature.DAOs.LoggingUtil;
import com.revature.Dealership.Cars;
import com.revature.Dealership.Customer;

public class CarsPostgresqlDAOImp implements CarsDAO {

	private static Connection conn = ConnectionFactory.getConnection();
	
	@Override
	public void createCar(Cars c) 
	{
		try 
		{
			PreparedStatement stmt = conn.prepareStatement("insert into cars(carName) values (?)");
			stmt.setString(1, c.getCarType());

			//LoggingUtil.debug("Before autocommit");
			conn.setAutoCommit(false);
			//LoggingUtil.debug("Before execute");
			stmt.execute();
			//LoggingUtil.debug("After execute");
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
	public Cars getCarbyId(int id) 
	{
		Cars car = null;
		String sql = "select * from cars where car_id = " + "'" + id+ "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				car = new Cars(rs.getString("carName"));
				car.setCar_Id(id);
			}
		}
		catch(SQLException e)
		{
			car = null;
			e.printStackTrace();
		}
		return car;
	}

	@Override
	public Cars getCarbyName(String name) 
	{
		Cars car = null;
		String sql = "select * from cars where carName = " + "'" +name + "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				car = new Cars(rs.getString("carName"));
				car.setCar_Id(rs.getInt("car_id"));
			}
			
		}
		catch(SQLException e)
		{
			car = null;
			e.printStackTrace();
		}
		return car;
	}

	@Override
	public List<Cars> getAllCars() 
	{
		List<Cars> carlist = new ArrayList<Cars>();
		String sql = "select * from cars;";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Cars car = new Cars(rs.getString("carName"));
				car.setCar_Id(rs.getInt("car_id"));
				carlist.add(car);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return carlist;
	}

	@Override
	public List<Cars> getAllCarsbyCustomer(int customerID) 
	{
		List<Cars> carlist = new ArrayList<Cars>();
		String sql = "select * from cars where c_id = " + "'" + customerID + "'";
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Cars newCar = new Cars(rs.getString("carName"));
				newCar.setCar_Id(rs.getInt("car_id"));
				carlist.add(newCar);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return carlist;
	}
	public void updateCarPurchase(Customer c, Cars car)
	{
		String sql = "{call updating_car_proc(?,?)}";
		try
		{
			CallableStatement call = conn.prepareCall(sql);
			call.setInt(1, car.getCar_Id());
			call.setInt(2, c.getCustomer_id());
			call.executeQuery();
		}
		catch(SQLException e)
		{
			LoggingUtil.error("Failed to update car");
			e.printStackTrace();
		}
	}
	public void DestroyCar(int i)
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("delete from cars where car_id = " + "'" +i+"'");

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
}
