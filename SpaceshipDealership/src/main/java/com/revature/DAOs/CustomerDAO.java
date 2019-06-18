package com.revature.DAOs;

import java.util.List;

import com.revature.Dealership.Customer;

public interface CustomerDAO 
{
	public void createCustomer(Customer c);
	public Customer getCustomerbyID(int id);
	public Customer getCustomerbyUsername(String username);
	public List<Customer> getAllCustomers();
	
}
