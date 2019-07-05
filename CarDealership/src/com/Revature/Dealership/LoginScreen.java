package com.Revature.Dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Revature.DAOs.LoggingUtil;

import DealershipInterface.LoginInterface;

public class LoginScreen implements LoginInterface 
{
	Inventory I;
	public LoginScreen(Inventory i)
	{
		LoggingUtil.trace("start of program");
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
					boolean isvalid = true;
					while(isvalid)
					{
						String sor ="";
						System.out.println("Customer(C) or Employee(E)");
						scan = new Scanner(System.in);
						sor = scan.nextLine();
						if(sor.equals("C") || sor.equals("c") || sor.equals("e")||sor.equals("E"))
						{
							isvalid = false;
							System.out.println("Enter Username: ");
							scan = new Scanner(System.in);
							String username = scan.nextLine();
							System.out.println("Enter Password: ");
							scan = new Scanner(System.in);
							String password = scan.nextLine();
							login(I, username, password, sor);
							valid = false;
						}
						else
						{
							isvalid = true;
						}
					}
					break;
				case 2:
					boolean isvalid1 = true;
					while(isvalid1)
					{
						String sore="";
						System.out.println("Customer(C) or Employee(E)");
						scan = new Scanner(System.in);
						sore = scan.nextLine();
						if(sore.equals("C") || sore.equals("c") || sore.equals("e")||sore.equals("E"))
						{
							System.out.println("Enter Username: ");
							scan = new Scanner(System.in);
							String username2 = scan.nextLine();
							System.out.println("Enter Password: ");
							scan = new Scanner(System.in);
							String password2 = scan.nextLine();
							CreateAccount(I, username2, password2, sore);
							valid = false;
						}
						else
						{
							isvalid1 = true;
						}
					}
					break;
				case 3:
					Systems.dao.saveInventory(I);
					System.exit(0);
				default:
					System.out.println("Please enter 1 or 2 please");
					valid = true;
					break;
			}
		}
		
	}
	
	@Override
	public void login(Inventory i, String username, String password, String sor) 
	{
		if(sor.equals("C")||sor.equals("c"))
		{
			if(i.getCList().getCustomers().isEmpty())
			{
				System.out.println("You are our first Customer! Please Create an Account!!");
				LoginScreen screen = new LoginScreen(i);
			}
			else
			{
				for(int is = 0; is < i.getCList().getCustomers().size(); is++)
				{
					if(i.getCList().getCustomers().get(is).getUsername().equals(username) && i.getCList().getCustomers().get(is).getPassword().equals(password))
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
		}
		else if(sor.equals("E")||sor.equals("e"))
		{
			if(i.getEmp().getEmployees().size()==0)
			{
				System.out.println("Please Create an Account");
				LoginScreen screen = new LoginScreen(i);
			}
			else
			{
				for(int is = 0; is < i.getEmp().getEmployees().size(); is++)
				{
					if(i.getEmp().getEmployees().get(is).getUsername().equals(username) && i.getEmp().getEmployees().get(is).getPassword().equals(password))
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
	}

	@Override
	public void CreateAccount(Inventory i, String username, String password, String sor) 
	{
		if(sor.equals("C") || sor.equals("c"))
		{
			LoggingUtil.info("Customer Account created");
			Customer c = new Customer(username,password);
			i.getCList().getCustomers().add(c);
			CustomerScreen cs = new CustomerScreen(i,i.getCList().getCustomers().get(i.getCList().getCustomers().indexOf(c)));
		}
		else if(sor.equals("E") || sor.equals("e"))
		{
			LoggingUtil.info("Employee Account created");
			Employee e = new Employee(username,password, i);
			i.getEmp().getEmployees().add(e);
			EmployeeScreen es = new EmployeeScreen(i,i.getEmp().getEmployees().get(i.getEmp().getEmployees().indexOf(e)));
		}
	}
}
