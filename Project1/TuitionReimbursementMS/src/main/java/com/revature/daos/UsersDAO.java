package com.revature.daos;

import java.util.List;

import com.revature.pojos.Users;

public interface UsersDAO 
{
	public void createUser(Users e);
	public Users getUserByUsername(String username);
	public Users getUserById(int Id);
	public List<Users> getAllUsers();
}
