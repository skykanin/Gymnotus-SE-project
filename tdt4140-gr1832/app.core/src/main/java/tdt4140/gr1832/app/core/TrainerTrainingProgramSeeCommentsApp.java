package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.app.containers.CommentContainer;

public class TrainerTrainingProgramSeeCommentsApp {
	private List<CommentContainer> containerComments = new ArrayList<CommentContainer>();
	private String baseURI = "http://146.185.153.244:8080/api/";
	
	private static boolean TEST = false;
	public static void setTest(boolean val) {
		TEST = val;
	}
	
	public void requestProgramComments(int id) {
		
		Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI+"comment/get_comments_from_program?program_id="+id);
        String test = "[{\"commentID\":4,\"userID\":36,\"programID\":1,\"date\":\"Apr 3, 2018\",\"content\":\"Jeg likte godt øvelsene i denne økten.\"}]";
        if(!TEST) {
        	test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);        	
        }
        Gson gson = new Gson();
        containerComments = gson.fromJson(test, new TypeToken<List<CommentContainer>>(){}.getType());


	}
	public List<String> getCommentList() {
		TrainerMemberInfoApp a = new TrainerMemberInfoApp();
		
		List<String> l = new ArrayList<>();
		for (CommentContainer containerComment : containerComments ) {
			a.requestUserInformation_ID(Integer.toString(containerComment.getUserID()));
			String n= a.getName();
			if (a.getContainerUser().getIsAnonymous()){
				n="Anonym#" + containerComment.getUserID();
			}
			if(a.getContainerUser().getIsTrainer()) { 
				l.add("[TRENER] " +n +", " + containerComment.getDate() + ": " + containerComment.getContent());
			}
				else {
					l.add("[BRUKER] " +n +", " + containerComment.getDate() + ": " + containerComment.getContent());
				}
		}
		return l;
	}

	public Object getBaseURI() {
		return baseURI;
	}
	public static void main(String[] args) {
		TrainerTrainingProgramSeeCommentsApp t = new TrainerTrainingProgramSeeCommentsApp();
		t.requestProgramComments(2);
		t.getCommentList();
	}

}
