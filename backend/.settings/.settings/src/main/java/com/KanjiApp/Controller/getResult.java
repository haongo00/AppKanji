package com.KanjiApp.Controller;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.DAO.DAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Util.DataResult;
import Util.Result;
import Util.Util;

@WebServlet(urlPatterns= {"/getresult"} )

public class getResult extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String answers = req.getParameter("choices");
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		// TODO Auto-generated method stub
		if(Util.questions.get(idUser)!=null) {
			boolean[] results = new boolean[10];
			int count_correction_answer = 0;
			for(int i=0; i<10; i++) {
				boolean check;
				String choice;
				String answer = Util.questions.get(idUser).get(i).getAnswer();
				switch(answers.charAt(i)) {
					case 'a':
						choice = Util.questions.get(idUser).get(i).getAnswerops().getA_option();
						check = answer.equals("A");
						results[i]=check;
						if(check==true) count_correction_answer++;
						break;
					case 'b':
						choice = Util.questions.get(idUser).get(i).getAnswerops().getB_option();
						check = answer.equals("B");
						results[i]=check;
						if(check==true) count_correction_answer++;
						break;
					case 'c':
					    choice =Util.questions.get(idUser).get(i).getAnswerops().getC_option();
					    check = answer.equals("C");
						results[i]=check;
						if(check==true) count_correction_answer++;
						break;
					case 'd':
						choice = Util.questions.get(idUser).get(i).getAnswerops().getD_option();
						check = answer.equals("D");
						results[i]=check;
						if(check==true) count_correction_answer++;
						break;
					default:
						results[i]=false;
						break;
				}
			}
			Result result = new Result(results, count_correction_answer); 
			ArrayList<Result> re = new ArrayList<Result>();
			re.add(result);
			DataResult data = new DataResult(re);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			PrintWriter printWriter = new PrintWriter(resp.getWriter());
			printWriter.println(g.toJson(data));
			Util.questions.remove(idUser);
		}
	}

}
