package Util;

import java.util.ArrayList;
import java.util.Random;

import edu.emory.mathcs.backport.java.util.Collections;

public class AnswerOptions {
	private String A_option;
	private String B_option;
	private String C_option;
	private String D_option;
	public AnswerOptions(String a_option, String b_option, String c_option, String d_option) {
		super();
		A_option = a_option;
		B_option = b_option;
		C_option = c_option;
		D_option = d_option;
	}
	public void shuffle() {
		Random generator = new Random();
		ArrayList<String> str_list = new ArrayList<String>();
		str_list.add(this.A_option);
		str_list.add(this.B_option);
		str_list.add(this.C_option);
		str_list.add(this.D_option);
		Collections.shuffle(str_list);
		this.A_option = str_list.get(0);
		this.B_option = str_list.get(1);
		this.C_option = str_list.get(2);
		this.D_option = str_list.get(3);
	}
	public String getA_option() {
		return A_option;
	}
	public void setA_option(String a_option) {
		A_option = a_option;
	}
	public String getB_option() {
		return B_option;
	}
	public void setB_option(String b_option) {
		B_option = b_option;
	}
	public String getC_option() {
		return C_option;
	}
	public void setC_option(String c_option) {
		C_option = c_option;
	}
	public String getD_option() {
		return D_option;
	}
	public void setD_option(String d_option) {
		D_option = d_option;
	}
	

}
	