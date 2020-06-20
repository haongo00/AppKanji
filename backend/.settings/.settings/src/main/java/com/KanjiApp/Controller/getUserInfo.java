package com.KanjiApp.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Util.Res;
import Util.Util;
@WebServlet(urlPatterns= {"/getuserinfo"} )
public class getUserInfo extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	Gson g = new GsonBuilder().setPrettyPrinting().create();
	int idUser = Integer.parseInt(req.getParameter("idUser"));
	resp.setCharacterEncoding("UTF-8");
	resp.setContentType("application/json");
	PrintWriter printWriter = resp.getWriter();
	printWriter.println(g.toJson(DAO.getUser(idUser)));	
}
}
