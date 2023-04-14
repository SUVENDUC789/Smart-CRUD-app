package com.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Update")
public class Update extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
//		out.println("UpDate servlet...");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String id = request.getParameter("id");
		
		int sl = Integer.parseInt(id);
		
		try {
			DB.updateRow(sl, fname, lname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}

}
