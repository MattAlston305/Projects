package com.revature.pojos;

import java.util.List;

import com.revature.daos.UsersDAOImp;

public class FakeDriver {

	private static UsersDAOImp UsersDAO = new UsersDAOImp();
	public static void main(String[] args) 
	{
		List<Users> users = UsersDAO.getAllUsers();
		for(Users u : users)
		{
			System.out.println(u.getUsername());
		}
	}

}
