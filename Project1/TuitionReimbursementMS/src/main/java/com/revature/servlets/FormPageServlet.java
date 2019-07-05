package com.revature.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Employee;
import com.revature.pojos.TRForm;
import com.revature.pojos.Users;
import com.revature.services.TRFormService;
import com.revature.services.TRFormServiceImp;

public class FormPageServlet extends HttpServlet
{
	TRFormServiceImp TRFservice = new TRFormServiceImp();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.sendRedirect("FormPage.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession sess = req.getSession(false);
		Users e = (Users) sess.getAttribute("user");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		Date dates = null;
		try {
			dates = formatter.parse(req.getParameter("EDate"));
		}catch(ParseException ex)
		{
			ex.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(dates.getTime());
		TRFservice.submit(new TRForm(req.getParameter("Ename"), req.getParameter("Etime"), req.getParameter("EType"), date, Integer.parseInt(req.getParameter("Ecost")), req.getParameter("EGF"), req.getParameter("PGrade"), req.getParameter("E-Description")), e);
		e.setPendingbalance(TRFservice.getpending()+e.getPendingbalance());
		switch(e.getPosition())
		{
		case "BenefitCoord":
			resp.sendRedirect("BenCoordHomePage.html");
			break;
		case "DeptHead":
			resp.sendRedirect("DeptHeadHomePage.html");
			break;
		case "Supervisor":
			resp.sendRedirect("SuperHomePage.html");
			break;
		case "Employee":
			resp.sendRedirect("EmpHomePage.html");
			break;
		}
	}
}
