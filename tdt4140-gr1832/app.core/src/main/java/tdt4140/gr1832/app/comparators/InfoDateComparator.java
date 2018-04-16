package tdt4140.gr1832.app.comparators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class InfoDateComparator implements Comparator<String> {
	Map<String, String> months = new HashMap<>();
	
	String pattern1 = "yyyy-MM-dd";
	DateTimeFormatter dateFormatter;

	public InfoDateComparator() {
		months.put("Jan", "01");
		months.put("Feb", "02");
		months.put("Mar", "03");
		months.put("Apr", "04");
		months.put("Mai", "05");
		months.put("Jun", "06");
		months.put("Jul", "07");
		months.put("Aug", "08");
		months.put("Sep", "09");
		months.put("Okt", "10");
		months.put("Nov", "11");
		months.put("Dec", "12");
		dateFormatter = DateTimeFormatter.ofPattern(pattern1);
		
	}
	
	@Override
	public int compare(String d1, String d2) {
		LocalDate date1 = null;
		LocalDate date2 = null;
		date1 = fromString(d1);
		date2 = fromString(d2);
		if (date1 != null && date2 != null) {
			return date1.compareTo(date2);			
		} else {
			System.out.println("null");
			return 0;
		}
	}
	
	 public LocalDate fromString(String string) {
		 String month = months.get(string.substring(0, 3));
		 String year = string.substring(string.length()-4,string.length());
		 String day = "";
		 if (string.length() == 12) {
			 day = string.substring(4, 6);
		 }else{
			 day = "0" + string.substring(4, 5);
		 }
         if (string != null && !string.isEmpty()) {
        	 LocalDate date = null;
        	 	try {
        	 		date = LocalDate.parse(year+"-"+month+"-"+day, dateFormatter);
        	 	} catch(Exception e) {
        	 		System.out.println(e);
        	 	} 
             return date;
        	 	
         } else {
             return null;
         }
     }
	
}
