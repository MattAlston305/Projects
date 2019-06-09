package DealershipInterface;

import com.Revature.Dealership.Inventory;

public interface LoginInterface 
{
    public String UserName ="";
	public String Password ="";
	
	public void login(Inventory i, String username, String password);
	public void CreateAccount(Inventory i, String username, String password);

}
