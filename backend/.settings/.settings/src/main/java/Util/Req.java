package Util;

public class Req {
	private int idKanji;
	private String request;
	public Req(int idUser, String request) {
		super();
		this.idKanji = idKanji;
		this.request = request;
	}
	public int getIdKanji() {
		return idKanji;
	}
	public void setIdUser(int idKanji) {
		this.idKanji = idKanji;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
}
