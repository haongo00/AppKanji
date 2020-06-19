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

import Util.ResAndReq;
import Util.Util;

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson g = new Gson();
		resp.setContentType("application/json");
		ResAndReq rar = g.fromJson(Util.convertoS(req.getReader()),ResAndReq.class);
		//String fwslist = DAO.fvwords(rar.getIdUser());
		DAO.deleteFvw(rar.getIdUser(), rar.getIdKanji());
		rar.setResponse("successful");
		PrintWriter printWriter = resp.getWriter();
		printWriter.println(g.toJson(rar));
	}

}
