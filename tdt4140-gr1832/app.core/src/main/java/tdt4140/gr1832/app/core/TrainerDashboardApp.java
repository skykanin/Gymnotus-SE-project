package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainerDashboardApp {
	
	private ShowUserInfoContainer containerUser;

	private List<Integer> userids = new ArrayList<Integer>();
	
	private List<ShowHealthInfoContainer> healthContainers = new ArrayList<ShowHealthInfoContainer>();
	
	private ShowAllUsersContainer containerAllUsers = new ShowAllUsersContainer();
	
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	public void requestUserInformation_ID(String id) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "user/"+id+"/user_info_id");
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerUser = gson.fromJson(test, ShowUserInfoContainer.class);
		containerUser.setUserId(id);
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
			if (user.getName().equals(name)) {
				return user.getUserID();
			}
		}
		return "1";
	}
	
	public List<String> getHeights() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<String> heights = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			heights.add(hContainer.getHeight()+"");
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
	
	//weight
	public List<String> getWeights() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<String> weights = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			weights.add(hContainer.getWeight()+ "");
		}
		
		return weights;
	}
	
	//steps
	public List<String> getSteps() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<String> steps = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			steps.add(hContainer.getSteps()+ "");
		}
		
		return steps;
	}
	
	//restingHR
	public List<String> getRestingHRs() {
		if (healthContainers.size()<1) {
			return null;
		}
		
		List<String> HRs = new ArrayList<>();
		
		for (ShowHealthInfoContainer hContainer : healthContainers) {
			HRs.add(hContainer.getRestingHR()+ "");
		}
		
		return HRs;
	}


}
