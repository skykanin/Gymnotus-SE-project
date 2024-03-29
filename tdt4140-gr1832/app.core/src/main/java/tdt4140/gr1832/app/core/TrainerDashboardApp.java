package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.app.containers.ShowAllUsersContainer;
import tdt4140.gr1832.app.containers.ShowHealthInfoContainer;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class TrainerDashboardApp {
	
	private ShowUserInfoContainer containerUser;

	private List<Integer> userids = new ArrayList<Integer>();
	
	private List<ShowHealthInfoContainer> healthContainers = new ArrayList<ShowHealthInfoContainer>();
	
	private ShowAllUsersContainer containerAllUsers = new ShowAllUsersContainer();
	
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	private static boolean TEST = false;
	public static void setTest(boolean b) {
		TEST = b;
	}
	
	public void requestUserInformation_ID(String id) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "user/"+id+"/user_info_id");
		String test = TEST ? "{\"userID\":1,\"username\":\"testbruker\",\"name\":\"Henrik Giske Fosse\",\"email\":\"henrik@fosse.no\",\"phone\":\"23443443\",\"gender\":0,\"age\":23,\"isAnonymous\":true,\"shareExerciseData\":true,\"shareHealthData\":true,\"isTrainer\":true}" 
							: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
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
		String test = TEST ? "[1]" :webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
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
		this.requestUserInformation_ID(id);
		
		String test = TEST ? "[{\"reportID\":154,\"userID\":1,\"date\":\"Jan 10, 2018\",\"bloodPressure\":120,\"dailySteps\":7912,\"restingHeartRate\":65,\"height\":187,\"weight\":73}]" 
							:  webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		healthContainers = gson.fromJson(test, new TypeToken<List<ShowHealthInfoContainer>>(){}.getType());
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
	
	public String getIDfromName(String name) {
		for (ShowUserInfoContainer user : containerAllUsers.getUsers()){
			if(name == null || user.getName() == null) continue;
			if (user.getName().equals(name)) {
				return user.getUserID();
			}
		}
		return "1";
	}
	
	public List<Integer> getHeights() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<Integer> heights = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			heights.add(hContainer.getHeight());
		}
		
		return heights;
	}

	public List<String> getDates() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<String> dates = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			dates.add(hContainer.getDate());
		}
		
		return dates;
	}
	
	public List<Integer> getWeights() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<Integer> weights = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			weights.add(hContainer.getWeight());
		}
		
		return weights;
	}
	
	public List<Integer> getSteps() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<Integer> steps = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			steps.add(hContainer.getSteps());
		}
		
		return steps;
	}
	
	public List<Integer> getRestingHRs() {
		if (healthContainers.size()<1) {
			return null;
		} 
		
		List<Integer> HRs = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			HRs.add(hContainer.getRestingHR());
		}
		
		return HRs;
	}

	public List<ShowHealthInfoContainer> getHealthContainers() {
		return healthContainers;
	}

	public void setContainerAllUsers(ShowAllUsersContainer containerAllUsers) {
		this.containerAllUsers = containerAllUsers;
	}

	public String getBaseURI() {
		return baseURI;
	}

	public void setContainerUser(ShowUserInfoContainer containerUser) {
		this.containerUser = containerUser;
	}

	public ShowUserInfoContainer getContainerUser() {
		return containerUser;
	}
}
