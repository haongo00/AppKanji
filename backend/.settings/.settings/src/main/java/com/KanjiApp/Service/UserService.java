package com.KanjiApp.Service;

import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Kunyomi;
import com.KanjiApp.Model.Onyomi;

import Util.Util;

public class UserService {
	public static boolean checklogin(String accountName, String pass) {
		if (DAO.findlogin(accountName, pass)) return true;
		return false;
	}
	public static boolean accNameIsValid(String accountName) {
		if(Util.checkaccountName(accountName)) {
			return true;
		}
		return false;
	}
	public static boolean passIsValid(String pass) {
		if(Util.checkpass(pass)) return true;
		return false;
	}
	public static boolean accNameExist(String accountName) {
		if(DAO.checkAccountName(accountName)) return true;
		return false;
	}
}
