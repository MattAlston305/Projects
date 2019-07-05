package com.revature.daos;

import java.util.List;

import com.revature.pojos.Employee;
import com.revature.pojos.TRForm;
import com.revature.pojos.Users;

public interface TRFormDAO 
{
	public void createForm(TRForm form, Users e);
	public List<TRForm> getFormByEmployeeid(int id);
	public List<TRForm> getFormByEmployeeUsername(String username);
	public TRForm getFormByFormid(int id);
	public TRForm getFormByEmployeeidAndFormid(int Eid, int Fid);
	
}
