package com.revature.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Employee;
import com.revature.pojos.TRForm;
import com.revature.pojos.Users;
import com.revature.services.LoggingUtil;

public class TRFormDAOImp implements TRFormDAO {

	private Connection conn = ConnectionFactory.getConnection();
	
	@Override
	public void createForm(TRForm form, Users e) {
		try
		{
			LoggingUtil.debug("IN TRY!!!!!!");
			PreparedStatement stmt = conn.prepareStatement("insert into TRForm(Ename, ETime, EType, EDate, ECost, EGF, PG, Grade, EID, status, EDescript) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, form.getEventName());
			stmt.setString(2, form.getEventTime());
			stmt.setString(3, form.getEventType());
			stmt.setDate(4, form.getEventDate());
			stmt.setInt(5, form.getEventCost());
			stmt.setString(6, form.getEventGradingFormat());
			stmt.setString(7, form.getPassingGrade());
			stmt.setString(8, "N/A");
			stmt.setInt(9, e.getuserID());
			stmt.setString(10, "Pending");
			stmt.setString(11, form.getEventDescription());

			LoggingUtil.debug("Before autocommit");
			conn.setAutoCommit(false);

			LoggingUtil.debug("Before execute");
			stmt.execute();
			LoggingUtil.debug("After execute");
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException ex)
		{
			try
			{
				LoggingUtil.error("SOMETHING BAD");
				conn.rollback();
			}
			catch(SQLException exc)
			{
				exc.printStackTrace();
			}
			ex.printStackTrace();
		}
	}
	
	public List<TRForm> getAllForms()
	{
		List<TRForm> forms = new ArrayList<TRForm>();
		String sql = "select * from TRForm where status != 'Approved'";
		Statement stmt;
		try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					TRForm form = new TRForm(rs.getString("Ename"), rs.getString("ETime"), rs.getString("EType"), rs.getDate("EDate"), rs.getInt("ECost"), rs.getString("EGF"), rs.getString("PG"), rs.getString("EDescript"));
					form.setFormID(rs.getInt("formID"));
					form.setEID(rs.getInt("EID"));
					form.setGrade(rs.getString("Grade"));
					if(rs.getString("status").isEmpty())
					{
						form.setStatus("Pending");
					}
					else
					{
						form.setStatus(rs.getString("status"));	
					}
							
					forms.add(form);
				}
			} catch(SQLException e)
				{
					e.printStackTrace();
				}
		return forms;
	}
	@Override
	public List<TRForm> getFormByEmployeeid(int id) {
		List<TRForm> forms = new ArrayList<TRForm>();
		String sql = "select * from TRForm where EID = " + "'" + id + "'";
		Statement stmt;
		try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					TRForm form = new TRForm(rs.getString("Ename"), rs.getString("ETime"), rs.getString("EType"), rs.getDate("EDate"), rs.getInt("ECost"), rs.getString("EGF"), rs.getString("PG"), rs.getString("EDescript"));
					form.setFormID(rs.getInt("formID"));
					form.setEID(id);
					form.setGrade(rs.getString("Grade"));
					if(rs.getString("status").isEmpty())
					{
						form.setStatus("Pending");
					}
					else
					{
						form.setStatus(rs.getString("status"));	
					}
							
					forms.add(form);
				}
			} catch(SQLException e)
				{
					e.printStackTrace();
				}
		return forms;
	}

	@Override
	public List<TRForm> getFormByEmployeeUsername(String username) {
		List<TRForm> forms = new ArrayList<>();
		String sql = "select * from TRForm where EID in (select empID from employeesp1 where Username = " +"'"+username+"'"+ ")";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				TRForm form = new TRForm(rs.getString("Ename"), rs.getString("ETime"), rs.getString("EType"), rs.getDate("EDate"), rs.getInt("ECost"), rs.getString("EGF"), rs.getString("PG"), rs.getString("EDescript"));
				form.setFormID(rs.getInt("formID"));
				form.setEID(rs.getInt("EID"));
				form.setGrade(rs.getString("Grade"));
				form.setStatus(rs.getString("status"));
				forms.add(form);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return forms;
	}

	@Override
	public TRForm getFormByFormid(int id) {
		TRForm form = null;
		String sql = "select * from TRForm where formID = " + "'" +id+ "'";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				form = new TRForm(rs.getString("Ename"), rs.getString("ETime"), rs.getString("EType"), rs.getDate("EDate"), rs.getInt("ECost"), rs.getString("EGF"), rs.getString("PG"), rs.getString("EDescript"));
				form.setFormID(rs.getInt("formID"));
				form.setEID(rs.getInt("EID"));
				form.setGrade(rs.getString("Grade"));
				form.setStatus(rs.getString("status"));
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return form;
	}

	@Override
	public TRForm getFormByEmployeeidAndFormid(int Eid, int Fid) {
		TRForm form = null;
		String sql = "select * from TRForm where EID = "+ "'" + Eid + "'" + " and formID = " + "'" +Fid+ "'";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				form = new TRForm(rs.getString("Ename"), rs.getString("ETime"), rs.getString("EType"), rs.getDate("EDate"), rs.getInt("ECost"), rs.getString("EGF"), rs.getString("PG"), rs.getString("EDescript"));
				form.setFormID(rs.getInt("formID"));
				form.setEID(rs.getInt("EID"));
				form.setGrade(rs.getString("Grade"));
				form.setStatus(rs.getString("status"));
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return form;
	}
	
	public int UpdateFormByFormID(int id, Users user, String status) {
		try {
			PreparedStatement stmt = conn.prepareStatement("update trform " + 
					"set status = '"+status+" by "+user.getPosition() +"'" + 
					" where formid = " + id + ";");
			LoggingUtil.debug("Before autocommit");
			conn.setAutoCommit(false);

			LoggingUtil.debug("Before execute");
			stmt.executeUpdate();
			LoggingUtil.debug("After execute");
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException ex)
		{
			try
			{
				LoggingUtil.error("SOMETHING BAD");
				conn.rollback();
			}
			catch(SQLException exc)
			{
				exc.printStackTrace();
			}
			ex.printStackTrace();
		}
		//System.out.println(getFormByFormid(id).getEID());
		if(getFormByFormid(id).getGrade()==null)
		{
			return 0;
		}
		else
		{
			return getFormByFormid(id).getEID();
		}
	}
	public void UpdateFormByFID(int id, Users user, String status, String Grade) {
		try {
			PreparedStatement stmt = conn.prepareStatement("update trform set grade = '"+Grade +"', status = '"+status+" by " +user.getPosition() +"' where formid = " + id);
			LoggingUtil.debug("Before autocommit");
			conn.setAutoCommit(false);

			LoggingUtil.debug("Before execute");
			stmt.executeUpdate();
			LoggingUtil.debug("After execute");
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(SQLException ex)
		{
			try
			{
				LoggingUtil.error("SOMETHING BAD");
				conn.rollback();
			}
			catch(SQLException exc)
			{
				exc.printStackTrace();
			}
			ex.printStackTrace();
		}
	}
}
