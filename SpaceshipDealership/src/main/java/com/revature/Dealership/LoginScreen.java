package com.revature.Dealership;

import java.util.Scanner;

import com.revature.DAOs.LoggingUtil;
import com.revature.ImpUtil.CustomerPostgresDAOImp;
import com.revature.ImpUtil.EmployeePostgresqlDAOImp;

import DealershipInterface.LoginInterface;

public class LoginScreen implements LoginInterface 
{
	CustomerPostgresDAOImp custom = new CustomerPostgresDAOImp();
	EmployeePostgresqlDAOImp emp = new EmployeePostgresqlDAOImp();
	public LoginScreen()
	{
		LoggingUtil.trace("start of program");
		LoggingUtil.info("\t\tWelcome to SmartCars Dealership\n");
		LoggingUtil.info("\t\t 1. Login");
		LoggingUtil.info("\t\t 2. Create Account\n"
					+	 "\t\t 3. Exit System\n");
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
						LoggingUtil.info("Customer(C) or Employee(E)");
						scan = new Scanner(System.in);
						sor = scan.nextLine();
						if(sor.equals("C") || sor.equals("c") || sor.equals("e")||sor.equals("E"))
						{
							isvalid = false;
							LoggingUtil.info("Enter Username: ");
							scan = new Scanner(System.in);
							String username = scan.nextLine();
							LoggingUtil.info("Enter Password: ");
							scan = new Scanner(System.in);
							String password = scan.nextLine();
							login(username, password, sor);
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
						LoggingUtil.info("Customer(C) or Employee(E)");
						scan = new Scanner(System.in);
						sore = scan.nextLine();
						if(sore.equals("C") || sore.equals("c") || sore.equals("e")||sore.equals("E"))
						{
							LoggingUtil.info("Enter Username: ");
							scan = new Scanner(System.in);
							String username2 = scan.nextLine();
							LoggingUtil.info("Enter Password: ");
							scan = new Scanner(System.in);
							String password2 = scan.nextLine();
							CreateAccount(username2, password2, sore);
							valid = false;
						}
						else
						{
							isvalid1 = true;
						}
					}
					break;
				case 3:
					System.exit(0);
				default:
					LoggingUtil.info("Please enter 1 or 2 please");
					valid = true;
					break;
			}
		}
	}
	
	@Override
	public void login(String username, String password, String sor) 
	{
		if(sor.equals("C")||sor.equals("c"))
		{
			if(custom.getAllCustomers().isEmpty())
			{
				LoggingUtil.info("You are our first Customer! Please Create an Account!!");
				LoginScreen screen = new LoginScreen();
			}
			else
			{
				if(custom.getCustomerbyUsername(username).getPassword().equals(password))
				{
					CustomerScreen cs = new CustomerScreen(custom.getCustomerbyUsername(username));
				}
				else
				{
					LoggingUtil.info("Please create a new Account or Retry");
					LoginScreen screen = new LoginScreen();
				}
				
			}
		}
		else if(sor.equals("E")||sor.equals("e"))
		{
			if(emp.getAllEmployees().isEmpty())
			{
				LoggingUtil.info("Please Create an Account");
				LoginScreen screen = new LoginScreen();
			}
			else
			{
				if(emp.getEmployeebyName(username).getPassword().equals(password))
				{
					EmployeeScreen es = new EmployeeScreen(emp.getEmployeebyName(username));
				}
				else
				{
					LoggingUtil.info("Please create a new Account or Retry");
					LoginScreen screen = new LoginScreen();
				}
			}
		}
	}

	@Override
	public void CreateAccount(String username, String password, String sor) 
	{
		if(sor.equals("C") || sor.equals("c"))
		{
			LoggingUtil.info("Customer Account created");
			Customer c = new Customer(username,password);
			custom.createCustomer(c);
			CustomerScreen cs = new CustomerScreen(c);
		}
		else if(sor.equals("E") || sor.equals("e"))
		{
			LoggingUtil.info("Employee Account created");
			Employee e = new Employee(username,password);
			System.out.println(e.getPassword());
			System.out.println(e.getUsername());
			emp.createEmployee(e);
			EmployeeScreen es = new EmployeeScreen(e);
		}
	}
}
