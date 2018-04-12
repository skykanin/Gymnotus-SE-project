package tdt4140.gr1832.app.core;

import java.util.Date;

public class FeedbackContainer {
	//Container for feedback from trainer to user
	
	Integer feedbackID;
	Integer trainerID;
	Integer userID;
	String date;
	String content;
	
	
	public FeedbackContainer(Integer feedbackID, Integer trainerID, Integer userID, String date, String content) {
		this.feedbackID = feedbackID;
		this.trainerID = trainerID;
		this.userID = userID;
		this.date = date;
		this.content = content;
	}
	public Integer getFeedbackID() {
		return feedbackID;
	}
	public void setFeedbackID(Integer feedbackID) {
		this.feedbackID = feedbackID;
	}
	public Integer getTrainerID() {
		return trainerID;
	}
	public void setTrainerID(Integer trainerID) {
		this.trainerID = trainerID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
