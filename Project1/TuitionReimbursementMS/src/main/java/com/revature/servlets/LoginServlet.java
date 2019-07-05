package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Users;
import com.revature.services.UserServiceImp;
import com.revature.services.UsersService;

public class LoginServlet extends HttpServlet
{
	private UsersService userService = new UserServiceImp();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession(false);
		if (sess != null && sess.getAttribute("user") != null) {
			Users user = (Users) sess.getAttribute("user");
			if(user.getPosition().equalsIgnoreCase("BenefitCoord"))
			{
				resp.sendRedirect("BenCoordHomePage.html");
			}
			else if(user.getPosition().equalsIgnoreCase("Supervisor"))
			{
				resp.sendRedirect("SuperHomePage.html");
			}
			else if(user.getPosition().equalsIgnoreCase("DeptHead"))
			{
				resp.sendRedirect("DeptHeadHomePage.html");
			}
			else if(user.getPosition().equalsIgnoreCase("Employee"))
			{
				resp.sendRedirect("EmpHomePage.html");
			}
		} else {
			resp.sendRedirect("Login.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String username = req.getParameter("username");
        String password = req.getParameter("password");
        Users user = userService.login(username, password);
        
        if(user == null)
        {
			resp.sendRedirect("Login.html");
		} else {

			HttpSession sess = req.getSession(true);
			sess.setAttribute("user", user);
			if(user.getPosition().equalsIgnoreCase("BenefitCoord"))
			{
				resp.sendRedirect("BenCoordHomePage.html");
			}
			else if(user.getPosition().equalsIgnoreCase("Supervisor"))
			{
				resp.sendRedirect("SuperHomePage.html");
			}
			else if(user.getPosition().equalsIgnoreCase("DeptHead"))
			{
				resp.sendRedirect("DeptHeadHomePage.html");
			}
			else if(user.getPosition().equalsIgnoreCase("Employee"))
			{
				resp.sendRedirect("EmpHomePage.html");
			}
		}
	}
}
