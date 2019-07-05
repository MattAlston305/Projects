package com.Revature.Dealership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerLot implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cars> CarList = new ArrayList<>();
	private Map<Cars, Map<Customer ,Double>> Lotcars = new HashMap<Cars, Map<Customer ,Double>>();
	private Map<Cars, Double> BoughtCars = new HashMap<Cars, Double>();

	public void addCarLotC(Cars car)
	{
		CarList.add(car);
	}
	public void ViewCarsC()
	{
		int i = 1;
		for(Cars c : CarList)
		{
			System.out.print(" " + i + ". ");
			System.out.println(c.getCarType());
			i++;
		}
	}
	public void addCarLotE(Cars c, Map<Customer, Double> offers)
	{
		Lotcars.put(c, offers);
	}
	public Map<Cars, Map<Customer, Double>> ViewCarLotE()
	{
		return Lotcars;
	}

	public void Soldcar(Cars car, double offer, Customer C, List<Customer> customers)
	{
		BoughtCars.put(car, offer);
		customers.get(customers.indexOf(C)).AddOwnedCar(car);
		customers.get(customers.indexOf(C)).addPayments(offer, car);
		Lotcars.remove(car);
		CarList.remove(car);
		car.getOffers().clear();
	}
	public List<Cars> ViewCarsE()
	{
		return CarList;
	}
}
