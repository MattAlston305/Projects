package com.Revature.Dealership;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Revature.DAOs.InvenSerializableDAO;

public class Systems
{
	public static InvenSerializableDAO dao = new InvenSerializableDAO();
	public static void main(String[] args)
	{
		Inventory inv;
		if(dao.loadInventory()== null)
		{
			inv = new Inventory();
			DealerLot Lot = new DealerLot();
			CustomerList customers = new CustomerList();
			EmployeeList emp = new EmployeeList();
			LoginScreen Screen = new LoginScreen(inv);
		}
		else
		{
			inv = dao.loadInventory();
			DealerLot Lot = inv.getLot();
			CustomerList customers = inv.getCList();
			EmployeeList emp = inv.getEmp();
			LoginScreen Screen = new LoginScreen(inv);
		}
	}


}
