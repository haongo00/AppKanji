package com.KanjiApp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnetion {
	public static Connection getJDBCConnection() {
		final String url = "jdbc:mysql://localhost:3306/kanji_data?useSSL=false";
	    final String user = "root";
		final String pass = "453123453";
		//final String url = "jdbc:mysql://remotemysql.com:3306/LWdq7j1okY?useSSL=false";
		//final String user = "LWdq7j1okY";
		//final String pass = "1ljRAFG9Yz";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, pass);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
