package tdt4140.gr1832.app.core;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

public class TrainerSettingsApp {
	
	private ShowUserInfoContainer containerUser;
	
	private String baseURI = "http://146.185.153.244:8080/api/";

	
	public void requestUserInformation_ID(String id) {

	Client client = ClientBuilder.newClient();
	WebTarget webTarget = client.target(baseURI + "user/"+id+"/user_info_id");
	String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
	Gson gson = new Gson();
	containerUser = gson.fromJson(test, ShowUserInfoContainer.class);
	}
	
	public String checkNull(String in) {
		if (in == null || in =="" || in == "null") {
			return "Ikke spesifisert";
		}
		return in;
	}

	public String getName() {
		return checkNull(containerUser.getName());
	}

	public String getUsername() {
		return checkNull(containerUser.getUsername());
	}

	public String getEmail() {
		return checkNull(containerUser.getEmail());
	}

	public String getTlf() {
		return checkNull(containerUser.getPhone());
	}

	public String getAge() {
		return checkNull(""+ containerUser.getAge());
	}

	public String getGender() {
		if (containerUser.getGender() == 1) {
			return "Mann";
		} else if( containerUser.getGender() == 2 ){
			return "Kvinne";
		}
		return checkNull("" + containerUser.getGender());
	}
	
	public static void main(String[] args) {
		TrainerSettingsApp t = new TrainerSettingsApp();
		t.requestUserInformation_ID("1");
//		t.requestAllUserID();
	}
	


}