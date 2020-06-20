package com.KanjiApp.Model;

public class Kanjiwords {
	private int idKanji;
	private String kanji;
	private String kunyomi;
	private String onyomi;
	private String hanviet;
	private String set;
	private String strokes;
	private String meaning;
	private String jlpt;
	private boolean status;
	public Kanjiwords(int idKanji, String kanji, String kunyomi, String onyomi, String hanviet, String set,
			String strokes, String meaning, String jlpt) {
		super();
		this.idKanji = idKanji;
		this.kanji = kanji;
		this.kunyomi = kunyomi;
		this.onyomi = onyomi;
		this.hanviet = hanviet;
		this.set = set;
		this.strokes = strokes;
		this.meaning = meaning;
		this.jlpt = jlpt;
		this.status = false;
	}
	public int getIdKanji() {
		return idKanji;
	}
	public void setIdKanji(int idKanji) {
		this.idKanji = idKanji;
	}
	public String getKanji() {
		return kanji;
	}
	public void setKanji(String kanji) {
		this.kanji = kanji;
	}
	public String getKunyomi() {
		return kunyomi;
	}
	public void setKunyomi(String kunyomi) {
		this.kunyomi = kunyomi;
	}
	public String getOnyomi() {
		return onyomi;
	}
	public void setOnyomi(String onyomi) {
		this.onyomi = onyomi;
	}
	public String getHanviet() {
		return hanviet;
	}
	public void setHanviet(String hanviet) {
		this.hanviet = hanviet;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public String getStrokes() {
		return strokes;
	}
	public void setStrokes(String strokes) {
		this.strokes = strokes;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getJlpt() {
		return jlpt;
	}
	public void setJlpt(String jlpt) {
		this.jlpt = jlpt;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
