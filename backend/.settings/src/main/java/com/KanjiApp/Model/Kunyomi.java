package com.KanjiApp.Model;

public class Kunyomi {
	private int idKanji;
	private String vocab;
	private String yomikata;
	private String hanviet;
	private String meaning;
	public Kunyomi() {};
	public Kunyomi( int idKanji, String vocab, String yomikata, String hanviet, String meaning) {
		super();
		this.idKanji = idKanji;
		this.vocab = vocab;
		this.yomikata = yomikata;
		this.hanviet = hanviet;
		this.meaning = meaning;
	}
	public int getIdKanji() {
		return idKanji;
	}
	public void setIdKanji(int idKanji) {
		this.idKanji = idKanji;
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
	public String getHanviet() {
		return hanviet;
	}
	public void setHanviet(String hanviet) {
		this.hanviet = hanviet;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
}
