package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class DB {
	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost:3306/test";
	public static String USER = "root";
	public static String PASSWD = "";

	public static Connection con;

	public static Connection connectDB() throws Exception {
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, USER, PASSWD);
		return con;
	}

//	String sql ="INSERT INTO `students` (`sl`, `first_name`, `last_name`, `datetime`, `gender`) VALUES (NULL, 'suvendu', 'chowdhury', current_timestamp(), 'male');";

	public static int insertDB(String fname, String lname, String gender) throws Exception {

		String sql = "INSERT INTO `students` (first_name,last_name,gender) VALUES (?,?,?)";
		Connection con = connectDB();
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, fname);
		psmt.setString(2, lname);
		psmt.setString(3, gender);

		int count = psmt.executeUpdate();

		return count;
	}

	public static void deleteRow(int id) throws Exception {

		try {
			Statement smt = DB.connectDB().createStatement();
			int count = smt.executeUpdate("DELETE FROM students WHERE sl =" + id);

			if (count == 1)
				System.out.println(count + " row deleted");
			else
				System.out.println("No row deleted");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}