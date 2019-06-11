package com.Revature.Dealership;



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
			LoginScreen Screen = new LoginScreen(inv);
		}
		else
		{
			inv = dao.loadInventory();
			LoginScreen Screen = new LoginScreen(inv);
		}
	}


}
