package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import containers.ShowCommentsContainer;

public class TrainerTrainingProgramSeeCommentsApp {
	private List<ShowCommentsContainer> containerComments = new ArrayList<ShowCommentsContainer>();
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	public void requestProgramComments(int id) {
		
		Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI+"comment/get_comments_from_program?program_id="+id);
        String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        containerComments = gson.fromJson(test, new TypeToken<List<ShowCommentsContainer>>(){}.getType());


	}
	public List<String> getCommentList() {
		TrainerMemberInfoApp a = new TrainerMemberInfoApp();
		
		List<String> l = new ArrayList<>();
		for (ShowCommentsContainer containerComment : containerComments ) {
			a.requestUserInformation_ID(Integer.toString(containerComment.getUserID()));
			String n= a.getName();
			l.add(n +", " + containerComment.getDate() + ": " + containerComment.getContent());
		}
		return l;
	}
	
	public ShowCommentsContainer getProgramCommentsContainer(int i) {
		return containerComments.get(i);
	}
	
	public int getContainerExcerciseProgramLength() {
		return (int)containerComments.size();
	}

	//public void addContainerTocontainerExercisePrograms(ShowCommentsContainer container) {
	//	containerComments.add(container);
		
	//}

	public Object getBaseURI() {
		return baseURI;
	}
	
	
	
	public String toString() {
		return ""+containerComments;
	}


}