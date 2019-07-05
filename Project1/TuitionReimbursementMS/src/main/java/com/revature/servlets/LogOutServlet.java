package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession sess = req.getSession(false);
		System.out.println("insidedopost");
		if(sess == null)
		{
			resp.sendRedirect("Login.html");
		}
		else {
			sess.invalidate();
			resp.sendRedirect("Login.html");
		}
	}

}
