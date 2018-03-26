package tdt4140.gr1832.app.core;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainingExerciseDataApp {

	private String baseURI = "http://146.185.153.244:8080/api/";
	
	private ShowExerciseDataContainer containerExercise = new ShowExerciseDataContainer();
	
	public void requestExerciseIformation_ID(String id) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "health_data/id/"+id);
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		containerExercise = gson.fromJson(test, new TypeToken<List<ShowHealthInfoContainer>>(){}.getType());
	}
}
