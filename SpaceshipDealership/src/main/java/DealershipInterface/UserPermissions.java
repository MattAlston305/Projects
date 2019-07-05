package DealershipInterface;

import java.util.List;
import java.util.Map;

import com.revature.Dealership.Cars;

public abstract class UserPermissions 
{
	public boolean LoginCheck(String Username, String Password)
	{
		if(Username.contains("@yahoo.com")||Username.contains("@gmail.com"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public abstract void ViewOwnedCars();
	public abstract void ViewPayments();
}
