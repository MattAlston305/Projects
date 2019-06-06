package com.Revature.Dealership;

import java.util.Scanner;

public class CustomerScreen 
{
	Customer custom;
	String Username;
	public CustomerScreen(String Username)
	{
		this.Username = Username;
		System.out.println("\t\t\t -------- Customer Screen --------");
		System.out.println("Welcome " + Username);
		customerMenu();
		
	}
	public void customerMenu()
	{
		System.out.println("\n\n\t\t Menu\n"
							+ "\t\t 1. View Cars\n"
							+ "\t\t 2. View Owned Cars\n"
							+ "\t\t 3. View Payments\n"
							+ "\t\t 4. Logout\n");
		Scanner scand = new Scanner(System.in);
		int choice = scand.nextInt();
		switch(choice)
		{
			case 1:
				boolean valid = true;
				do
				{
					Systems.Lot.ViewCarsC();
					System.out.println("Do you want to make a offer on a car?");
					scand = new Scanner(System.in);
					String s = scand.nextLine();
					if(s.equals("yes") || s.equals("Yes") || s.equals("y"))
					{
						System.out.println("Choose the car? ");
						scand = new Scanner(System.in);
						int c = scand.nextInt();
						System.out.println("What is your offer?");
						scand = new Scanner(System.in);
						double number = scand.nextInt();
						Systems.Lot.ViewCarsE().get(c-1).getOffers().put(custom, number);
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
				custom.ViewOwnedCars();
				break;
			case 3:
				System.out.println(custom.ViewPayments());
				break;
			case 4:
				LoginScreen screen = new LoginScreen();
		}
	}

}
