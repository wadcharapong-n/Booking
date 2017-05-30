package com.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking", "booking", "password");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static Date getCurrentTime() throws SQLException{
		Date result = null;
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("select now() as time");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			// get data from column
			result = rs.getDate("time");
		}
		rs.close();
		ps.close();
		return result;
	}

}
