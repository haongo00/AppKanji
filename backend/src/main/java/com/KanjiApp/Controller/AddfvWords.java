package com.KanjiApp.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Kanjiwords;
import com.google.gson.Gson;

import Util.ResAndReq;
import Util.Util;
@WebServlet(urlPatterns= {"/addjvwords"} )
public class AddfvWords extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String str = Util.convertoS(req.getReader());
		Gson g = new Gson();
		ResAndReq rar = g.fromJson(str, ResAndReq.class);
		System.out.println(rar.getIdKanji()+" "+rar.getIdUser());
		String fwslist = DAO.fvwords(rar.getIdUser()); 
		System.out.println(fwslist);
		PrintWriter printWriter = resp.getWriter();
		if(Util.isSubstr(rar.getIdKanji(), fwslist)) {
			rar.setIdKanji(0);
			rar.setIdUser(0);
			rar.setResponse("already exist");
			printWriter.println(g.toJson(rar));
		}
		else {
			DAO.addFvw(rar.getIdUser(), rar.getIdKanji());
			rar.setResponse("successful");
			printWriter.println(g.toJson(rar));
		}
		
	}
}
