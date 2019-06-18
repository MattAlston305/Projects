package DealershipInterface;

import java.util.List;
import java.util.Map;

import com.revature.Dealership.Cars;
import com.revature.Dealership.Customer;

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
	public abstract void removeCar(int i);
	public abstract void acceptOffers(Cars car, Customer consumer);
	public abstract void rejectOffers(Cars car, Customer C);
	public abstract void viewPayments();
	public abstract void ViewLotinfo();
}
