package com.KanjiApp.Controller;

import java.awt.List;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import Util.DataKanji;
import Util.Req;
import Util.Res;
import Util.SearchRq;
import Util.Util;
@WebServlet(urlPatterns= {"/search"} )
public class Search extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String s = Util.convertoS(req.getReader());
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);
		Gson g = new GsonBuilder().setPrettyPrinting().create();;
		String key = g.fromJson(s, SearchRq.class).getKey();
		int idUser = g.fromJson(s, SearchRq.class).getIdUser();
	    
		DAO.getKanjis(key);
		
	    if(key!=null) {
	 	    if(key.equals("")) {
	 	    	Req rq = new Req(0, "space");
	 	    	printWriter.println(g.toJson(rq));
	 	    	System.out.println("space");
	 	    }
	 		else {
	 	 		if(!DAO.kanjis.isEmpty()) {
	 	 			for(int i =0; i<DAO.kanjis.size(); i++) {
	 	 				String check = DAO.getStatus(idUser, DAO.kanjis.get(i).getIdKanji());
	 	 				if(check!=null&&check.equals("liked")) {
	 	 					DAO.kanjis.get(i).setStatus(true);
	 	 				}
	 	 			}
	 	 			DataKanji data2 = new DataKanji(DAO.kanjis);
		 	 		printWriter.println(g.toJson(data2));
	 	 		}
	 	 		else {
		 	 		Req rq = new Req(0, "khong tim thay");
		 	    	printWriter.println(g.toJson(rq));
		 	    	System.out.println("khong tim thay");
	 	 		}
	 		}
	 	}
	 	else {
	 		Req rq = new Req(0, "null");
 	    	printWriter.println(g.toJson(rq));
 	    	
	 	}
		DAO.kanjis.clear();
    }	 
}