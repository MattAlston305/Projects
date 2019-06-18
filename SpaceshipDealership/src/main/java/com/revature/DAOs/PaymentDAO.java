package com.revature.DAOs;

import com.revature.Dealership.Cars;

public interface PaymentDAO 
{
	public void newPayment(Cars c, int payment);
	public int getPaymentbyCar(Cars c);
	public int getMonthlyPaymentbyCar(Cars c);
	
	

}
