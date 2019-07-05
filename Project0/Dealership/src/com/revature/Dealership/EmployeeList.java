package com.Revature.Dealership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList implements Serializable
{
	private List<Employee> employees = new ArrayList<>();

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
