package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.TRForm;
import com.revature.pojos.Users;
import com.revature.services.TRFormService;
import com.revature.services.TRFormServiceImp;

public class ApprovingFormPageServlet extends HttpServlet{
	TRFormServiceImp TRFormser = new TRFormServiceImp();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			ObjectMapper om = new ObjectMapper();
			List<TRForm> trf = TRFormser.viewAllForms();
			String trfliststring = om.writeValueAsString(trf);
			System.out.println(trfliststring);
			resp.getWriter().write(trfliststring);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession(false);
		if(sess == null || sess.getAttribute("user") == null)
		{
			resp.sendRedirect("Login.html");
		}
		else
		{
			Users user = (Users) sess.getAttribute("user");
			TRFormser.ApproveForm(Integer.parseInt(req.getParameter("FormID")), user, "Approved");
			switch(user.getPosition())
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

}
