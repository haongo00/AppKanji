package com.KanjiApp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Kunyomi;
import com.KanjiApp.Model.Onyomi;
import com.KanjiApp.Model.Users;

import Util.Ans;
public class DAO {
	public static List<Kanjiwords> kanjis = new ArrayList<Kanjiwords>(); 
	public static List<Kunyomi> kunyomiex = new ArrayList<Kunyomi>();
	public static List<Onyomi> onyomiex = new ArrayList<Onyomi>();
	public static int iduser;
	public static List<Ans> Ans_list = new ArrayList<Ans>();
	public static void getAnsKunyomi(String key) {
		 JDBCConnection conn = new JDBCConnection();
		 String sql = "select vocab, meaning, yomikata from kunyomi_tb Where idKanji In" + key + "order by rand()";
			try {
				ResultSet rs = conn.getdata(sql);
				while(rs.next()) {
					Ans ans = new Ans(rs.getString("vocab"), rs.getString("yomikata"), rs.getString("meaning"));
					Ans_list.add(ans);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				conn.close();
			}
	}
	public static void getAnsOnyomi(String key) {
		 JDBCConnection conn = new JDBCConnection();
		 String sql = "select vocab, meaning, yomikata from onyomi_tb Where idKanji In" + key + "order by rand()";
			try {
				ResultSet rs = conn.getdata(sql);
				while(rs.next()) {
					Ans ans = new Ans(rs.getString("vocab"), rs.getString("yomikata"), rs.getString("meaning"));
					Ans_list.add(ans);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				conn.close();
			}
	}
	public static void addFvw(int idUser, int idKanji) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "INsert into favoriteWords(idUser, idKanji, status) values(" +idUser + ","+ idKanji +",'liked')";
		try {
			conn.putdata(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
	}
	public static void deleteFvw(int idUser, int idKanji) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "DELETE FROM favoriteWords WHERE idUser="+idUser+" AND idKanji=" +idKanji;;
		try {
			conn.putdata(sql);;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		
	}
	public static String getStatus(int idUser, int idKanji) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT status FROM favoriteWords WHERE idUser = " + idUser+" AND idKanji="+idKanji;
		try {
			ResultSet rs = conn.getdata(sql);
			if(rs.next())return rs.getString("status");
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return null;
	}
	public static Users getUser(int idUser) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT * FROM users WHERE idUser = "+idUser;
		try {
			ResultSet rs = conn.getdata(sql);
			rs.next();
			return new Users(rs.getInt("idUser"), rs.getString("userName"), 
			rs.getString("accountName"), rs.getString("pass"));

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return null;
	}
	public static String fvwords(int idUser) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT idKanji FROM favoriteWords WHERE idUser = " + idUser;
		String list = null;
		try {
			ResultSet rs = conn.getdata(sql);
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
		finally {
			conn.close();
		}
		return null;
		
	}
	public static void getFvws(String list) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT * FROM kanjiwords WHERE idKanji IN "+list;
		try {
			ResultSet rs = conn.getdata(sql);
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
			finally {
				conn.close();
			}
	}
	public static void getKanjis(String key) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT * FROM kanjiwords WHERE hanviet LIKE "+"'"+key+"%'"  +" OR kanji="+"'"+key+"'" ;
		try {
			ResultSet rs = conn.getdata(sql);
			while(rs.next()) {
					Kanjiwords kanji = new Kanjiwords(rs.getInt("idKanji"), rs.getString("kanji"), 
						      rs.getString("kunyomi"),rs.getString("onyomi"),
						     rs.getString("hanviet"), rs.getString("set"), rs.getString("strokes"),
						      rs.getString("meaning"), rs.getString("jlpt"));
					kanjis.add(kanji);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		
	}
	public static boolean findlogin(String accountName, String pass) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT * FROM users WHERE accountName = '"+ accountName + "' AND pass='"+ pass+"'";
		try {
			String accName=" ";
			String pas =" ";
			ResultSet rs =conn.getdata(sql);
			while(rs.next()) {
				iduser = rs.getInt("idUser");
				accName = rs.getString("accountName");
				pas = rs.getString("pass");
			} 
			if(accName!=" "&& pass!=" ") return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			conn.close();
		}
		
		return false;
	}
	public static void InserUser(String userName, String accountName, String pass) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "INsert into users(userName, accountName, pass) values('" +userName+"','" + accountName+"','" +pass+"')";
		try {
			conn.putdata(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
    }
	public static boolean checkAccountName(String accountName) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT accountName FROM users WHERE accountName ='" + accountName +"'";
		try {
			String accName=" ";
			ResultSet rs = conn.getdata(sql);
			while(rs.next()) {
				accName = rs.getString("accountName");
			} 
			if(accName!=" ") return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			conn.close();
		}
		
		return false;

	}
	public static void getkunyomi(int key) {
		JDBCConnection conn = new JDBCConnection();
		 String sql = "SELECT * FROM kunyomi_tb WHERE idKanji = " + key;
		 Kunyomi kun = new Kunyomi();
			try {
				ResultSet rs = conn.getdata(sql);
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
			finally {
				conn.close();
			}
	}
	public static Onyomi getonyomi(int key) {
		JDBCConnection conn = new JDBCConnection();
		String sql = "SELECT * FROM onyomi_tb WHERE idKanji ="+ key;
		 Onyomi on = new Onyomi();
			try {
				ResultSet rs = conn.getdata(sql);
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
			finally {
				conn.close();
			}
			return on;
	}
}
