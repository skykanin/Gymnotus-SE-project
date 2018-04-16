package tdt4140.gr1832.app.core;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class TrainerSettingsApp {
	private static String baseURI = "http://146.185.153.244:8080/api/";
	
	private static Response response;
	private static boolean TEST = false;
	public void setTest(boolean val) {
		TEST = val;
	}

	
	public String checkNull(String in) {
		if (in == null || in.equals("")) {
			return "Ikke spesifisert";
		}
		return in;
	}
	
	public static Response getResponse() {
		return response;
	}
	
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
	  response = Response.status(400).build();
	  if(!TEST) {
		  response = webTarget.request().post(Entity.form(formData));		  
	  }
	  
	  return response.getStatus() == 200 ? true : false;
	}
}
