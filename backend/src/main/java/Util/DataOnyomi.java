package Util;

import java.util.ArrayList;
import java.util.List;

import com.KanjiApp.Model.Kanjiwords;
import com.KanjiApp.Model.Kunyomi;
import com.KanjiApp.Model.Onyomi;

public class DataOnyomi {
	ArrayList<Onyomi> data = new ArrayList<Onyomi>();
	
	public DataOnyomi(List<Onyomi> data) {
		this.data = (ArrayList<Onyomi>) data;
	}

}
