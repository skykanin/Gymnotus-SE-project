package tdt4140.gr1832.app.core;


import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.awt.Container;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import containers.ShowAllUsersContainer;
import containers.ShowHealthInfoContainer;
import containers.ShowUserInfoContainer;

public class TrainerMemberInfoApp {
	
	int healtInfoIndex = 0;

	private ShowUserInfoContainer containerUser;
	
	private List<Integer> userids = new ArrayList<Integer>();
	
	private ShowAllUsersContainer containerAllUsers = new ShowAllUsersContainer();
	
	private List<ShowHealthInfoContainer> containerHealth = new ArrayList<ShowHealthInfoContainer>();

	private String baseURI = "http://146.185.153.244:8080/api/";

	
	public void requestUserInformation_ID(String id) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "user/"+id+"/user_info_id");
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerUser = gson.fromJson(test, ShowUserInfoContainer.class);
		containerUser.setUserId(id);
		if(containerUser.getIsAnonymous()) {
			containerUser.setUsername("Brukeren er anonym");
			containerUser.setName("Anonym#" + containerUser.getUserID());
			containerUser.setPhone("Brukeren er anonym");
			containerUser.setEmail("Brukeren er anonym");
		}
	}
	
	public void requestAllUserID() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI +"user/get_all_ids");
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		userids = gson.fromJson(test, new TypeToken<List<Integer>>(){}.getType());
		
		for(Integer i : userids) {
			requestUserInformation_ID(i.toString());
			containerUser.setUserId(i.toString());
			containerAllUsers.addUserInfo(containerUser);
		}
	}
	
	public void requestHealthInformation_ID(String id) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "health_data/id/"+id);
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerHealth = gson.fromJson(test, new TypeToken<List<ShowHealthInfoContainer>>(){}.getType());
		this.requestUserInformation_ID(id);
		healtInfoIndex = containerHealth.size()-1;
		if (containerUser != null && (!containerUser.getShareHealthData())) { //Hvis health er false, da vises ikke data
			for (ShowHealthInfoContainer healtcontainer : containerHealth) {
				healtcontainer.viewNoHealthData();
			}
		}
	}
	
	public String checkNull(String in) {
		if (in == null || in =="" || in == "null") {
			return "Ikke spesifisert";
		}
		return in;
	}
	
	//Not used now
	String convertArrayToString(String[] in ) {
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
		return checkHealthDataView(containerHealth.get(healtInfoIndex).getHeight());
	}

	public String getDate() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		
		return containerHealth.get(healtInfoIndex).getDate();
	}

	public String getWeight() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return checkHealthDataView(containerHealth.get(healtInfoIndex).getWeight());
	}

	public String getSteps() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return checkHealthDataView(containerHealth.get(healtInfoIndex).getSteps());
	}

	public String getRestingHR() {
		if (containerHealth.size()<1) {
			return "Ikke spesifisert";
		}
		return checkHealthDataView(containerHealth.get(healtInfoIndex).getRestingHR());
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
		if (containerUser.getGender() == 0) {
			return "Mann";
		} else if( containerUser.getGender() == 1 ){
			return "Kvinne";
		} else {
			return "Uspesifisert";
		}
	}
	
	public List<String> getNames(){
		List<String> usernames = new ArrayList<>();
		for (ShowUserInfoContainer user : containerAllUsers.getUsers()){
			String name = user.getName();
			if (name != null) {
				usernames.add(name);
			}
			}
		
		return usernames;
		}
	
	public List<ShowUserInfoContainer> getUsers() {
		return containerAllUsers.getUsers();
	}
	
	public String getIDfromName(String name) {
		for (ShowUserInfoContainer user : containerAllUsers.getUsers()){
			if (user.getName().equals(name)) {
				return user.getUserID();
			}
		}
		return "1";
	}
	
	public String getBaseUrl() {
		return baseURI;
	}
	public void addContainerHealth(ShowHealthInfoContainer e) {
		containerHealth.add(e);
	}
	
	public ShowUserInfoContainer getContainerUser() {
		return containerUser;
	}
	public void setContainerUser(ShowUserInfoContainer c) {
		this.containerUser = c;
	}
	
	public void setAllUsersContainer(ShowAllUsersContainer e) {
		this.containerAllUsers = e;
	}

	public void nextDate() {
		if (healtInfoIndex < containerHealth.size()-1) healtInfoIndex++;
		else healtInfoIndex = 0;
	}

	public void lastDate() {
		if (healtInfoIndex >0 ) healtInfoIndex--;
		else healtInfoIndex = containerHealth.size()-1;
		
	}
	
	public void giveDateIndex(int index) {
		if (-1<index && index<containerHealth.size()) {
			this.healtInfoIndex = index;
		} else {
			
		}
	}
	
	public List<String> getDates() {
		List<String> result = new ArrayList<>();
		for (ShowHealthInfoContainer c : containerHealth) {
			result.add(c.getDate());
		}
		return result;
	}
	
	public ShowAllUsersContainer getContainerAllUsers() {
		return containerAllUsers;
	}
	public List<ShowHealthInfoContainer> getContainerHealth() {
		return containerHealth;
	}
	
	private String checkHealthDataView(int i) {
		if (i == -1) {
			return "Brukeren viser ikke helsedata";
		} else{
			return i + "";
		}	
	}
}

