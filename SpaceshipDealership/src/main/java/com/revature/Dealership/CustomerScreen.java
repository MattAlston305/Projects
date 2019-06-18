package com.revature.Dealership;

import java.util.List;
import java.util.Scanner;

import com.revature.DAOs.LoggingUtil;
import com.revature.ImpUtil.CarsPostgresqlDAOImp;
import com.revature.ImpUtil.ConnectionFactory;
import com.revature.ImpUtil.OfferPostgresDAOImp;

public class CustomerScreen 
{
	private Customer cs;
	private OfferPostgresDAOImp offers = new OfferPostgresDAOImp();
	private CarsPostgresqlDAOImp cars = new CarsPostgresqlDAOImp();
	public CustomerScreen(Customer c)
	{
		cs = c;
		System.out.println("\t\t\t -------- Customer Screen --------");
		System.out.println("Welcome " + cs.getUsername());
		customerMenu();
	}
	public void customerMenu()
	{
		System.out.println("\n\n\t\t Menu\n"
							+ "\t\t 1. View Cars\n"
							+ "\t\t 2. View Offers\n"
							+ "\t\t 3. View Owned Cars\n"
							+ "\t\t 4. View Payments\n"
							+ "\t\t 5. Logout\n");
		Scanner scand = new Scanner(System.in);
		int choice = scand.nextInt();
		switch(choice)
		{
			case 1:
				LoggingUtil.info("Customer viewing the Lot");
				cs.ViewCars();
				LoggingUtil.info("would you like to make an offer on a car");
				scand = new Scanner(System.in);
				String c = scand.nextLine();
				if(c.toLowerCase().equals("yes"))
				{
					cs.ViewCars();
					LoggingUtil.info("What car would like to make a offer on?");
					scand = new Scanner(System.in);
					int car = scand.nextInt();
					LoggingUtil.info("Enter your offer: ");
					scand = new Scanner(System.in);
					int offer = scand.nextInt();
					offers.createOffer(cs, car, offer);
					customerMenu();
				}
				else
				{
					customerMenu();
				}
				break;
			case 2:
				for(Offer o : offers.getAllOffersforCustomer(cs))
				{
					
					System.out.println("Car " + cars.getCarbyId(o.getCarid()).getCarType() + "\n Price: " + o.getOffer() + "\n Status: " + o.getStatus());
				}
				customerMenu();
				break;
			case 3:
				LoggingUtil.info("Customer viewing Their Cars.");
				cs.ViewOwnedCars();
				customerMenu();
				break;
			case 4:
				cs.ViewPayments();
				customerMenu();
				break;
			case 5:
				System.out.println("You are loginning out");
				LoginScreen screen = new LoginScreen();
				break;
		}
	}

}
