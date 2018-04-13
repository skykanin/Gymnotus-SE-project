package containers;

import java.util.Date;

public class FeedbackContainer {
	//Container for feedback from trainer to user
	
	int feedbackID;
	int trainerID;
	int userID;
	String date;
	String content;
	
	
	public FeedbackContainer(Integer feedbackID, Integer trainerID, Integer userID, String date, String content) {
		this.feedbackID = feedbackID;
		this.trainerID = trainerID;
		this.userID = userID;
		this.date = date;
		this.content = content;
	}
	public int getFeedbackID() {
		return feedbackID;
	}

	public int getTrainerID() {
		return trainerID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}
	
}
