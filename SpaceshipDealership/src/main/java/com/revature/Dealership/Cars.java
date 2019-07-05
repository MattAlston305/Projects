package com.revature.Dealership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars
{
	private Map<Customer, Double> offers = new HashMap<>();
	private String carType;
	private int Car_Id;
	
	public Cars(String car)
	{
		this.setCarType(car);
	}

	public int getCar_Id() {
		return Car_Id;
	}

	public void setCar_Id(int car_Id) {
		Car_Id = car_Id;
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
