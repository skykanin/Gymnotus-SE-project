package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.app.comparators.InfoDateComparator;
import tdt4140.gr1832.app.comparators.SortedSetComparator;
import tdt4140.gr1832.app.containers.ExerciseProgramContainer;
import tdt4140.gr1832.app.containers.ShowExerciseDataContainerFromProgram;
import tdt4140.gr1832.app.containers.ShowHealthInfoContainer;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class TrainerTrainingProgramAttendantsApp {
 
	public final String baseURI = "http://146.185.153.244:8080/api/";
	
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
	
	public static boolean TEST = false;	
	
	public List<ShowHealthInfoContainer> getHealthList() {
		return healthList;
	}
	
	public void TrainingExerciseDataAppSetup() {
		if(TEST) {
			programApp.setTest(true);
		}
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
		String test = "[{\"userID\":37,\"username\":\"stianismar\",\"name\":\"Stian Ismar\",\"email\":\"Stismar@gmail.com\",\"phone\":\"12345678\",\"gender\":0,\"age\":17,\"isAnonymous\":true,\"shareExerciseData\":true,\"shareHealthData\":true,\"isTrainer\":false}]";
		if(!TEST) {			
			test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		}
		Gson gson = new Gson();
		userContainers = gson.fromJson(test, new TypeToken<List<ShowUserInfoContainer>>(){}.getType());
		return checkAndMakeAnonymous(userContainers);
	}
	
	public List<ShowUserInfoContainer> checkAndMakeAnonymous(List<ShowUserInfoContainer> userContainers) {
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
		healthList.clear();
		resultList.clear();
		//request user information
		trainerMemberInfoApp.requestUserInformation_ID(Integer.toString(userID));
		user = trainerMemberInfoApp.getContainerUser();

		//request exercise result information
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "result/get_results_by_program_and_user?user_id="+userID+"&program_id="+programID);
		String test = "[{\"resultID\":30,\"userID\":1,\"exerciseID\":1,\"date\":\"Jan 10, 2018\",\"resultParameter\":70,\"description\":\"Benkpress\"}]";
		if(!TEST) {
			test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);			
		}
		Gson gson = new Gson();
		resultList = gson.fromJson(test, new TypeToken<List<ShowExerciseDataContainerFromProgram>>(){}.getType());
		webTarget = client.target(baseURI + "health_data/id/"+userID);
		test = "[{\"reportID\":154,\"userID\":1,\"date\":\"Jan 10, 2018\",\"bloodPressure\":120,\"dailySteps\":7912,\"restingHeartRate\":65,\"height\":187,\"weight\":73}]";
		if(!TEST) {
			test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);			
		}
		gson = new Gson();
		healthList = gson.fromJson(test, new TypeToken<List<ShowHealthInfoContainer>>(){}.getType());
		if (user != null && (!user.getShareHealthData())) { //Hvis health er false, da vises ikke data
			for (ShowHealthInfoContainer healtcontainer : healthList) {
				healtcontainer.viewNoHealthData();
			}
		}
		
		this.makeResultList();
	}
	public void makeResultList() {
		int counter = 0;
		resultMap.clear();
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
		//Because of problems with format and parsing for DateTimeFormatter 
		//in gitlab, these features can not be included in the test. 
		//But all og the main features will still be testet, Infocomparator will not.
		//InfoDate Comparator are tested manually. An important notice is that 
		//InfoComparator is working on mvn locally
		if (!TEST) {
			sortedResultMap = new TreeMap<>(new InfoDateComparator());
			sortedResultMap.putAll(resultMap);
		} else {
			sortedResultMap = new TreeMap<>();
			sortedResultMap.putAll(resultMap);
		}
		availableDates.clear();
		availableDates.addAll(sortedResultMap.keySet());		
	}
	
	public String getDate(int i) {
		if (i> availableDates.size()-1) {
			return null;
		}
		return availableDates.get(i);
	}
	
	public String getAge() {
		return "" + user.getAge();
	}
	
	public String getGender() {
		if (user.getGender() == 0) {
			return "Mann";
		} else if(user.getGender() == 1 ){
			return "Kvinne";
		} else {
			return "Ikke spesifisert";
		}
	}
	
	public String getSteps(int i) {
		String result = "";
		String d = availableDates.get(i);
		SortedSet<Object> liste = sortedResultMap.get(d);
		for (Object container : liste) {
			if (container instanceof ShowHealthInfoContainer) {
				result = "" + ((ShowHealthInfoContainer) container).getSteps();
				if(result.equals("-1")) {
					result = "Brukeren viser ikke helsedata";
				}
				return result;
			}
		}
		return "Ikke spesifisert";
	}
	
	public String getWeight(int i) {
		String result = "";
		String d = availableDates.get(i);
		SortedSet<Object> liste = sortedResultMap.get(d);
		for (Object container : liste) {
			if (container instanceof ShowHealthInfoContainer) {
				result="" + ((ShowHealthInfoContainer) container).getWeight();
			}
			if(result.equals("-1")) {
				result = "Brukeren viser ikke helsedata";
			}
			return result;
		}
		return "Ikke spesifisert";
	}
	
	public String getRestingHR(int i) {
		String result = "";
		String d = availableDates.get(i);
		SortedSet<Object> liste = sortedResultMap.get(d);
		for (Object container : liste) {
			if (container instanceof ShowHealthInfoContainer) {
				result =  "" + ((ShowHealthInfoContainer) container).getRestingHR();
			}
			if(result.equals("-1")) {
				result = "Brukeren viser ikke helsedata";
			}
			return result;
		}
		return "Ikke spesifisert";
	}
	
	public boolean userIsSharingExerciseData() {
		if (user!= null) {
			return user.getShareExerciseData();
		} return false;
	}
	
	public void getExercises(int i) {
		if (i < availableDates.size()) {
			returnList = new ArrayList<List<String>>();
			String d = availableDates.get(i);
			SortedSet<Object> liste = sortedResultMap.get(d);
			for (Object container : liste) {
				if (container instanceof ShowExerciseDataContainerFromProgram) {
					returnList.add(Arrays.asList(((ShowExerciseDataContainerFromProgram) container).getExerciseName(), "" + ((ShowExerciseDataContainerFromProgram) container).getResultParameter()));
				}
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
	
	public String getResult1() {
		if(returnList.size() > 0) {
			return returnList.get(0).get(1);
		}
		return null;
	}
	
	public String getResult2() {
		if(returnList.size() > 1) {
			return returnList.get(1).get(1);
		}
		return null;
	}
	
	public String getResult3() {
		if(returnList.size() > 2) {
			return returnList.get(2).get(1);
		}
		return null;
	}
	
	public String getResult4() {
		if(returnList.size() > 3) {
			return returnList.get(3).get(1);
		}
		return null;
	}

	public List<ShowUserInfoContainer> getUsersInProgram(int i) {
		if (i < usersInPrograms.size()) {
			return usersInPrograms.get(i);
		}
		return null;
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

	//Helpmethods for tests
	
	public void addContainerHealthList(List<ShowHealthInfoContainer> hContainers) {
		healthList.addAll(hContainers);
	}
	
	public void addContainerExerciseList(List <ShowExerciseDataContainerFromProgram> eContainers) {
		resultList.addAll(eContainers);
	}
	
	public void addUsersInProgram(List<ShowUserInfoContainer> cList) {
		usersInPrograms.add(cList);
	}
	
	public void setUser(ShowUserInfoContainer container) {
		this.user = container;
	}
	
	public void addPrograms(List<ExerciseProgramContainer> programs) {
		this.programs.addAll(programs);
	}
	
	public int getResultMapSize() {
		return sortedResultMap.size();
	}
	
	public static void setTestTrue() {
		TEST = true;
	}
}
