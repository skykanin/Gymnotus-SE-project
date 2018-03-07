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
	public static boolean changeUser(String username, String new_name, String new_email, String new_phone, String new_age, String new_gender) {		
				
		Client client = ClientBuilder.newClient();
	  WebTarget webTarget = client.target(baseURI + "user/" + username + "/update_user");
	  
	  //POST
	  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
	  if(new_name != null) formData.add("new_name",  new_name);
	  formData.add("new_email", new_email);
	  formData.add("new_phone", new_phone);
	  formData.add("new_age", new_age);
	  formData.add("new_gender",new_gender);
		 // formData.add("password", this.user.getPassword());
	  Response response = webTarget.request().post(Entity.form(formData));
	  
	  return response.getStatus() == 200 ? true : false;
	}
	
	/*
	 MANGLER PASSORD OG KJØNNSENDRINGER
	 */
	
	public static void main(String[] args) {
	}
	


}