package Util;

public class ResAndReq{
	private int idUser;
	private int idKanji;
	private String response;
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdKanji() {
		return idKanji;
	}
	public void setIdKanji(int idKanji) {
		this.idKanji = idKanji;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public ResAndReq(int idUser, int idKanji, String response) {
		super();
		this.idUser = idUser;
		this.idKanji = idKanji;
		this.response = response;
	}
	
	
}
