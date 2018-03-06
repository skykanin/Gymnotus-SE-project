package tdt4140.gr1832.app.core;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainerMemberInfoApp {

	private ShowUserInfoContainer containerUser;
	
	private List<Integer> userids = new ArrayList<Integer>();
	
	ShowAllUsersContainer containerAllUsers = new ShowAllUsersContainer();
	
	private List<ShowHealthInfoContainer> containerHealth = new ArrayList<ShowHealthInfoContainer>();

	private String baseURI = "http://146.185.153.244:8080/api/";

	
	public void requestUserInformation_ID(String id) {

	Client client = ClientBuilder.newClient();
	WebTarget webTarget = client.target(baseURI + "user/"+id+"/user_info_id");
	String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
	System.out.println(test);
	Gson gson = new Gson();
	containerUser = gson.fromJson(test, ShowUserInfoContainer.class);
	}
	
	public void requestAllUserID() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI +"user/get_all_ids");
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		userids = gson.fromJson(test, new TypeToken<List<Integer>>(){}.getType());
		
		for(Integer i : userids) {
			requestUserInformation_ID(i.toString());
			containerAllUsers.addUserInfo(containerUser);
		}
	}
	
	public void requestHealthInformation_ID(String id) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "health_data/id/"+id);
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerHealth = gson.fromJson(test, new TypeToken<List<ShowHealthInfoContainer>>(){}.getType());
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
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return containerHealth.get(0).getHeight();
	}

	public String getDate() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return containerHealth.get(0).getDate();
	}

	public String getWeight() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return containerHealth.get(0).getWeight();
	}

	public String getSteps() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return containerHealth.get(0).getSteps();
	}

	public String getRestingHR() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return containerHealth.get(0).getRestingHR();
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
	
	public List<String> getNames(){
		List<String> usernames = new ArrayList<>();
		for (ShowUserInfoContainer user : containerAllUsers.getUsers()){
			String name = user.getName();
			if (name != null) {
				usernames.add(name);
			}
			}
		System.out.println(usernames);
		return usernames;
		}
	
	public String getIDfromName(String name) {
		for (ShowUserInfoContainer user : containerAllUsers.getUsers()){
			if (user.getName() == name) {
				System.out.println(user.getUserID());
				return user.getUserID();
			}
		}
		return "1";
	}


	public static void main(String[] args) {
		TrainerMemberInfoApp t = new TrainerMemberInfoApp();
//		t.requestUserInformation_ID("1");
//		t.requestHealthInformation_ID("1");
		t.requestAllUserID();
	}
}
