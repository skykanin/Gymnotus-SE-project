package tdt4140.gr1832.app.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;

public class dateComparator implements Comparator<String> {
	String pattern1 = "LLL dd, yyyy";
	String pattern2 = "LLL d, yyyy";
	DateTimeFormatter dateFormatter;
	@Override
	public int compare(String d1, String d2) {
		LocalDate date1 = null;
		LocalDate date2 = null;
		date1 = fromString(d1);
		date2 = fromString(d2);
		if (date1 != null && date2 != null) {
			return date1.compareTo(date2);			
		} else {
			return 0;
		}
	}
	 
	 public LocalDate fromString(String string) {
		 string = string.toLowerCase();
		 if (string.length() == pattern1.length()) {
				dateFormatter = DateTimeFormatter.ofPattern(pattern1);
			} else {
				dateFormatter = DateTimeFormatter.ofPattern(pattern2);
			}
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
	
}
