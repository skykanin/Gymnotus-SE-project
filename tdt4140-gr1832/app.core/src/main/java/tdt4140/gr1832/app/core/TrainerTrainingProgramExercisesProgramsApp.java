package tdt4140.gr1832.app.core;

import java.util.ArrayList;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.app.containers.ExerciseContainer;
import tdt4140.gr1832.app.containers.ExerciseProgramContainer;
import tdt4140.gr1832.app.containers.ResultContainer;
import tdt4140.gr1832.app.containers.ShowAllUsersContainer;
import tdt4140.gr1832.app.containers.ShowHealthInfoContainer;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;


public class TrainerTrainingProgramExercisesProgramsApp {
	
	private ShowUserInfoContainer containerUser;

	private List<Integer> userids = new ArrayList<Integer>();
	
	private List<ShowHealthInfoContainer> healthContainers = new ArrayList<ShowHealthInfoContainer>();
	
	private ShowAllUsersContainer containerAllUsers = new ShowAllUsersContainer();
	
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	private List<ExerciseContainer> exContainers = new ArrayList<>();
	
	private List<ResultContainer> resContainers;
	
	private static boolean TEST = false;
	public static void setTest(boolean b) {
		TEST = b;
	}
	
	//START  INFORMATION ABOUT PROGRAMS
	public List<ExerciseContainer> getExContainers() {
		return exContainers;
	}
	private List<ExerciseProgramContainer> containerExercisePrograms = new ArrayList<ExerciseProgramContainer>();

	public void requestExerciseProgramInformation() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "exercise_program/all_programs");
		String test = TEST ? "[{\"programID\":1,\"name\":\"Bryst og skuldre\",\"description\":\"Inneholder øvelsene benkpress, skulderpress, pushups og sidehev med hantler. Programmet er designet for deg som ikke er veldig erfaren og vil trene disse to muskelgruppene. Her vil brukeren merke fremgang raskt.\"}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerExercisePrograms = gson.fromJson(test, new TypeToken<List<ExerciseProgramContainer>>(){}.getType());
	}
		
	public ExerciseProgramContainer getExerciseProgramContainer(int i) {
		return containerExercisePrograms.get(i);
	}
	
	public int getContainerExcerciseProgramLength() {
		return (int)containerExercisePrograms.size();
	}

	public void addContainerTocontainerExercisePrograms(ExerciseProgramContainer container) {
		containerExercisePrograms.add(container);	
	}
	
	public List<String> getNamesOfPrograms() {
		requestExerciseProgramInformation();
		List<String> navn = new ArrayList<>();
		for (ExerciseProgramContainer epc : containerExercisePrograms) {
			navn.add(epc.getName());
		}
		return navn;
	}
		
	public int getProgramIDfromName(String name) {
		for (ExerciseProgramContainer epc : containerExercisePrograms) {
			if (epc.getName().equals(name)) {
				return epc.getProgramID();
			}
		}
		return -1;
	}
		
	public List<Integer> getUserIDsOnProgram(String programName){
		
		
		List<Integer> integerIDs = new ArrayList<>();
		List<ShowUserInfoContainer> userIDs = new ArrayList<>();

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI +"exercise_program/get_users?programID=" + getProgramIDfromName(programName));
		String test = TEST ? "[{\"userID\":1,\"username\":\"testbruker\",\"name\":\"Henrik Giske Fosse\",\"email\":\"henrik@fosse.no\",\"phone\":\"23443443\",\"gender\":0,\"age\":22,\"isAnonymous\":false,\"shareExerciseData\":true,\"shareHealthData\":true,\"isTrainer\":true}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		userIDs = gson.fromJson(test, new TypeToken<List<ShowUserInfoContainer>>(){}.getType());
		
		for (ShowUserInfoContainer user : userIDs) {
			integerIDs.add(Integer.parseInt(user.getUserID()));
		}
		
		return integerIDs;
	}
	//END  INFORMATION ABOUT PROGRAMS
		
	//START INFORMATION ABOUT EXERCISES IN PROGRAM
		
	public void getExercisesOnAProgram(int programID) {
		List<ExerciseContainer> exCons = new ArrayList<>();
	
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI +"exercise/get_exercises?program_id=" + programID);
		String test = TEST ? "[{\"exerciseID\":1,\"programID\":1,\"description\":\"Benkpress\",\"sets\":5,\"repsPerSet\":8,\"pauseBetweenSets\":90,\"parameterDescription\":\"Legg pÃ¥ vekt slik at du omtrent akkurat klarer 8 reps. FÃ¸lg instrukser om pause og sets.\"}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		exCons = gson.fromJson(test, new TypeToken<List<ExerciseContainer>>(){}.getType());
		
		exContainers = exCons;
	}
	
	public void getResultsOfExercise(int exerciseID) {
		
		List<ResultContainer> resCons = new ArrayList<>();
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "result/get_results_by_exercise?exercise_id=" + exerciseID);
		String test = TEST ? "[{\"resultID\":30,\"userID\":1,\"exerciseID\":1,\"date\":\"Jan 10, 2018\",\"resultParameter\":70},{\"resultID\":31,\"userID\":1,\"exerciseID\":1,\"date\":\"Jan 14, 2018\",\"resultParameter\":71}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		resCons = gson.fromJson(test, new TypeToken<List<ResultContainer>>(){}.getType());
		
		if (resCons.size() < 1) {
			resContainers = null;
		} else {				
			resContainers = resCons;
		}
	}
	
	public int getSizeResultsOfExercise(int exerciseID) {
		List<ResultContainer> resCons = new ArrayList<>();
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "result/get_results_by_exercise?exercise_id=" + exerciseID);
		String test = TEST ? "[{\"resultID\":30,\"userID\":1,\"exerciseID\":1,\"date\":\"Jan 10, 2018\",\"resultParameter\":70},{\"resultID\":31,\"userID\":1,\"exerciseID\":1,\"date\":\"Jan 14, 2018\",\"resultParameter\":71}]"
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		resCons = gson.fromJson(test, new TypeToken<List<ResultContainer>>(){}.getType());
		
		return resCons.size();
	}
		
	public void getResultsOfExcerciseAndUser(int exerciseID, int userID) {
		List<ResultContainer> resCons = new ArrayList<>();
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "result/get_results_by_user_and_exercise?user_id="+userID+"&exercise_id="+exerciseID);
		String test = TEST ? "[{\"resultID\":30,\"userID\":1,\"exerciseID\":1,\"date\":\"Jan 10, 2018\",\"resultParameter\":70}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		resCons = gson.fromJson(test, new TypeToken<List<ResultContainer>>(){}.getType());
		
		if (resCons.size() < 1) {
			resContainers = null;
		} else {				
			resContainers = resCons;
		}
	}
	
	//END INFORMATION ABOUT EXERCISES IN PROGRAM
	
	public List<ResultContainer> getResContainers() {
		return resContainers;
	}

	public void requestUserInformation_ID(String id) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "user/"+id+"/user_info_id");
		String test = TEST ? "{\"userID\":1,\"username\":\"testbruker\",\"name\":\"Henrik Giske Fosse\",\"email\":\"henrik@fosse.no\",\"phone\":\"23443443\",\"gender\":0,\"age\":22,\"isAnonymous\":true,\"shareExerciseData\":true,\"shareHealthData\":true,\"isTrainer\":true}" 
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
		String test = TEST ? "[1]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
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
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		healthContainers = gson.fromJson(test, new TypeToken<List<ShowHealthInfoContainer>>(){}.getType());
	}
	
	public List<String> getNames(){
		requestAllUserID();
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
			if (user.getName() != null && user.getName().equals(name)) {
				return user.getUserID();
			}
		}
		return "-1";
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
		
		if ((resContainers == null) || (resContainers.size()<1) ){
			
			return null;
		}
		
		List<String> dates = new ArrayList<>();
		
		for (ResultContainer resCon : resContainers) {
			dates.add(resCon.getDate());
		}
		
		return dates;
	}
	

	public List<String> getDatesFromList(List<ResultContainer> resCons) {
		if (resCons.size()<1) {
			return null;
		}
		
		List<String> dates = new ArrayList<>();
		
		for (ResultContainer resCon : resCons) {
			dates.add(resCon.getDate());
		}
		
		return dates;
	}
	
	
	
	public List<Integer> getResults() {
		if ((resContainers == null) ||resContainers.size()<1) {
			return null;
		} 
		
		List<Integer> results = new ArrayList<>();
		
		for (ResultContainer resCon : resContainers) {
			results.add(resCon.getResultParameter());
		}
		
		return results;
	}
	
	public List<Integer> getResultsFromList(List<ResultContainer> resCons) {
		if (resCons.size()<1) {
			return null;
		} 
		
		List<Integer> results = new ArrayList<>();
		
		for (ResultContainer resCon : resCons) {
			results.add(resCon.getResultParameter());
		}
		
		return results;
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

	public void setResContainers(List<ResultContainer> resCons) {
		this.resContainers = resCons;
		
	}

	public void setBaseURI(String string) {
		this.baseURI = string;
		
	}

	public Object getUserIDs() {
		
		return userids;
	}

	public List<ExerciseProgramContainer> getContainerExercisePrograms() {
		
		return containerExercisePrograms;
	}

	public void setUserids(List<Integer> userids2) {
		this.userids = userids2;
		
	}

	public void setContainerExercisePrograms(List<ExerciseProgramContainer> exProgCons) {
		this.containerExercisePrograms = exProgCons;
		
	}

	public void setExContainers(List<ExerciseContainer> exCons) {
		this.exContainers = exCons;
		
	}

	public void setHealthContainers(List<ShowHealthInfoContainer> healthContainers2) {
		this.healthContainers = healthContainers2;
		
	}

	public List<Integer> requestUserIDsOnExercise(int exID) {
		List<Integer> userIDs = new ArrayList<>();
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "result/get_users_added_results_to_exercise?exercise_id=" + exID);
		String test = TEST ? "[{\"userID\":1,\"username\":\"testbruker\",\"name\":\"Henrik Giske Fosse\",\"email\":\"henrik@fosse.no\",\"phone\":\"23443443\",\"gender\":0,\"age\":22,\"isAnonymous\":false,\"shareExerciseData\":true,\"shareHealthData\":true,\"isTrainer\":true}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		List<ShowUserInfoContainer> users = gson.fromJson(test, new TypeToken<List<ShowUserInfoContainer>>(){}.getType());
		
		for (ShowUserInfoContainer user: users) {
			userIDs.add(Integer.parseInt(user.getUserID()));
		}
		
		return userIDs;
	}


}