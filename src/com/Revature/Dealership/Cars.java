package com.Revature.Dealership;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars 
{
	private Map<Customer ,Double> offers = new HashMap<>();
	private String carType;
	
	public Cars(String car)
	{
		this.setCarType(car);
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Map<Customer, Double> getOffers() {
		return offers;
	}

	public void setOffers(double offer, Customer custom) {
		this.offers.put(custom, offer);
	}

}
