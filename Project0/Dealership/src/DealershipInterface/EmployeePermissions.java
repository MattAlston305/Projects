package DealershipInterface;

import java.util.List;
import java.util.Map;

import com.Revature.Dealership.Cars;
import com.Revature.Dealership.Customer;

public abstract class EmployeePermissions 
{
	public boolean LoginCheck(String Username, String Password)
	{
		if(Username.contains("@revCars.com"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public abstract void addCar(Cars car);
	public abstract void removeCar(Cars car);
	public abstract void acceptOffers(Cars car, double offer, Customer consumer,List<Customer> consumers);
	public abstract void rejectOffers(Cars car, Customer C);
	public abstract Map<Customer, Map<Cars, List<Double>>> viewPayments();
	public abstract void ViewLotinfo();
}
