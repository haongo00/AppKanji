package Util;

import java.util.ArrayList;
import java.util.List;

import com.KanjiApp.Model.Onyomi;
import com.KanjiApp.Model.Question;

public class DataResult {
    private ArrayList<Result> data = new ArrayList<Result>();
	public DataResult(List<Result> data) {
		this.data = (ArrayList<Result>) data;
	}
}
