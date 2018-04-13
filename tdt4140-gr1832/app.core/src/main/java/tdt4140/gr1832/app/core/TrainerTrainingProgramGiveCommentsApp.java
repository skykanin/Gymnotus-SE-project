package tdt4140.gr1832.app.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.aopalliance.reflect.ProgramUnit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import containers.CommentContainer;
import containers.ExerciseProgramContainer;
import containers.FeedbackContainer;
import containers.ShowUserInfoContainer;

public class TrainerTrainingProgramGiveCommentsApp {
	
	private Client client;
	private WebTarget webTarget;
	private String test;
	private Gson gson;
	
	
	private List<CommentContainer> comments = new ArrayList<>();
	private List<FeedbackContainer> feedbacks = new ArrayList<>();
	
	private static final String baseURI = "http://146.185.153.244:8080/api/";
	private String trainerId = ApplicationState.getLoggedInUser().getUserID();
	private Map<Integer,String> users = new HashMap<>();
	private Map<Integer,String> programs = new HashMap<>();
	
	public boolean makeCommentToGroup(int programId, String content) {
		this.setUpConnection("comment/create_comment");
		Date date = new Date();
		 MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		 formData.add("user_id", trainerId + "");
		 formData.add("program_id", programId + "");
		 formData.add("content", content);
		 formData.add("date", this.fromDate(date));
		 Response response = webTarget.request().post(Entity.form(formData)); 
		 return response.getStatus() == 200 ? true : false;
	}
	
	public boolean makeFeedbackToUser(String feedback, int userId) {
		this.setUpConnection("feedback/create_feedback");
		Date date = new Date();
		//POST
		//user_id(int), program_id(int), content(String) og date(på format yyyy-mm-dd
		  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		  formData.add("user_id", userId + "");
		  formData.add("trainer_id", trainerId + "");
		  formData.add("content", feedback);
		  formData.add("date", this.fromDate(date));
		  Response response = webTarget.request().post(Entity.form(formData));
		  
		  return response.getStatus() == 200 ? true : false;
	}
	
	
	public boolean updateComment(int commentId, String content) {
		this.setUpConnection("comment/update_comment");
		Date date = new Date();
		  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		  formData.add("comment_id", Integer.toString(commentId));
		  formData.add("content", content);
		  formData.add("date", this.fromDate(date));
		  Response response = webTarget.request().post(Entity.form(formData));
		  return response.getStatus() == 200 ? true : false;
	}
	
	public boolean updateFeedback(int feedbackId, String content) {
		this.setUpConnection("feedback/update_feedback");
		Date date = new Date();
		  MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		  formData.add("feedback_id", feedbackId +"");
		  formData.add("content", content);
		  formData.add("date", this.fromDate(date));
		  Response response = webTarget.request().post(Entity.form(formData));
		  return response.getStatus() == 200 ? true : false;
	}
	public void requestFeedbackGiven() {
		this.setUpConnection("feedback/get_feedbacks_from_trainer?trainer_id="+trainerId);
		test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		gson = new Gson();
		feedbacks = gson.fromJson(test, new TypeToken<List<FeedbackContainer>>(){}.getType());
	}
	
	private void setUpConnection(String URL) {
		client = ClientBuilder.newClient();
		webTarget = client.target(baseURI+URL);
	}
	
	public void requestCommentGiven() {
		this.setUpConnection("comment/get_comments_from_user?user_id="+trainerId);
		test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		gson = new Gson();
		comments = gson.fromJson(test, new TypeToken<List<CommentContainer>>(){}.getType());
	}
	
	public void requestPrograms() {
		TrainerTrainingProgramOverviewApp progApp = new TrainerTrainingProgramOverviewApp();
		progApp.requestExerciseProgramInformation();
		for (int i = 0; i < progApp.getContainerExcerciseProgramLength(); i++) {
			ExerciseProgramContainer progContainer = progApp.getExerciseProgramContainer(i);
			programs.put(progContainer.getProgramID(), progContainer.getName());
		}
		
	}
	
	public void requestUsers() {
		TrainerMemberInfoApp infoApp = new TrainerMemberInfoApp();
		infoApp.requestAllUserID();
		List<ShowUserInfoContainer> userList = infoApp.getContainerAllUsers().getUsers();
		for (ShowUserInfoContainer uContainer : userList) {
			if (!uContainer.getIsTrainer()) {
				if (uContainer.getIsAnonymous()) {
					users.put(Integer.parseInt(uContainer.getUserID()), "Anonym#"+uContainer.getUserID());
				} else {
					users.put(Integer.parseInt(uContainer.getUserID()), uContainer.getName());
				}
			}
		}
	}
	
	public  List<CommentContainer> getCommentsFromTrainer() {
		return comments;
	}
	
	public  List<FeedbackContainer> getFeedbacksFromTrainer() {
		return feedbacks;
	}
	
	public List<String> getProgramNames() {
		List<String> liste = new ArrayList<>(programs.values());
		return liste;
	}
	
	public int getIdFromUsername(String name) {
		for (Integer c : users.keySet()) {
			if(users.get(c) != null && users.get(c).equals(name)) {
				return c;
			}
		}
		return -1;
	}
	
	public int getIdFromProgramName(String name) {
		for (Integer c : programs.keySet()) {
			if(programs.get(c) != null && programs.get(c).equals(name)) {
				return c;
			}
		}
		return -1;
	}
	
	public List<String> getUserNames() {
		List<String> liste = new ArrayList<>(users.values());
		return liste;
	}
	
	
	
	public String getProgramById(int id) {
		Integer i = id;
		return programs.get(i);
	}
	
	public String getUserById(int id) {
		Integer i = id;
		return users.get(i);
	}
	
	public String fromDate(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
		
	}
	
	public int getCommentId(String date, String content) {
		for (CommentContainer c : comments){
			if (c.getContent().equals(content) && c.getDate().equals(date)) {
				return c.getCommentID();
			}
		}
		return -1;
	}
	
	public int getFeedbackId(String date, String content) {
		for (FeedbackContainer c : feedbacks){
			if (c.getContent().equals(content) && c.getDate().equals(date)) {
				return c.getFeedbackID();
			}
		}
		return -1;
	}
	
	public Map<Integer, String> getUsers() {
		return users;
	}
	
	public  Map<Integer, String> getPrograms() {
		return programs;
	}
}
