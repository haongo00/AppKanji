package Util;

import java.util.ArrayList;

public class Result {
	private boolean[] results = new boolean[10];
	private int correct_answers;
	public Result(boolean[] results, int correct_answers) {
		super();
		this.results = results;
		this.correct_answers = correct_answers;
	}
	public boolean[] getResults() {
		return results;
	}
	public void setResults(boolean[] results) {
		this.results = results;
	}
	public int getCorrect_answers() {
		return correct_answers;
	}
	public void setCorrect_answers(int correct_answers) {
		this.correct_answers = correct_answers;
	}
	
}
