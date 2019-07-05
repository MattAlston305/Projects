package com.Revature.Dealership;

import java.util.HashMap;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import DealershipInterface.EmployeePermissions;

public class Employee extends EmployeePermissions implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8414769786363777415L;
	private String Username ="";
	private String Password ="";
	private Map<Customer, Map<Cars,List<Double>>> payments = new HashMap<Customer, Map<Cars,List<Double>>>();
	private Inventory is;

	public Employee(String username2, String password2, Inventory i) 
	{
		Username = username2;
		Password = password2;
		is = i;
	}
	public String getPassword()
	{
		return Password;
	}
	public String getUsername()
	{
		return Username;
	}
	@Override
	public void addCar(Cars car) 
	{
		is.getLot().addCarLotC(car);
		is.getLot().addCarLotE(car, car.getOffers());
	}

	@Override
	public void removeCar(Cars car) 
	{
		is.getLot().ViewCarsE().remove(car);
		is.getLot().ViewCarsE().remove(car);
	}

	@Override
	public void acceptOffers(Cars car, double offer, Customer consumer, List<Customer> consumers) 
	{
		is.getLot().Soldcar(car, offer, consumer, consumers);
	}

	@Override
	public void rejectOffers(Cars car, Customer C) 
	{
		is.getLot().ViewCarLotE().get(car).remove(C);
	}

	@Override
	public Map<Customer, Map<Cars, List<Double>>> viewPayments()
	{
		return payments;
	}

	@Override
	public void ViewLotinfo() 
	{
		if(is.getLot().ViewCarLotE().isEmpty())
		{
			System.out.println("Lot is Empty. Please add a car.");
		}
		else
		{
			int i =0;
			for(Cars C : is.getLot().ViewCarsE())
			{
				System.out.println((i+1) +  ". " + is.getLot().ViewCarsE().get(i).getCarType() 
									+" "+ is.getLot().ViewCarLotE().get(C).toString());
				i++;
			}
		}
	}
	
}
