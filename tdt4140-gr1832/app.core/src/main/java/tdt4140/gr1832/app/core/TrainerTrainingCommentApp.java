package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainerTrainingCommentApp {
	private List<CommentContainer> comments = new ArrayList<>();
	private static final String baseURI = "http://146.185.153.244:8080/api/";
	
	public boolean makeCommentToGroup(int programId, String content) {
		int userId = 0;
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "comment/create_comment");
		Date date = new Date();
		//POST
		//user_id(int), program_id(int), content(String) og date(på format yyyy-mm-dd
		  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		  formData.add("user_id", userId + "");
		  formData.add("program_id", programId + "");
		  formData.add("content", content);
		  formData.add("date", "2018-03-01");
		  Response response = webTarget.request().post(Entity.form(formData));
		  
		  return response.getStatus() == 200 ? true : false;
	}
	
	public boolean makeCommentToUser(int userId, String content) {
		int programId = 0;
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "comment/create_comment");
		Date date = new Date();
		//POST
		//user_id(int), program_id(int), content(String) og date(på format yyyy-mm-dd
		  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		  formData.add("user_id", userId + "");
		  formData.add("program_id", programId + "");
		  formData.add("content", content);
		  formData.add("date", "2018-03-01");
		  Response response = webTarget.request().post(Entity.form(formData));
		  
		  return response.getStatus() == 200 ? true : false;
	}
	
	
	public boolean updateComment(int commentId, String content) {
		//http://<ip-addresse>:8080/api/comment/update_comment
		int userId = 0;
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI + "comment/create_comment");
		Date date = new Date();
		//POST
		//user_id(int), program_id(int), content(String) og date(på format yyyy-mm-dd
		  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		  formData.add("comment_id", userId + "");
		  formData.add("content", content);
		  formData.add("date", "2018-03-01");
		  Response response = webTarget.request().post(Entity.form(formData));
		  return response.getStatus() == 200 ? true : false;
	}
	
	public void getMadeComments(int userId) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI+"comment/get_comments_from_program?program_id="+userId);
		String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();
		comments = gson.fromJson(test, new TypeToken<List<CommentContainer>>(){}.getType());
	}
	
	public  List<CommentContainer> getComments() {
		return comments;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		String date1 = Integer.toString(date.getYear()) +"-" + date.getMonth() + "-"+date.getDay();
		System.out.println(date1);
		System.out.println(date.toString());
		System.out.println(date);
		TrainerTrainingCommentApp t = new TrainerTrainingCommentApp();
		t.getMadeComments(2);
		System.out.println(t.getComments());
	}
}
