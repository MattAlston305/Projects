package com.revature.DAOs;

import java.util.List;

import com.revature.Dealership.Cars;

public interface CarsDAO 
{
	public void createCar(Cars c);
	public Cars getCarbyId(int id);
	public Cars getCarbyName(String name);
	public List<Cars> getAllCars();
	public List<Cars> getAllCarsbyCustomer(int customerID);
	
}
