package Util;

public class Ans{
	private String vocab;
	private String yomikata;
	private String meaning;
	public Ans(String vocab, String yomikata, String meaning) {
		super();
		this.vocab = vocab;
		this.yomikata = yomikata;
		this.meaning = meaning;
	}
	public String getVocab() {
		return vocab;
	}
	public void setVocab(String vocab) {
		this.vocab = vocab;
	}
	public String getYomikata() {
		return yomikata;
	}
	public void setYomikata(String yomikata) {
		this.yomikata = yomikata;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
}
