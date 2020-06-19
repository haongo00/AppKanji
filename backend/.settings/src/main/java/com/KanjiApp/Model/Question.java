package com.KanjiApp.Model;

import Util.AnswerOptions;

public class Question {
	private String question;
	private String answer;
	private AnswerOptions answerops;
	
	public Question(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	public Question(String question, String answer, AnswerOptions answerops) {
		super();
		this.question = question;
		this.answer = answer;
		this.answerops = answerops;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public AnswerOptions getAnswerops() {
		return answerops;
	}
	public void setAnswerops(AnswerOptions answerops) {
		this.answerops = answerops;
	}
	
	
}
