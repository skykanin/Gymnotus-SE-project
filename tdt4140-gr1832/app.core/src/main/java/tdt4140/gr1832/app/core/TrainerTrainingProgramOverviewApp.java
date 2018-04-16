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



public class TrainerTrainingProgramOverviewApp {
	private List<ExerciseProgramContainer> containerExercisePrograms = new ArrayList<ExerciseProgramContainer>();
	private List<ExerciseContainer> containerExercise = new ArrayList<ExerciseContainer>();
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	private static boolean TEST = false;
	public static void setTest(boolean val) {
		TEST = val;
	}
	
	public void requestExerciseProgramInformation() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "exercise_program/all_programs");
		String test = "";
		if(TEST) {
			test = "[{\"programID\":1,\"name\":\"Bryst og skuldre\",\"description\":\"test for programbeskrivelse\"}]";
		} else {
			test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		}
		Gson gson = new Gson();
		containerExercisePrograms = gson.fromJson(test, new TypeToken<List<ExerciseProgramContainer>>(){}.getType());
	}
	
	public void requestExerciseInformationFromProgramID(int programID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "exercise/get_exercises?program_id=" + programID);
		String test = "";
		if(TEST) {
			test = "[{\"exerciseID\":1,\"programID\":1,\"description\":\"Benkpress\",\"sets\":5,\"repsPerSet\":8,\"pauseBetweenSets\":90,\"parameterDescription\":\"test for beskrivelse av ovelse\"}]";
		} else {
			test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		}
		Gson gson = new Gson();
		containerExercise = gson.fromJson(test, new TypeToken<List<ExerciseContainer>>(){}.getType());
	}
	
	public ExerciseProgramContainer getExerciseProgramContainer(int i) {
		return containerExercisePrograms.get(i);
	}
	
	public List<String> getExerciseList() {
		List<String> ovelser = new ArrayList<>();
		
		for (ExerciseContainer excon : containerExercise ) {
				ovelser.add(excon.getDescription());
		}
		return ovelser;
	}
	
	public String getExerciseContainertoString(String name) {
		for (ExerciseContainer eContainer : containerExercise) {
			if (eContainer.getDescription().equals(name)) {
				return eContainer.toString();
			}
		}
		return "Ingen informasjon";
	}
	
	public ExerciseContainer getExerciseContainer(int i) {
		return containerExercise.get(i);
	}
	
	public int getContainerExcerciseProgramLength() {
		return (int)containerExercisePrograms.size();
	}

	public Object getBaseURI() {
		return baseURI;
	}
}