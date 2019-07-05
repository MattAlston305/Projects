package com.revature.services;

import java.util.List;

import com.revature.pojos.Employee;
import com.revature.pojos.TRForm;
import com.revature.pojos.Users;

public interface TRFormService {
	//submit form
	public void submit(TRForm form, Users e);
	//view forms for employees
	public List<TRForm> viewFormforEmployee(int eid);
	//view forms for supervisors
	public List<TRForm> viewFormforSupervisors(int eid);
	//view forms for department heads
	public List<TRForm> viewFormforDeptHeads(int eid);
	//view forms for benefits coord
	public List<TRForm> viewFormforBenefitsCoord(int eid);
}
