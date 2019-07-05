package com.revature.services;

import java.util.List;

import com.revature.daos.UsersDAOImp;
import com.revature.pojos.BenefitsCoordinator;
import com.revature.pojos.DepartmentHead;
import com.revature.pojos.Employee;
import com.revature.pojos.Supervisor;
import com.revature.pojos.Users;

public class UserServiceImp implements UsersService 
{
	private UsersDAOImp userDAO = new UsersDAOImp();

	public Users login(String username, String password) 
	{
		Users user = userDAO.getUserByUsername(username);
			if(user.getUsername().equals(username) && user.getPassword().equals(password))
			{
				return user;
			}
			else
			{
				return null;
			}
		
	}

	public void createAccount(String username, String password) {
		Users users = new Users(username, password);
		userDAO.createUser(users);
		
	}

}
