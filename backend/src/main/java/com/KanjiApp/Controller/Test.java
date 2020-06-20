package com.KanjiApp.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KanjiApp.DAO.DAO;
import com.KanjiApp.Model.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Util.Ans;
import Util.AnswerOptions;
import Util.DataTests;
import Util.Res;
import Util.Util;
import edu.emory.mathcs.backport.java.util.Collections;
@WebServlet(urlPatterns= {"/test"} )
public class Test extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		String type_test = req.getParameter("type_test");
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		ArrayList<Question> questions = new ArrayList<Question>();
		String fvwlists = DAO.fvwords(idUser);
		String[] str = fvwlists.split(" ");
		System.out.println(fvwlists+" "+ str.length);
		if(str.length<10) {
			Res res = new Res(0, "Phải có ít nhất 10 từ yêu thích mới có thể test");
			printWriter.println(g.toJson(res));
		}
		else {
			String key = Util.getlistfws(fvwlists);
			DAO.getAnsKunyomi(key);
			DAO.getAnsOnyomi(key);
			Collections.shuffle(DAO.Ans_list);
			Random generator = new Random();
			String[] answers = new String[10];
			if(type_test.equals("meaning")) {
				for(int i=0; i<10; i++) {
					Question q = new Question(DAO.Ans_list.get(i).getVocab(), DAO.Ans_list.get(i).getMeaning().trim());
					int a= 0, b=0, c=0;
				    while((DAO.Ans_list.get(a).getMeaning().trim()).equals(DAO.Ans_list.get(b).getMeaning().trim())
					    	 ||(DAO.Ans_list.get(a).getMeaning().trim().equals(DAO.Ans_list.get(c).getMeaning().trim()))
					    	 ||(DAO.Ans_list.get(b).getMeaning().trim().equals(DAO.Ans_list.get(c).getMeaning().trim()))
					    	 ||(DAO.Ans_list.get(a).getMeaning().trim().equals(DAO.Ans_list.get(i).getMeaning().trim()))
					    	 ||(DAO.Ans_list.get(b).getMeaning().trim().equals(DAO.Ans_list.get(i).getMeaning().trim()))
					    	 ||(DAO.Ans_list.get(c).getMeaning().trim().equals(DAO.Ans_list.get(i).getMeaning().trim()))){
				            a = generator.nextInt(DAO.Ans_list.size());
				            b = generator.nextInt(DAO.Ans_list.size());
				            c = generator.nextInt(DAO.Ans_list.size());
				    }
				    AnswerOptions ans_ops = new AnswerOptions(DAO.Ans_list.get(i).getMeaning().trim(), DAO.Ans_list.get(a).getMeaning().trim(),
				    		                 DAO.Ans_list.get(b).getMeaning().trim(), DAO.Ans_list.get(c).getMeaning().trim());
				    ans_ops.shuffle();
				    q.setAnswerops(ans_ops);
				    if(q.getAnswer().equals(ans_ops.getA_option())) q.setAnswer("A");
				    else if(q.getAnswer().equals(ans_ops.getB_option())) q.setAnswer("B");
				    else if(q.getAnswer().equals(ans_ops.getC_option())) q.setAnswer("C");
				    else q.setAnswer("D");
				    questions.add(q);
				}
			}
			else if(type_test.equals("yomikata")) {
				for(int i=0; i<10; i++) {
					Question q = new Question(DAO.Ans_list.get(i).getVocab(), DAO.Ans_list.get(i).getYomikata().trim());
					int a= 0, b=0, c=0;
				    while((DAO.Ans_list.get(a).getYomikata().trim()).equals(DAO.Ans_list.get(b).getYomikata().trim())
				    	 ||(DAO.Ans_list.get(a).getYomikata().trim().equals(DAO.Ans_list.get(c).getYomikata().trim()))
				    	 ||(DAO.Ans_list.get(b).getYomikata().trim().equals(DAO.Ans_list.get(c).getYomikata().trim()))
				    	 ||(DAO.Ans_list.get(a).getYomikata().trim().equals(DAO.Ans_list.get(i).getYomikata().trim()))
				    	 ||(DAO.Ans_list.get(b).getYomikata().trim().equals(DAO.Ans_list.get(i).getYomikata().trim()))
				    	 ||(DAO.Ans_list.get(c).getYomikata().trim().equals(DAO.Ans_list.get(i).getYomikata().trim()))){
				            a = generator.nextInt(DAO.Ans_list.size());
				            b = generator.nextInt(DAO.Ans_list.size());
				            c = generator.nextInt(DAO.Ans_list.size());
				    }
				    AnswerOptions ans_ops = new AnswerOptions(DAO.Ans_list.get(i).getYomikata().trim(), DAO.Ans_list.get(a).getYomikata().trim(),
				    		                 DAO.Ans_list.get(b).getYomikata().trim(), DAO.Ans_list.get(c).getYomikata().trim());
				    ans_ops.shuffle();
				    q.setAnswerops(ans_ops);
				    if(q.getAnswer().equals(ans_ops.getA_option())) q.setAnswer("A");
				    else if(q.getAnswer().equals(ans_ops.getB_option())) q.setAnswer("B");
				    else if(q.getAnswer().equals(ans_ops.getC_option())) q.setAnswer("C");
				    else q.setAnswer("D");
				    questions.add(q);
				}
			}
			DataTests data_tests = new DataTests(questions);
			printWriter.println(g.toJson(data_tests));
		}
		DAO.Ans_list.clear();
		Util.questions.put(idUser, questions);
		
	}

}
