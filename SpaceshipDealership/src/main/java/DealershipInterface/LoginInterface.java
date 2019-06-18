package DealershipInterface;

public interface LoginInterface 
{
    public String UserName ="";
	public String Password ="";
	
	public void login(String username, String password, String sor);
	public void CreateAccount(String username, String password, String sor);

}
