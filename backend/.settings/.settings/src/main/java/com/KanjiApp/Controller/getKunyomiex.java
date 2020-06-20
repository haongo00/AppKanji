package com.KanjiApp.Controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Kunyomi;
import com.google.gson.Gson;

import Util.DataKunyomi;
import Util.Req;
import Util.Util;
@WebServlet(urlPatterns= {"/getkunyomiex"} )
public class getKunyomiex extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);
		Gson g = new Gson();
		int id = Integer.parseInt(req.getParameter("idKanji"));
		int key;
		if(id != 0) {
			key = id;
			DAO.getkunyomi(key);
			DataKunyomi data = new DataKunyomi(DAO.kunyomiex);
			printWriter.println(g.toJson(data));
			
		}
		else {
			Req rq = new Req(0, "can not find");
			printWriter.print(g.toJson(rq));
		}
		DAO.kunyomiex.clear();
	}
}	