package com.KanjiApp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
public class JDBCConnection {
	
	   static String url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_933b8493771d8e4?useUnicode=yes&characterEncoding=UTF-8";
	   //static String url = "jdbc:mysql://localhost:3306/kanji_data?allowPublicKeyRetrieval=true&useSSL=false";
	    Connection conn;
	    Statement st;

	    public JDBCConnection() {

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            this.conn = DriverManager.getConnection(url, "b9caf2de664961", "c1009b25");
	            //this.conn = DriverManager.getConnection(url, "root", "453123453");

	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    public void putdata(String sql) throws Exception {
	        st = this.conn.createStatement();
	        st.executeUpdate(sql);
	    }

	    public ResultSet getdata(String sql) throws Exception {
	        Statement st = this.conn.createStatement();
	        return st.executeQuery(sql);
	    }

	    public void close() {
	        try {
	            this.conn.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    public static long compareTo(Date date1, Date date2) {
	        return date1.getTime() - date2.getTime();
	    }
}
