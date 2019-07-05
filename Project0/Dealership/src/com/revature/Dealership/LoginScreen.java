package com.Revature.Dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DealershipInterface.LoginInterface;

public class LoginScreen implements LoginInterface 
{
	Inventory I;
	public LoginScreen(Inventory i)
	{
		I = i;
		System.out.println("\t\tWelcome to revCars Dealership\n");
		System.out.println("\t\t 1. Login");
		System.out.println("\t\t 2. Create Account\n"
				+ "\t\t 3. Exit System\n");
		Scanner scan = new Scanner(System.in);
		boolean valid = true;
		int choice = scan.nextInt();
		while(valid)
		{
			switch(choice)
			{
				case 1:
					System.out.println("Enter Username: ");
					scan = new Scanner(System.in);
					String username = scan.nextLine();
					System.out.println("Enter Password: ");
					scan = new Scanner(System.in);
					String password = scan.nextLine();
					login(i, username, password);
					valid = false;
					break;
				case 2:
					System.out.println("Enter Username: ");
					scan = new Scanner(System.in);
					String username2 = scan.nextLine();
					System.out.println("Enter Password: ");
					scan = new Scanner(System.in);
					String password2 = scan.nextLine();
					CreateAccount(i, username2, password2);
					valid = false;
					break;
				case 3:
					Systems.dao.saveInventory(i);
					System.exit(0);
				default:
					System.out.println("Please enter 1 or 2 please");
					valid = true;
					break;
			}
		}
		
	}
	
	@Override
	public void login(Inventory i, String username, String password) 
	{
		if(username.contains("yahoo") || username.contains("gmail"))
		{
			for(int is = 0; is < i.getCList().getCustomers().size(); is++)
			{
				if(i.getCList().getCustomers().get(is).getUsername() ==  username && i.getCList().getCustomers().get(is).getPassword() == password)
				{
					CustomerScreen cs = new CustomerScreen(i, i.getCList().getCustomers().get(is));
				}
				else
				{
					System.out.println("Please Create an Account or retry");
					LoginScreen screen = new LoginScreen(i);
				}
			}
		}
		else
		{
			for(int is = 0; is < i.getEmp().getEmployees().size(); is++)
			{
				if(i.getEmp().getEmployees().get(is).getUsername() == username && i.getEmp().getEmployees().get(is).getPassword() == password)
				{
					EmployeeScreen es = new EmployeeScreen(i, i.getEmp().getEmployees().get(is));
				}
				else
				{
					System.out.println("Please Create an Account or retry");
					LoginScreen screen = new LoginScreen(i);
				}
			}
		}
	}

	@Override
	public void CreateAccount(Inventory i, String username, String password) 
	{
		if(username.contains("yahoo")||username.contains("gmail"))
		{
			Customer c = new Customer(username,password);
			i.getCList().getCustomers().add(c);
			CustomerScreen cs = new CustomerScreen(i,i.getCList().getCustomers().get(i.getCList().getCustomers().indexOf(c)));
		}
		else
		{
			Employee e = new Employee(username,password, i);
			i.getEmp().getEmployees().add(e);
			EmployeeScreen es = new EmployeeScreen(i,i.getEmp().getEmployees().get(i.getEmp().getEmployees().indexOf(e)));
		}
	}
}
