package com.KanjiApp.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.DAO.DAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Util.DataKanji;
import Util.Req;
import Util.Res;
import Util.Util;

@WebServlet(urlPatterns= {"/getfvwords"} )
public class getFvWords extends HttpServlet {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	resp.setCharacterEncoding("UTF-8");
	resp.setContentType("application/json");
	Gson g = new GsonBuilder().setPrettyPrinting().create();
	int idUser = Integer.parseInt(req.getParameter("idUser"));
	String fvwslist = DAO.fvwords(idUser);
	//System.out.println(Util.getlistfws(fvwslist));
	System.out.println(fvwslist);
	PrintWriter printWriter = resp.getWriter();
	if(fvwslist==null||fvwslist.equals("")) {
		Res rp = new Res(0, "Can not find");
		printWriter.println(g.toJson(rp));
	}
	else {
		DAO.getFvws(Util.getlistfws(fvwslist));

	 		for(int i =0; i<DAO.kanjis.size(); i++) {
	 			String check = DAO.getStatus(idUser, DAO.kanjis.get(i).getIdKanji());
	 			System.out.println(check);
	 			if(check!=null&&check.equals("liked")) {
	 				System.out.println("yes");
	 				DAO.kanjis.get(i).setStatus(true);
	 			}
	 		}
		DataKanji data = new DataKanji(DAO.kanjis);
		printWriter.println(g.toJson(data));
	}
	DAO.kanjis.clear();
}
}
