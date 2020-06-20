package Util;

import java.util.ArrayList;
import java.util.List;

import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Kunyomi;
import com.KanjiApp.Model.Onyomi;

public class DataKanji {
	ArrayList<Kanjiwords> data = new ArrayList<Kanjiwords>();
	public DataKanji(List<Kanjiwords> data) {
		this.data = (ArrayList<Kanjiwords>) data;
	}
}
