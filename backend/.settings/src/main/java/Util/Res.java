package Util;

public class Res {
	private int idUser;
	private String response;
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Res(int idUser, String response) {
		super();
		this.idUser = idUser;
		this.response = response;
	}
	
}
