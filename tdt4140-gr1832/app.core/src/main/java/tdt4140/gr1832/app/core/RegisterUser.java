package tdt4140.gr1832.app.core;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import com.google.gson.Gson;

public class RegisterUser {
	
	User user = new User();
	String baseURI = "http://146.185.153.244:8080/api/";
	
	public void registerUser(User u) {
		this.user= u;
		
		Map<String, String> telefonliste= new HashMap();
		
	
		Client client = ClientBuilder.newClient();
	  WebTarget webTarget = client.target(baseURI + "user/create_user");
	  
	  //POST
	  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
	  formData.add("username", this.user.getUsername());
	  formData.add("password", this.user.getPassword());
	  formData.add("name", this.user.getName());
	  formData.add("email", this.user.getEmail());
	  formData.add("phone", this.user.getPhone());
	  formData.add("gender",Integer.toString(this.user.getGender()));
	  formData.add("age", Integer.toString(this.user.getAge()));
	  
	  Response response = webTarget.request().post(Entity.form(formData));
	  
	  System.out.println(response.getStatus());
		
	}

}
	
	
	 
	
	

		
		
	
	
	

