package com.revature.Dealership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.revature.ImpUtil.EmployeePostgresqlDAOImp;

public class EmployeeList implements Serializable
{
	private EmployeePostgresqlDAOImp emp = new EmployeePostgresqlDAOImp();

	public List<Employee> getEmployees() 
	{
		return emp.getAllEmployees();
	}

	
	
}
