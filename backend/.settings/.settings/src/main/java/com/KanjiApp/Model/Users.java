package com.KanjiApp.Model;

public class Users {
	private int idUser;
	private String userName;
	private String accountName;
	private String pass;
	public Users(int idUser, String userName, String accountName, String pass) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.accountName = accountName;
		this.pass = pass;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
