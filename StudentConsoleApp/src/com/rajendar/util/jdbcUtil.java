package com.rajendar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcUtil {
	
	private static Connection conn;
	
	public static Connection getJdbcConnection() {
		
		String url = "jdbc:mysql://localhost:3306/spring";
		String username ="root";
		String password="root";
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
