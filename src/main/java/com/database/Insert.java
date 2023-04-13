package com.database;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Insert")
public class Insert extends HttpServlet  {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fname = req.getParameter("first_name");
		
		String lname = req.getParameter("last_name");
		
		String gender = req.getParameter("gender");
		
		
		PrintWriter out = resp.getWriter();
		out.println("Suvendu Chowdhury");
		
		try {
			
			int c = DB.insertDB(fname, lname, gender);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
