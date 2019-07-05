package com.Revature.DAOs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.Revature.Dealership.Inventory;

public class InvenSerializableDAO implements InventoryDAO
{
	private static final String FILENAME = "Inventory.txt";
	@Override
	public void saveInventory(Inventory i) 
	{
		ObjectOutputStream oss = null;
		FileOutputStream foo = null;
		
		try
		{
			foo = new FileOutputStream(FILENAME);
			oss = new ObjectOutputStream(foo);
			oss.writeObject(i);
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(IOException e)
		{
			
		}
		finally
		{
			try
			{
				if(oss != null)
				{
					oss.close();
				}
			}
			catch(IOException e)
			{
				
			}
		}
	}

	@Override
	public Inventory loadInventory() 
	{
		Inventory myInventory = null;
		try(FileInputStream fis = new FileInputStream(FILENAME);)
		{
			ObjectInputStream oii = new ObjectInputStream(fis);
			myInventory = (Inventory) oii.readObject();
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(IOException e)
		{
			
		}
		catch(ClassNotFoundException e)
		{
			
		}
		return myInventory;
	}
	

}
