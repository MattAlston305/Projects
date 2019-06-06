package com.Revature.Dealership;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DealershipInterface.EmployeePermissions;

public class Employee extends EmployeePermissions
{
	
	String Username;
	String Password;
	Map<Customer, List<Double>> payments = new HashMap<Customer, List<Double>>();

	@Override
	public void addCar(Cars car) 
	{
		Systems.Lot.addCarLotC(car);
		Systems.Lot.addCarLotE(car, car.getOffers());
	}

	@Override
	public void removeCar(Cars car) 
	{
		Systems.Lot.ViewCarsE().remove(car);
		Systems.Lot.ViewCarsE().remove(car);
	}

	@Override
	public void acceptOffers(Cars car, double offer, Customer consumer) 
	{
		Systems.Lot.Soldcar(car, offer, consumer);
	}

	@Override
	public void rejectOffers(Cars car, Customer C) 
	{
		Systems.Lot.ViewCarLotE().get(car).remove(C);
	}

	@Override
	public Map<Customer, List<Double>> viewPayments()
	{
		return payments;
	}

	@Override
	public void ViewLotinfo() 
	{
		if(Systems.Lot.ViewCarLotE().isEmpty())
		{
			System.out.println("Lot is Empty. Please add a car.");
		}
		else
		{
			int i =0;
			for(Cars C : Systems.Lot.ViewCarsE())
			{
				System.out.println((i+1) +  ". " + Systems.Lot.ViewCarsE().get(i).getCarType() 
									+" "+ Systems.Lot.ViewCarLotE().get(C).toString());
				i++;
			}
		}
	}
	
}
