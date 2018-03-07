package tdt4140.gr1832.app.core;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import tdt4140.gr1832.app.ui.FxApp;

import com.google.gson.Gson;

public class TrainerSettingsApp {
	// HER HENTER VI BRUKERINFORMASJON
	
	private static String baseURI = "http://146.185.153.244:8080/api/";

	
	public static String checkNull(String in) {
		if (in == null || in =="" || in == "null") {
			return "Ikke spesifisert";
		}
		return in;
	}

	//Her starter endringen av brukerinformasjonen.
	
	//Returnerer true brukerinfo ble endret riktig.
	public static boolean changeUser(String new_name, String new_email, String new_phone, String new_age) {
		ShowUserInfoContainer user = FxApp.getAS().getLoggedInUser();				
				
		Client client = ClientBuilder.newClient();
	  WebTarget webTarget = client.target(baseURI + "user/" + user.getUsername() + "/update_user");
	  
	  //POST
	  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
	  if(new_name != null) formData.add("new_name",  new_name);
	  formData.add("new_email", new_email);
	  formData.add("new_phone", new_phone);
	  formData.add("new_age", new_age);
		 // formData.add("gender",Integer.toString(this.user.getGender()));
		 // formData.add("password", this.user.getPassword());
	  Response response = webTarget.request().post(Entity.form(formData));
	  
	  return response.getStatus() == 200 ? true : false;
	}
	
	//Email
	public static void setEmail(String email) throws IllegalArgumentException {
		if (!(isValidEmailAddress(email))) {
			if (!(email==null)) {
				throw new IllegalArgumentException("Invalid E-mail");	
			}
		}	
	}
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}

	//Sette navn
	
	public void setName(String name) { //ingen tall
		for (int i=0; i<name.length(); i++) {
			if (Character.isDigit(name.charAt(i))) {
				throw new IllegalArgumentException("Name cannot contain any digits");
			}
		}
	}

	//sette alder
	public void setAge(int age) {	
		String stringAge=Integer.toString(age);
		int lengde=stringAge.length();
		if (stringAge.length() < (1) || stringAge.length() >(2)) {
			throw new IllegalArgumentException("age must be a 1 or 2- digit number");
		}
		for ( int i=0; i<lengde;i++) {
			char c= stringAge.charAt(i);
			if (!(Character.isDigit(c))) {
				throw new IllegalArgumentException("age can only consist of digits");
			}
			}
		}
	
	/*
	 MANGLER PASSORD OG KJØNNSENDRINGER
	 */
	
	public static void main(String[] args) {
		

	}
	


}