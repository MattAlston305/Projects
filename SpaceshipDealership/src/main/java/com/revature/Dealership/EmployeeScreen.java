package com.revature.Dealership;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import com.revature.DAOs.LoggingUtil;
import com.revature.ImpUtil.CarsPostgresqlDAOImp;
import com.revature.ImpUtil.ConnectionFactory;
import com.revature.ImpUtil.CustomerPostgresDAOImp;
import com.revature.ImpUtil.OfferPostgresDAOImp;
import com.revature.ImpUtil.PaymentPostgresDAOImp;

public class EmployeeScreen 
{
	
	private Employee emp;
	private OfferPostgresDAOImp offer = new OfferPostgresDAOImp();
	private PaymentPostgresDAOImp payment = new PaymentPostgresDAOImp();
	private CarsPostgresqlDAOImp cars = new CarsPostgresqlDAOImp();
	private CustomerPostgresDAOImp customer = new CustomerPostgresDAOImp();
	public EmployeeScreen(Employee e)
	{
		emp = e;
		System.out.println("\t\t\t -------- Employee Screen --------");
		System.out.println("Welcome " + emp.getUsername());
		
		EmployeeMenu();
	}
	
	public void EmployeeMenu()
	{
		LoggingUtil.info("\t\t Employee Menu\n"
						+  "\t\t 1. View Lot Information\n"
						+  "\t\t 2. Add Car to Lot\n"
						+  "\t\t 3. Remove Car from Lot\n"
						+  "\t\t 4. Accept offer for a Car\n"
						+  "\t\t 5. Reject offer for a Car\n"
						+  "\t\t 6. View Payments for Customers\n"
						+  "\t\t 7. Logout");
		Scanner scand = new Scanner(System.in);
		int choice = scand.nextInt();
		switch(choice)
		{
			case 1:
				LoggingUtil.info("Employee viewing Lot.");
				emp.ViewLotinfo();
				EmployeeMenu();
				break;
			case 2:
				LoggingUtil.info("Adding car to Lot");
				LoggingUtil.info("Enter the car you want to add");
				scand = new Scanner(System.in);
				String newcar = scand.nextLine();
				emp.addCar(new Cars(newcar));
				emp.ViewLotinfo();
				EmployeeMenu();
				break;
			case 3:
				LoggingUtil.info("Removing SmartCar from Lot");
				emp.ViewLotinfo();
				LoggingUtil.info("Enter the SmartCar you want to remove");
				scand = new Scanner(System.in);
				int carnumber = scand.nextInt();
				emp.removeCar(carnumber);
				emp.ViewLotinfo();
				EmployeeMenu();
				break;
			case 4:
				LoggingUtil.info("Accepting offer");
				emp.ViewLotinfo();
				LoggingUtil.info("Enter the carid you want to work with: ");
				scand = new Scanner(System.in);
				int carid = scand.nextInt();
				if(offer.getAllOffersbyCar(cars.getCarbyId(carid)).isEmpty())
				{
					System.out.println("Car has no offers");
					EmployeeMenu();
				}
				else
				{
					for(Offer o: offer.getAllOffersbyCar(cars.getCarbyId(carid)))
					{
						System.out.println("Customer " + o.getCustomerid() + ": "  + customer.getCustomerbyID(o.getCustomerid()).getUsername()
										+  "\n Offer: " + o.getOffer());
					}
					LoggingUtil.info("Enter the customer id of the offer you want to accept: ");
					scand = new Scanner(System.in);
					int cusid = scand.nextInt();
					System.out.println(customer.getCustomerbyID(cusid).getCustomer_id());
					emp.acceptOffers(cars.getCarbyId(carid), customer.getCustomerbyID(1));
					EmployeeMenu();
				}
				
				break;
			case 5:
				LoggingUtil.info("Rejecting an offer");
				emp.ViewLotinfo();
				LoggingUtil.info("Enter the carid you want to work with: ");
				scand = new Scanner(System.in);
				int car = scand.nextInt();
				if(offer.getAllOffersbyCar(cars.getCarbyId(car)).isEmpty())
				{
					System.out.println("Car has no offers");
					EmployeeMenu();
				}
				else
				{
					for(Offer o: offer.getAllOffersbyCar(cars.getCarbyId(car)))
					{
						System.out.println("Customer " + o.getCustomerid() + ": "  + customer.getCustomerbyID(o.getCustomerid()).getUsername()
										+  "\n Offer: " + o.getOffer());
					}
					LoggingUtil.info("Enter the customer id of the offer you want to reject: ");
					scand = new Scanner(System.in);
					int cusid = scand.nextInt();
					System.out.println(customer.getCustomerbyID(cusid).getCustomer_id());
					emp.rejectOffers(cars.getCarbyId(car), customer.getCustomerbyID(1));
					EmployeeMenu();
				}
				EmployeeMenu();
				break;
			case 6:
				emp.viewPayments();
				EmployeeMenu();
				break;
			case 7:
				System.out.println("You are loginning out");
				LoginScreen screen = new LoginScreen();
				break;
		}
	}

}
