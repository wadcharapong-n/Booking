package com.booking.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			conn = DriverManager.getConnection("jdbc:mysql://hostname:port/dbname", "username", "password");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

}
