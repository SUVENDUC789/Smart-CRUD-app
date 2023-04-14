package com.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Search")
public class Search extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String search = request.getParameter("search");
		

		String sql = "SELECT sl,first_name,last_name,gender,datetime FROM students WHERE first_name LIKE \"%"+search+"%\" or last_name LIKE \"%"+search+"%\" OR gender LIKE \"%"+search+"%\";";
		try {
			Statement smt = DB.connectDB().createStatement();
			ResultSet rs = smt.executeQuery(sql);
			int i = 0;

			out.println("<h1 class=\"text-center alert-info\">Student Details</h1>");

			out.println("    <table class=\"table table-striped table-hover\">\r\n" + "        <tr>\r\n"
					+ "            <th>SL</th>\r\n" + "            <th>First Name</th>\r\n"
					+ "            <th>Last Name</th>\r\n" + "            <th>Gender</th>\r\n"
					+ "            <th>Date of Admission</th>\r\n" + "        "
					+ "            <th>Delete</th>\r\n" + "        "
					+"<th>Update</th>"
					+ "</tr>");

			while (rs.next()) {
				i++;
				String sl = rs.getString(1);
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String gender = rs.getString(4);
				String dob = rs.getString(5);

				out.println("\r\n" + "        <tr>\r\n" + "            <td>" + sl + "</td>\r\n" + "            <td>"
						+ fname + "</td>\r\n" + "            <td>" + lname + "</td>\r\n" + "            <td>" + gender
						+ "</td>\r\n" + "            <td>" + dob + "</td>\r\n" + "  <td><button class='delete-btn' data-id='"+sl+"'>Delete</button> </td> " + "<td><button class='update-btn' data-id='"+sl+"'>Update</button> </td>"+ "      </tr>");
			}

			out.println("</table>");

			if (i == 0) {
				out.println("<h1>No Data found.</h1>");
			}

		} catch (Exception e) {

			out.println("<h1>No Data Found</h1>");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
