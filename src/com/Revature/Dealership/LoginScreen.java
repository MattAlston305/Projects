package com.Revature.Dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DealershipInterface.LoginInterface;

public class LoginScreen implements LoginInterface 
{
	
	public LoginScreen()
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your Email: ");
		String username = scan.nextLine();
		System.out.print("Enter your password: ");
		String password = scan.nextLine();
		boolean there = false;
		if(username.contains("E@"))
		{
			login(username, password);
		}
		else
		{
			for(int i = 0; i<Systems.customers.size(); i++)
			{
				if(Systems.customers.get(i).getUsername().contentEquals(username))
				{
					for(Customer c: Systems.customers)
					{
						System.out.println(c.getUsername());
					}
					login(username, password);
					break;
				}
				else
				{
					there = true;
				}
			}
			if(there == true)
			{
				Systems.customers.add(new Customer(username, password));
				login(username, password);
			}
		}
	}
	
	@Override
	public void login(String Username, String Password) 
	{
		
		if(Username.contains("@revCars.com"))
		{
			EmployeeScreen es = new EmployeeScreen(Username);
		}
		else if(Username.contains("@yahoo.com") || Username.contains("@gmail.com") )
		{
			CustomerScreen cs = new CustomerScreen(Username);
		}
	}
}
