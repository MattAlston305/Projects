package com.revature.services;

import com.revature.pojos.Users;

public interface UsersService 
{
	public Users login(String username, String password);
	public void createAccount(String username, String password);
}
