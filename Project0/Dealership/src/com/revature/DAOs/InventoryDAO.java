package com.Revature.DAOs;

import com.Revature.Dealership.Inventory;

public interface InventoryDAO 
{
	public void saveInventory(Inventory i);
	public Inventory loadInventory();

}
