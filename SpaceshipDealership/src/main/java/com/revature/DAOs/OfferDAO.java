package com.revature.DAOs;

import java.util.List;

import com.revature.Dealership.Cars;
import com.revature.Dealership.Customer;
import com.revature.Dealership.Offer;

public interface OfferDAO 
{
	public void createOffer(Customer c, int carId, int offer);
	public List<Offer> getAllOffersbyCar(Cars car);
	public List<Offer> getAllOffersforCustomer(Customer c);
	

}
