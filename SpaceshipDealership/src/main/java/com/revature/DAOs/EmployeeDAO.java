package com.revature.DAOs;

import java.util.List;

import com.revature.Dealership.Employee;

public interface EmployeeDAO 
{
	public void createEmployee(Employee e);
	public Employee getEmployeebyId(int Id);
	public Employee getEmployeebyName(String name);
	public List<Employee> getAllEmployees();

}
