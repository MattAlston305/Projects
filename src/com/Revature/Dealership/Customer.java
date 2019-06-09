package com.Revature.Dealership;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DealershipInterface.UserPermissions;

public class Customer extends UserPermissions implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4628557409595895134L;
	private String Username;
	private String Password;
	private List<Double> offers = new ArrayList<>();
	private List<Cars> OwnedCars = new ArrayList<>();
	private Map<Cars, List<Double>> payments = new HashMap<>();

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
	public double makeOffer(double offer) 
	{
		offers.add(offer);
		return offer;
	}

	@Override
	public void ViewOwnedCars() 
	{

		int i = 1;
		for(Cars c : OwnedCars)
		{
			System.out.print(" " + i + ". ");
			System.out.println(c.getCarType());
			i++;
		}
	}

	@Override
	public Map<Cars, List<Double>> ViewPayments() 
	{
		return payments;
	}
	public String getPassword() {
		return Password;
	}

	public String getUsername() {
		return Username;
	}
	
	public void AddOwnedCar(Cars c)
	{
		this.OwnedCars.add(c);
	}
	public void addPayments(double offer, Cars c)
	{
		List<Double> payment = new ArrayList<>();
		for(int i =0; i < 13; i++)
		{
			payment.add(offer/12);
		}
		this.payments.put(c, payment);
	}
		
}
