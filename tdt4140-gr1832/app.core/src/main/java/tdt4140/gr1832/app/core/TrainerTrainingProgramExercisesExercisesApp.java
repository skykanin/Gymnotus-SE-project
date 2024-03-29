package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt4140.gr1832.app.containers.ExerciseContainer;
import tdt4140.gr1832.app.containers.ExerciseProgramContainer;
import tdt4140.gr1832.app.containers.ResultContainer;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class TrainerTrainingProgramExercisesExercisesApp {
	
	private ShowUserInfoContainer containerUser;
	
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	private List<ExerciseContainer> exContainers = new ArrayList<>();
	
	private List<ResultContainer> resContainers;
	
	private ObservableList<String> namesOfExercises = FXCollections.observableArrayList();
	
	private List<ExerciseProgramContainer> containerExercisePrograms = new ArrayList<ExerciseProgramContainer>();
	
	private static boolean TEST = false;
	public static void setTest(boolean b) {
		TEST = b;
	}
	
	//START  INFORMATION ABOUT PROGRAMS

	public List<ExerciseContainer> getExContainers() {
		return exContainers;
	}

	public void requestExerciseProgramInformation() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "exercise_program/all_programs");
		String test = TEST ? "[{\"programID\":1,\"name\":\"Bryst og skuldre\",\"description\":\"Inneholder øvelsene benkpress, skulderpress, pushups og sidehev med hantler. Programmet er designet for deg som ikke er veldig erfaren og vil trene disse to muskelgruppene. Her vil brukeren merke fremgang raskt.\"}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerExercisePrograms = gson.fromJson(test, new TypeToken<List<ExerciseProgramContainer>>(){}.getType());
	}
	
	
	//END  INFORMATION ABOUT PROGRAMS
		
	//START INFORMATION ABOUT EXERCISES IN PROGRAM
		
	public List<Integer> requestUserIDsOnExercise(int exID){
		List<Integer> userIDs = new ArrayList<>();
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "result/get_users_added_results_to_exercise?exercise_id=" + exID);
		String test = TEST ? "[{\"userID\":37,\"username\":\"stianismar\",\"name\":\"Stian Ismar\",\"email\":\"Stismar@gmail.com\",\"phone\":\"12345678\",\"gender\":0,\"age\":17,\"isAnonymous\":true,\"shareExerciseData\":false,\"shareHealthData\":true,\"isTrainer\":false}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		List<ShowUserInfoContainer> users = gson.fromJson(test, new TypeToken<List<ShowUserInfoContainer>>(){}.getType());
		
		for (ShowUserInfoContainer user: users) {
			userIDs.add(Integer.parseInt(user.getUserID()));
		}
		
		return userIDs;
	}
		
	public void requestExerciseContainers() {
		namesOfExercises.clear();
		
		Client client = ClientBuilder.newClient();
		
		requestExerciseProgramInformation();
		
		for (ExerciseProgramContainer epc : containerExercisePrograms) {
		WebTarget webTarget = client.target(baseURI + "exercise/get_exercises?program_id=" + epc.getProgramID());
		String test = TEST ? "[{\"exerciseID\":1,\"programID\":1,\"description\":\"Benkpress\",\"sets\":5,\"repsPerSet\":8,\"pauseBetweenSets\":90,\"parameterDescription\":\"Legg pÃ¥ vekt slik at du omtrent akkurat klarer 8 reps. FÃ¸lg instrukser om pause og sets.\"}]" 
				: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		List<ExerciseContainer> exCons = gson.fromJson(test, new TypeToken<List<ExerciseContainer>>(){}.getType());
			for (ExerciseContainer exCon : exCons) {
				exContainers.add(exCon);
				namesOfExercises.add(exCon.getDescription());
			}
		}
	}
		
		
	public int getIDfromExerciseName(String exName) {
		for (ExerciseContainer exCon : exContainers) {
			if (exCon.getDescription().equals(exName)) {
				return exCon.getExerciseID();
			}
		}
		return -1;
	}
		

	public ObservableList<String> getNamesOfExercises() {
		return namesOfExercises;
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
	
	
	public List<String> getDates() {
		
		if ((resContainers == null) || (resContainers.size()<1) ){
			
			return null;
		}
		
		List<String> dates = new ArrayList<>();
		
		for (ResultContainer resCon : resContainers) {
			if (!dates.contains(resCon.getDate())){
				
				dates.add(resCon.getDate());
			}
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

	public List<ExerciseProgramContainer> getContainerExercisePrograms() {
		return containerExercisePrograms;
	}


	public void setContainerExercisePrograms(List<ExerciseProgramContainer> exProgCons) {
		this.containerExercisePrograms = exProgCons;
		
	}

	public void setExContainers(List<ExerciseContainer> exCons) {
		this.exContainers = exCons;
	}
}
