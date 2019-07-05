package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Users;

public class HomePageServlet  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession sess = req.getSession(false);
		ObjectMapper om = new ObjectMapper();
		Users user = (Users)sess.getAttribute("user");
		String userinfo = om.writeValueAsString(user);
		System.out.println(userinfo);
		resp.getWriter().write(userinfo);
	}
}
