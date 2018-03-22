package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainerTrainingProgramOverviewApp {
	private List<ExerciseProgramContainer> containerExercisePrograms = new ArrayList<ExerciseProgramContainer>();
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	public void requestExerciseProgramInformation() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "exercise_program/all_programs");
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerExercisePrograms = gson.fromJson(test, new TypeToken<List<ExerciseProgramContainer>>(){}.getType());
	}
	
	public ExerciseProgramContainer getExerciseProgramContainer(int i) {
		return containerExercisePrograms.get(i);
	}
	
	public int getContainerExcerciseProgramLength() {
		return (int)containerExercisePrograms.size();
	}
}
