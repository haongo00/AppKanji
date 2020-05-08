package com.KanjiApp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Kunyomi;
import com.KanjiApp.Model.Onyomi;
import com.KanjiApp.Model.Users;
public class DAO {
	public static List<Kanjiwords> kanjis = new ArrayList<Kanjiwords>(); 
	public static List<Kunyomi> kunyomiex = new ArrayList<Kunyomi>();
	public static List<Onyomi> onyomiex = new ArrayList<Onyomi>();
	public static int iduser;
	public static void addFvw(int idUser, int idKanji) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "INsert into favoriteWords(idUser, idKanji, status) values(?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUser);
			ps.setInt(2, idKanji);
			ps.setString(3, "liked");
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void deleteFvw(int idUser, int idKanji) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "DELETE FROM favoriteWords WHERE idUser=? AND idKanji=?";
		String s = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUser);
			ps.setInt(2, idKanji);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static String getStatus(int idUser, int idKanji) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "SELECT status FROM favoriteWords WHERE idUser = ? AND idKanji=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUser);
			ps.setInt(2, idKanji);
			ResultSet rs = ps.executeQuery();
			if(rs.next())return rs.getString("status");
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Users getUser(int idUser) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "SELECT * FROM users WHERE idUser = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Users(rs.getInt("idUser"), rs.getString("userName"), 
			rs.getString("accountName"), rs.getString("pass"));

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String fvwords(int idUser) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "SELECT idKanji FROM favoriteWords WHERE idUser = ?";
		String list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			rs.next();
			list=rs.getString("idKanji") + " ";
			while(rs.next()) {
				list = list+ String.valueOf(rs.getInt("idKanji"))+" ";
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static void getFvws(String list) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "SELECT * FROM kanjiwords WHERE idKanji IN "+list;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			Kanjiwords kanji = new Kanjiwords(rs.getInt("idKanji"), rs.getString("kanji"), 
							      rs.getString("kunyomi"),rs.getString("onyomi"),
							      rs.getString("hanviet"), rs.getString("set"), rs.getString("strokes"),
							      rs.getString("meaning"), rs.getString("jlpt"));
			kanjis.add(kanji);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	public static void getKanjis(String key){
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "SELECT * FROM kanjiwords WHERE hanviet LIKE ? OR kanji=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, key+"%");
			ps.setString(2, key);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Kanjiwords kanji = new Kanjiwords(rs.getInt("idKanji"), rs.getString("kanji"), 
					      rs.getString("kunyomi"),rs.getString("onyomi"),
					      rs.getString("hanviet"), rs.getString("set"), rs.getString("strokes"),
					      rs.getString("meaning"), rs.getString("jlpt"));
				kanjis.add(kanji);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean findlogin(String accountName, String pass) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "SELECT * FROM users WHERE accountName =? and pass=?"+ " ";
		try {
			String accName=" ";
			String pas =" ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				iduser = rs.getInt("idUser");
				accName = rs.getString("accountName");
				pas = rs.getString("pass");
			} 
			if(accName!=" "&& pass!=" ") return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	public static void InserUser(String userName, String accountName, String pass) {
	    Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "INsert into users(userName, accountName, pass) values(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, accountName);
			ps.setString(3, pass);
		    ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
    }
	public static boolean checkAccountName(String accountName) {
		Connection conn = JDBCConnetion.getJDBCConnection();
		String sql = "SELECT accountName FROM users WHERE accountName =?";
		try {
			String accName=" ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accName = rs.getString("accountName");
			} 
			if(accName!=" ") return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;

	}
	public static void getkunyomi(int key) {
		 Connection conn = JDBCConnetion.getJDBCConnection();
		 String sql = "SELECT * FROM kunyomi_tb WHERE idKanji = ? ";
		 Kunyomi kun = new Kunyomi();
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, key);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					kun = new Kunyomi(rs.getInt("idKanji"), rs.getString("vocab"), 
					           rs.getString("yomikata"),rs.getString("hanviet"),
					           rs.getString("meaning"));
					DAO.kunyomiex.add(kun);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	public static Onyomi getonyomi(int key) {
		 Connection conn = JDBCConnetion.getJDBCConnection();
		 String sql = "SELECT * FROM onyomi_tb WHERE idKanji = ?";
		 Onyomi on = new Onyomi();
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, key);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					on = new Onyomi(rs.getInt("idKanji"), rs.getString("vocab"), 
					           rs.getString("yomikata"),rs.getString("hanviet"),
					           rs.getString("meaning"));
					DAO.onyomiex.add(on);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return on;
	}
}
