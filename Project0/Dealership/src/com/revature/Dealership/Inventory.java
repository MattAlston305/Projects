package com.Revature.Dealership;

import java.io.Serializable;

public class Inventory implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8762895743015483010L;
	private CustomerList CList;
	private EmployeeList EList;
	private DealerLot Lot;
	
	public Inventory()
	{
		setCList(new CustomerList());
		setEmp(new EmployeeList());
		setLot(new DealerLot());
	}

	public CustomerList getCList() {
		return CList;
	}

	public void setCList(CustomerList cList) {
		CList = cList;
	}

	public EmployeeList getEmp() {
		return EList;
	}

	public void setEmp(EmployeeList emp) {
		this.EList = emp;
	}

	public DealerLot getLot() {
		return Lot;
	}

	public void setLot(DealerLot lot) {
		Lot = lot;
	}
}
