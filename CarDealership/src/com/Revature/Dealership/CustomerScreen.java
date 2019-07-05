package com.Revature.Dealership;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import com.Revature.DAOs.LoggingUtil;

public class CustomerScreen 
{
	private Inventory iv;
	private Customer cs;
	public CustomerScreen(Inventory inv, Customer c)
	{
		iv = inv;
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
				boolean valid = true;
				do
				{
					LoggingUtil.info("Customer viewing cars.");
					iv.getLot().ViewCarsC();
					System.out.println("Do you want to make a offer on a car?");
					scand = new Scanner(System.in);
					String s = scand.nextLine();
					if(s.equals("yes") || s.equals("Yes") || s.equals("y"))
					{
						LoggingUtil.info("Customer making offer.");
						System.out.println("Choose the car? ");
						scand = new Scanner(System.in);
						int c = scand.nextInt();
						System.out.println("What is your offer?");
						scand = new Scanner(System.in);
						double number = scand.nextInt();
						iv.getLot().ViewCarsE().get(c-1).getOffers().put(cs, number);
						valid = false;
						customerMenu();
					}
					else if(s.contentEquals("no") || s.contentEquals("No") || s.contentEquals("n"))
					{
						customerMenu();
						valid = false;
					}
					else
					{
						System.out.println("Enter a yes or no please!");
						valid = true;
					}
				}
				while(valid);
					break;
			case 2:
				for(Cars c: iv.getLot().ViewCarsE())
				{
					System.out.print(c.getCarType() + ": ");
					System.out.println(c.getOffers().get(cs));
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
				LoginScreen screen = new LoginScreen(iv);
				break;
		}
	}

}
