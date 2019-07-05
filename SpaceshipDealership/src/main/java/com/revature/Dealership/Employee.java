package com.revature.Dealership;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.DAOs.LoggingUtil;
import com.revature.ImpUtil.CarsPostgresqlDAOImp;
import com.revature.ImpUtil.OfferPostgresDAOImp;
import com.revature.ImpUtil.PaymentPostgresDAOImp;

import DealershipInterface.EmployeePermissions;

public class Employee extends EmployeePermissions implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8414769786363777415L;
	private String Username ="";
	private String Password ="";
	private OfferPostgresDAOImp offer = new OfferPostgresDAOImp();
	private PaymentPostgresDAOImp payment = new PaymentPostgresDAOImp();
	private CarsPostgresqlDAOImp cars = new CarsPostgresqlDAOImp();

	public Employee(String username2, String password2) 
	{
		Username = username2;
		Password = password2;
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
		cars.createCar(car);
	}

	@Override
	public void removeCar(int i) 
	{
		cars.DestroyCar(i);
	}

	@Override
	public void acceptOffers(Cars car, Customer consumer) 
	{
		offer.AcceptOffer(car.getCar_Id(), consumer.getCustomer_id());
		int payments = offer.getOfferbyCarandCustomer(car, consumer).getOffer();
		cars.updateCarPurchase(consumer, car);
		payment.newPayment(car, payments);
	}

	@Override
	public void rejectOffers(Cars car, Customer C) 
	{
		offer.RejectOffer(car.getCar_Id(), C.getCustomer_id());
	}

	@Override
	public void viewPayments()
	{
		int d = 1;
		for(int p : payment.getAllPayments())
		{
			System.out.println(d + ". " + p);
			d++;
		}
	}

	@Override
	public void ViewLotinfo() 
	{
		if(cars.getAllCars().isEmpty())
		{
			System.out.println("lot is empty. please add a car");
		}
		else
		{
			for(Cars c : cars.getAllCars())
			{
				System.out.println(c.getCar_Id() + ". " + c.getCarType());
			}
		}
	}
	public List<Offer> ViewCarOffers(Cars car)
	{
		return offer.getAllOffersbyCar(car);
	}
}
