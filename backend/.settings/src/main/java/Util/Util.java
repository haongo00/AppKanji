package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Question;
import com.google.gson.Gson;

public class Util {
	public static Map<Integer, ArrayList<Question>> questions = new HashMap<Integer, ArrayList<Question>>(); 

	 public static String addIdfw(int i, String s){
	        String str = Integer.toString(i);
	        if(s==null) return str;
	        return s+str+" ";
	 }
	 public static String delete(int substr, String str){
	        String [] list =str.split(" ");
	        String tmp = Integer.toString(substr);
	        String result = "";
	        for(int i = 0; i<list.length; i++){
	            if(!list[i].equals(tmp)) result = result+ list[i] + " ";
	        }
	        return result;
	    }
	 public static boolean isSubstr(int substr, String str){
		 if(str==null) return false;
	     String[] list = str.split(" ");
	     String tmp = Integer.toString(substr);
	     for(int i =0; i<list.length; i++){
	    	 if(tmp.equals(list[i])) return true;
	     }
	     return false;
	}
	public static String getlistfws(String list){
	    String str = "(";
	    String[] sp = list.split(" ");
	    for(int i =0; i<sp.length; i++){
	        if(i ==sp.length-1)  str = str +"'"+ sp[i] + "')";
	        else str = str+ "'" +sp[i] + "',";
	    }
	    return str;
	}
	
	public static String convertoS(BufferedReader reader) {
	     try {
	         StringBuilder sb = new StringBuilder();
	         String s;
	         while ((s = reader.readLine()) != null) {
	                sb.append(s);
	         }  
	         return sb.toString();
	     }catch(IOException e) {
	    	 e.printStackTrace();
	     }
	     return null;
	}
	public static boolean checkpass(String pass) {
		if(pass.length()<=7) return false;
		int[] check = new int[2];
		for(int i = 0; i<2; i++) {
			check[i] = 0;
		}
		for(int i =0; i<pass.length(); i++) {
			char character = pass.charAt(i);
			if ('a'<=character&&character<='z' || 'A'<=character&&character<='Z') check [0]++;
			else if (48<=character&&character<=57) check[1]++;
			else return false;
		}
		for(int i =0; i<2; i++) {
			if(check[i]==0) return false;
		}
		return true;
	}
	public static boolean checkaccountName(String accountName) {
		int[] check = new int[3];
		for(int i = 0; i<3; i++) {
			check[i] = 0;
		}
		for(int i =0; i<accountName.length(); i++) {
			char character = accountName.charAt(i);
			if ('a'<=character&&character<='z' || 'A'<=character&&character<='Z') check [0]++;
			else if (48<=character&&character<=57) check[1]++;
			else if (character =='_' || character =='.') check[2]++; 
			else return false;
		}
		for(int i =0; i<3; i++) {
			if(check[i]==0) return false;
		}
		return true;
	}
}
