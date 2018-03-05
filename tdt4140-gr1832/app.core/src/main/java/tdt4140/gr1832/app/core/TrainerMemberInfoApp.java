package tdt4140.gr1832.app.core;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import com.google.gson.Gson;

public class TrainerMemberInfoApp {

	private ShowUserInfoContainer containerUser;
	private ShowHealthInfoContainer containerHealth;
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	
	public void requestUserInformation_ID() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "user/1/user_info_id");
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerUser = gson.fromJson(test, ShowUserInfoContainer.class);
	}
	
	public void requestHelthInformation_ID() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "health_data/id/1");
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerHealth = gson.fromJson(test, ShowHealthInfoContainer.class);
	}

	public String checkNull(String in) {
		if (in == null || in =="" || in == "null") {
			return "Ikke spesifisert";
		}
		return in;
	}
	
	private String convertArrayToString(String[] in ) {
		String result = "";
		for (int i=0; i<in.length; i++) {
			result += in[i];
		}
		return result;
	}
	
	public String getHeight() {
		return convertArrayToString(containerHealth.getHeight());
	}

	public String getDate() {
		return convertArrayToString(containerHealth.getDate());
	}

	public String getWeight() {
		return convertArrayToString(containerHealth.getWeight());
	}

	public String getSteps() {
		return convertArrayToString(containerHealth.getSteps());
	}

	public String getRestingHR() {
		return convertArrayToString(containerHealth.getRestingHR());
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
		TrainerMemberInfoApp t = new TrainerMemberInfoApp();
		t.requestUserInformation_ID();
		t.requestHelthInformation_ID();
	}
}
