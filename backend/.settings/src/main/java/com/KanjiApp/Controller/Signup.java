package com.KanjiApp.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Users;
import com.KanjiApp.Service.UserService;
import com.google.gson.Gson;

import Util.Res;
import Util.Util;
@WebServlet(urlPatterns= {"/signup"} )
public class Signup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String s = Util.convertoS(req.getReader());
		//resp.setCharacterEncoding("UTF-8");
		//resp.setContentType("application/json");
		Gson gson = new Gson();
		Users u = gson.fromJson(s, Users.class);
		System.out.println(u.getUserName());
		System.out.println(u.getAccountName());
		System.out.print(u.getPass());
		PrintWriter printWriter = resp.getWriter();
		if(UserService.accNameIsValid(u.getAccountName())) {
			if(UserService.accNameExist(u.getAccountName())) {
				Res rp = new Res(0, "This account name has already existed ");
				printWriter.println(gson.toJson(rp));
			} else {
				if (!UserService.passIsValid(u.getPass())) {
					Res rp = new Res(0, "Password is invalid ");
					printWriter.println(gson.toJson(rp));
				} else {
					DAO.InserUser(u.getUserName(), u.getAccountName(), u.getPass());
					DAO.findlogin(u.getAccountName(), u.getPass());
					Res rp = new Res(DAO.iduser, "Registration successful");
					printWriter.println(gson.toJson(rp));
				}
			}
		}
		else {
			Res rp = new Res(0, "Account name is invalid ");
			printWriter.println(gson.toJson(rp));
		}
		
		// PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);
		//PrintWriter printWriter = resp.getWriter();
	}
}
