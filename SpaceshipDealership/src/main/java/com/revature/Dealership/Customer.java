package com.revature.Dealership;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.DAOs.LoggingUtil;
import com.revature.ImpUtil.CarsPostgresqlDAOImp;
import com.revature.ImpUtil.OfferPostgresDAOImp;
import com.revature.ImpUtil.PaymentPostgresDAOImp;

import DealershipInterface.UserPermissions;

public class Customer extends UserPermissions
{
	/**
	 * 
	 */

	private String Username;
	private String Password;
	private int customer_id;
	private CarsPostgresqlDAOImp cars = new CarsPostgresqlDAOImp();
	private PaymentPostgresDAOImp pays = new PaymentPostgresDAOImp();
	
	

	public Customer(String Username, String Password)
	{
		this.setUsername(Username);
		this.setPassword(Password);
	}
	private void setUsername(String username2) {
		this.Username = username2;
		
	}
	private void setPassword(String password2) {
		this.Password = password2;
	}

	@Override
	public void ViewOwnedCars() 
	{
		if(cars.getAllCarsbyCustomer(customer_id).isEmpty())
		{
			System.out.println("You do not own any of our cars");
		}
		else
		{
			System.out.println(customer_id);
			for(Cars c: cars.getAllCarsbyCustomer(customer_id))
			{
				System.out.println(c.getCar_Id() + ". " + c.getCarType());
			}
		}
	}

	@Override
	public void ViewPayments() 
	{
		
		for(Cars c: cars.getAllCarsbyCustomer(customer_id))
		{
			System.out.println(c.getCar_Id() + ". " + c.getCarType() +" :"+ pays.getPaymentbyCar(c));
			System.out.println(pays.getPaymentbyCar(c));
		}
		
	}
	public String getPassword() {
		return Password;
	}

	public String getUsername() {
		return Username;
	}
	
	public void AddOwnedCar(Cars c)
	{
		
	}
	public void addPayments(double offer, Cars c)
	{
		
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public void ViewCars()
	{
		for(Cars c : cars.getAllCars())
		{
			System.out.println(c.getCar_Id() + ". " + c.getCarType());
		}
	}
		
}
