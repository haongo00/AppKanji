package Util;

import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.Gson;

public class Util {
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
		for(int i =0; i<pass.length(); i++) {
			char character = pass.charAt(i);
			if(!(('a'<=character&&character<='z')||(48<=character&&character<=57))) return false; 
		}
		return true;
	}
	public static boolean checkaccountName(String accountName) {
		for(int i =0; i<accountName.length(); i++) {
			char character = accountName.charAt(i);
			if(!(('a'<=character&&character<='z') || ('A'<=character&&character<='Z')||
			(48<=character&&character<=57)|| (character =='_') || (character =='.'))) return false; 
		}
		return true;
	}
}
