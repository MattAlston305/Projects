package com.Revature.Dealership;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerLot 
{
	private static List<Cars> CarList = new ArrayList<>();
	private static Map<Cars, Map<Customer ,Double>> Lotcars = new HashMap<Cars, Map<Customer ,Double>>();
	private static Map<Cars, Double> BoughtCars = new HashMap<Cars, Double>();

	public void addCarLotC(Cars car)
	{
		CarList.add(car);
	}
	public void ViewCarsC()
	{
		CarList.add(new Cars("Honda"));
		CarList.add(new Cars("Ford"));
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

	public void Soldcar(Cars car, double offer, Customer C)
	{
		BoughtCars.put(car, offer);
		Systems.customers.get(Systems.customers.indexOf(C)).AddOwnedCar(car);
		Lotcars.remove(car);
		CarList.remove(car);
		car.getOffers().clear();
	}
	public List<Cars> ViewCarsE()
	{
		return CarList;
	}
}
