package com.revature.services;

import java.util.List;

import com.revature.daos.TRFormDAOImp;
import com.revature.daos.UsersDAOImp;
import com.revature.pojos.Employee;
import com.revature.pojos.TRForm;
import com.revature.pojos.Users;

public class TRFormServiceImp implements TRFormService {
	
	private TRFormDAOImp TRFormDao = new TRFormDAOImp();
	private UsersDAOImp UserDAO = new UsersDAOImp();
	private double pending;

	public double getpending()
	{
		return pending;
	}
	@Override
	public void submit(TRForm form, Users e) {
		TRFormDao.createForm(form, e);
		this.pending = form.getEventCost()*form.getPayback();

	}

	@Override
	public List<TRForm> viewFormforEmployee(int eid) {
		return TRFormDao.getFormByEmployeeid(eid);
	}

	@Override
	public List<TRForm> viewFormforSupervisors(int eid) {
		return TRFormDao.getFormByEmployeeid(eid);
	}

	@Override
	public List<TRForm> viewFormforDeptHeads(int eid) {
		return TRFormDao.getFormByEmployeeid(eid);
	}

	@Override
	public List<TRForm> viewFormforBenefitsCoord(int eid) {
		return TRFormDao.getFormByEmployeeid(eid);
	}
	public void ApproveForm(int fid, Users user, String status)
	{
		int id = TRFormDao.UpdateFormByFormID(fid, user, status);
		System.out.println(id);
		Users userd = UserDAO.getUserById(id);
		System.out.println(userd.getTotalbalance());
		if(id !=0 && UserDAO.getUserById(id).getPosition().equalsIgnoreCase("BenefitCoord"))
		{
			Users usere = UserDAO.getUserById(id);
			
			usere.setAwarded(pending+usere.getAwarded());
			System.out.println(usere.getAwarded());
			usere.setTotalbalance(usere.getTotalbalance()-pending);
			usere.setAvailablebalance(usere.getTotalbalance()-usere.getPendingbalance()-usere.getAwarded());
			UserDAO.Updatemoney(usere);
			
		}
	}
	public void UpdateForm(int fid, Users user, String status, String Grade)
	{
		TRFormDao.UpdateFormByFID(fid, user, status, Grade);
	}
	public List<TRForm> viewAllForms(){
		return TRFormDao.getAllForms();
		
	}

}
