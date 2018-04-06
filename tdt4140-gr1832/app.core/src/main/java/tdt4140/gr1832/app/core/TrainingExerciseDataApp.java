package tdt4140.gr1832.app.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.server.handler.ContextHandler.Availability;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainingExerciseDataApp {

	private String baseURI = "http://146.185.153.244:8080/api/";
	
	private int currentprogramID;
	
	private int currentuserID;
	
	private ShowUserInfoContainer user;
	
	private List<String> availableDates = new ArrayList<>();

	private List<ShowExerciseDataContainerFromProgram> resultList = new ArrayList<>();

	private List<ShowHealthInfoContainer> healthList = new ArrayList<ShowHealthInfoContainer>();
	
	private Map<String,SortedSet<Object>> resultMap = new HashMap<String, SortedSet<Object>>();
	
	private TreeMap<String , SortedSet<Object>> sortedResultMap;
	
	//To use functionality in programOverviewApp
	private TrainerTrainingProgramOverviewApp programApp = new TrainerTrainingProgramOverviewApp();
	
	//To use functionality in memberInfoApp class
	private TrainerMemberInfoApp trainerMemberInfoApp = new TrainerMemberInfoApp();
	
	//List of exercisename with coresponding result
	private List<List<String>> returnList;
	
	//2D-list with coloums for programs, and rows for users on program
	private List<List<ShowUserInfoContainer>> usersInPrograms = new ArrayList<>();
	
	//List with programs - corresponding usersInPrograms
	private List<ExerciseProgramContainer> programs = new ArrayList<>();
	
	
	
	
	public TrainingExerciseDataApp() {
		programApp.requestExerciseProgramInformation();
		int counter = 0;
		while (counter < programApp.getContainerExcerciseProgramLength()) {
			//Add programInfo to programs
			programs.add(programApp.getExerciseProgramContainer(counter));
			
			//Request UserInfos connected to a program
			int programID = programApp.getExerciseProgramContainer(counter).getProgramID();
			usersInPrograms.add(getUsersOnAProgram(programID));
			counter++;
		}
	}
	
	private List<ShowUserInfoContainer> getUsersOnAProgram(int programID) {
		
		List<ShowUserInfoContainer> userContainers = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI +"exercise_program/get_users?programID=" + programID);
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		userContainers = gson.fromJson(test, new TypeToken<List<ShowUserInfoContainer>>(){}.getType());
		for (ShowUserInfoContainer containerUser : userContainers) {
			if(containerUser.getIsAnonymous()) {
				containerUser.setUsername("Brukeren er anonym");
				containerUser.setName("Anonym#" + containerUser.getUserID());
				containerUser.setPhone("Brukeren er anonym");
				containerUser.setEmail("Brukeren er anonym");
			}
		}
		return userContainers;
		
	}
	
	public void requestHealthExerciseDataByProgramUserID(int programID, int userID){
		this.currentprogramID = programID;
		this.currentuserID = userID;
		
		//request user information
		trainerMemberInfoApp.requestUserInformation_ID(Integer.toString(userID));
		user = trainerMemberInfoApp.getContainerUser();

		//request exercise result information
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "result/get_results_by_program_and_user?user_id="+userID+"&program_id="+programID);
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		resultList = gson.fromJson(test, new TypeToken<List<ShowExerciseDataContainerFromProgram>>(){}.getType());
		
		webTarget = client.target(baseURI + "health_data/id/"+userID);
		test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		gson = new Gson();
		healthList = gson.fromJson(test, new TypeToken<List<ShowHealthInfoContainer>>(){}.getType());
		if (user != null && (!user.getShareHealthData())) { //Hvis health er false, da vises ikke data
			for (ShowHealthInfoContainer healtcontainer : healthList) {
				healtcontainer.viewNoHealthData();
			}
		}
		
		this.makeResultList();
		
	}

	
	private void makeResultList() {
		int counter = 0;
		while (counter < healthList.size() || counter < resultList.size()) {
			
			
			//Check if there are more Healthinfo in healthList
			if( counter < healthList.size()) {
				ShowHealthInfoContainer healthContainer = healthList.get(counter);
				if (healthContainer.getDate() != null && !resultMap.containsKey(healthContainer.getDate()) && !resultMap.containsValue(healthContainer)) {
					resultMap.put(healthContainer.getDate(), new TreeSet<>(new SortedSetComparator()));
					resultMap.get(healthContainer.getDate()).add(healthContainer);
				} else if (healthContainer.getDate() != null && !resultMap.get(healthContainer.getDate()).contains(healthContainer)) {
					resultMap.get(healthContainer.getDate()).add(healthContainer);
				}
			}
			
			//Check if there are more ResultInfo in resultList;
			if( counter < resultList.size()) {
				ShowExerciseDataContainerFromProgram eContainer = resultList.get(counter);
				if (eContainer.getDate() != null && !resultMap.containsKey(eContainer.getDate())) {
					resultMap.put(eContainer.getDate(), new TreeSet<>(new SortedSetComparator()));
					resultMap.get(eContainer.getDate()).add(eContainer);
					
				} else if (eContainer.getDate() != null) {
					resultMap.get(eContainer.getDate()).add(eContainer);
				}
			}
			counter++;
		}
		
		sortedResultMap = new TreeMap<>(new dateComparator());
		sortedResultMap.putAll(resultMap);
		availableDates.addAll(sortedResultMap.keySet());		
	}
	
	public String getDate(int i) {
		return availableDates.get(i);
	}
	
	public String getAge() {
		return trainerMemberInfoApp.getAge();
	}
	
	public String getGender() {
		return trainerMemberInfoApp.getGender();
	}
	
	public String getSteps(int i) {
		String d = availableDates.get(i);
		SortedSet<Object> liste = sortedResultMap.get(d);
		for (Object container : liste) {
			if (container instanceof ShowHealthInfoContainer) {
				return "" + ((ShowHealthInfoContainer) container).getSteps();
			}
		}
		return "Ikke spesifisert";
	}
	
	public String getWeight(int i) {
		String d = availableDates.get(i);
		SortedSet<Object> liste = sortedResultMap.get(d);
		for (Object container : liste) {
			if (container instanceof ShowHealthInfoContainer) {
				return "" + ((ShowHealthInfoContainer) container).getWeight();
			}
		}
		return "Ikke spesifisert";
	}
	
	public String getRestingHR(int i) {
		String d = availableDates.get(i);
		SortedSet<Object> liste = sortedResultMap.get(d);
		for (Object container : liste) {
			if (container instanceof ShowHealthInfoContainer) {
				return "" + ((ShowHealthInfoContainer) container).getRestingHR();
			}
		}
		return "Ikke spesifisert";
	}
	
	public boolean userIsSharingExerciseData() {
		return user.getShareExerciseData();
	}
	
	public void getExercises(int i) {
		returnList = new ArrayList<List<String>>();
		String d = availableDates.get(i);
		SortedSet<Object> liste = sortedResultMap.get(d);
		for (Object container : liste) {
			if (container instanceof ShowExerciseDataContainerFromProgram) {
				 returnList.add(Arrays.asList(((ShowExerciseDataContainerFromProgram) container).getExerciseName(), "" + ((ShowExerciseDataContainerFromProgram) container).getResultParameter()));
			}
		}
	}
	
	public String getExercise1() {
		if(returnList.size() > 0) {
			return returnList.get(0).get(0);
		}
		return null;
	}
	
	public String getExercise2() {
		if(returnList.size() > 1) {
			return returnList.get(1).get(0);
		}
		return null;
	}
	
	public String getExercise3() {
		if(returnList.size() > 2) {
			return returnList.get(2).get(0);
		}
		return null;
	}
	
	public String getExercise4() {
		if(returnList.size() > 3) {
			return returnList.get(3).get(0);
		}
		return null;
	}
	
	public String getResult1(int i) {
		if(returnList.size() > 0) {
			return returnList.get(0).get(1);
		}
		return null;
	}
	
	public String getResult2(int i) {
		if(returnList.size() > 1) {
			return returnList.get(1).get(1);
		}
		return null;
	}
	
	public String getResult3(int i) {
		if(returnList.size() > 2) {
			return returnList.get(2).get(1);
		}
		return null;
	}
	
	public String getResult4(int i) {
		if(returnList.size() > 3) {
			return returnList.get(3).get(1);
		}
		return null;
	}

	public List<ShowUserInfoContainer> getUsersInProgram(int i) {
		return usersInPrograms.get(i);
	}
	
	public ExerciseProgramContainer getProgram(int index) {
		if (index < programs.size() && index >= -programs.size()) {
			return programs.get(index);
		}
		return null;
	}
	
	public int getProgramsListSize() {
		return programs.size();
	}
	
	public List<String> getDates() {
		return availableDates;
	}
	
	public void clearSortedResultMap() {
		sortedResultMap.clear();
	}
	
	public static void main(String[] args) {
		TrainingExerciseDataApp t = new TrainingExerciseDataApp();
		t.requestHealthExerciseDataByProgramUserID(1, 1);
		t.getExercises(0);
		
	}
	
	
	
}
