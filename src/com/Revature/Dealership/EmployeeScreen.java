package com.Revature.Dealership;

import java.util.Scanner;

public class EmployeeScreen 
{
	private String Username;
	private Employee emp = new Employee();
	public EmployeeScreen(String Username)
	{
		this.Username = Username;
		System.out.println("\t\t\t -------- Employee Screen --------");
		System.out.println("Welcome " + Username);
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
				emp.ViewLotinfo();
				EmployeeMenu();
				break;
			case 2:
				System.out.println("Enter the car's name  you want to add?");
				scand = new Scanner(System.in);
				String name = scand.nextLine();
				emp.addCar(new Cars(name));
				emp.ViewLotinfo();
				break;
			case 3:
				emp.ViewLotinfo();
				System.out.println("Enter the car number you want to remove");
				scand = new Scanner(System.in);
				int w = scand.nextInt();
				emp.removeCar(Systems.Lot.ViewCarsE().get(w-1));
				break;
			case 4:
				
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
		}
	}

}
