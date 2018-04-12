package tdt4140.gr1832.app.ui;

import java.util.ArrayList;
import java.util.List;

public class ListObject {
	
	public List<String> getDates() {
		return dates;
	}

	public List<Integer> getResults() {
		return results;
	}

	List<String> dates = new ArrayList<>();
	List<Integer> results = new ArrayList<>();
	
	public ListObject(List<String> dates, List<Integer> results) {
		this.dates = dates;
		this.results = results; 
	}
	
	
}
