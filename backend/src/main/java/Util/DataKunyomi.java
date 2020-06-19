package Util;

import java.util.ArrayList;
import java.util.List;

import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Kunyomi;
import com.KanjiApp.Model.Onyomi;

public class DataKunyomi {
	ArrayList<Kunyomi> data = new ArrayList<Kunyomi>();

	public DataKunyomi(List<Kunyomi> data) {
		this.data = (ArrayList<Kunyomi>) data;
	}
}
