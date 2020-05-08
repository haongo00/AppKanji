package com.KanjiApp.Controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.Model.*;

import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Users;
import com.KanjiApp.Service.UserService;
import com.google.gson.Gson;

import Util.Req;
import Util.Res;
import Util.Util;
@WebServlet(urlPatterns= {"/login"} )
public class Login extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonString = Util.convertoS(req.getReader());
		Gson g = new Gson();
		String accountName = req.getParameter("accountName");
		String pass = req.getParameter("pass");
		PrintWriter printWriter = resp.getWriter();
		if(accountName==null||pass==null) {
			Res rp = new Res(0, "Account name or password is not ivalid");
			printWriter.println(g.toJson(rp));
		}
		else {
			if(UserService.checklogin(accountName,pass)) {
				Res rp = new Res(DAO.iduser, "Login succesful");
				printWriter.println(g.toJson(rp));
				
			}
			else {
				Res rp = new Res(0, "Account name or password is not ivalid");
				printWriter.println(g.toJson(rp));
			}
		}
			
	}
}
