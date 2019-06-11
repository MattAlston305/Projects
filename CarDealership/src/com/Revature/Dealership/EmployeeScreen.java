package com.Revature.Dealership;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import com.Revature.DAOs.LoggingUtil;

public class EmployeeScreen 
{
	

	private Inventory iv;
	private Employee emp;
	
	public EmployeeScreen(Inventory inv, Employee e)
	{
		emp = e;
		iv = inv;
		System.out.println("\t\t\t -------- Employee Screen --------");
		System.out.println("Welcome " + emp.getUsername());
		EmployeeMenu();
	}
	
	public void EmployeeMenu()
	{
		System.out.println("\t\t Employee Menu\n"
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
				
				System.out.print("Enter the car's name  you want to add: ");
				scand = new Scanner(System.in);
				String name = scand.nextLine();
				emp.addCar(new Cars(name));
				emp.ViewLotinfo();
				EmployeeMenu();
				break;
			case 3:
				emp.ViewLotinfo();
				System.out.print("Enter the car number you want to remove: ");
				scand = new Scanner(System.in);
				int w = scand.nextInt();
				if(iv.getLot().ViewCarsE().isEmpty())
				{
					System.out.println("No cars in Lot.");
					EmployeeMenu();
				}
				else
				{
					emp.removeCar(iv.getLot().ViewCarsE().get(w-1));
					EmployeeMenu();
				}
				break;
			case 4:
				emp.ViewLotinfo();
				if(iv.getLot().ViewCarsE().isEmpty())
				{
					System.out.println("No Cars in Lot.");
					EmployeeMenu();
				}
				else
				{
				System.out.print("Enter the car number you want to work with: ");
				scand = new Scanner(System.in);
				int d = scand.nextInt();
				Cars car = iv.getLot().ViewCarsE().get(d-1);
				int i =1;
				for(Customer c : iv.getLot().ViewCarLotE().get(car).keySet())
				{
					System.out.println(i +". "+ c.getUsername());
					i++;
				}
				System.out.println("Enter customer number");
				scand = new Scanner(System.in);
				int l = scand.nextInt();
				Customer custom =(Customer) iv.getLot().ViewCarLotE().get(car).keySet().toArray()[l-1];
				double off = iv.getLot().ViewCarLotE().get(car).get(custom);
				emp.acceptOffers(car, off, custom);
				EmployeeMenu();
				}
				break;
			case 5:
				emp.ViewLotinfo();
				System.out.print("Enter the car number you want to work with: ");
				scand = new Scanner(System.in);
				int j = scand.nextInt();
				Cars car2 = iv.getLot().ViewCarsE().get(j-1);
				Customer custom2 = (Customer) iv.getLot().ViewCarLotE().get(car2).keySet().toArray()[j-1];
				emp.rejectOffers(car2, custom2);
				EmployeeMenu();
				break;
			case 6:
				emp.viewPayments();
				EmployeeMenu();
				break;
			case 7:
				System.out.println("You are loginning out");
				LoginScreen screen = new LoginScreen(iv);
				break;
		}
	}

}
