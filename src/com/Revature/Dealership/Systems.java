package com.Revature.Dealership;

import java.util.ArrayList;
import java.util.List;

import DealershipInterface.LoginInterface;

public class Systems
{
	public static DealerLot Lot = new DealerLot();
	public static List<Customer> customers = new ArrayList<>();
	public static void main(String[] args)
	{
		LoginScreen Screen = new LoginScreen();
	}


}
