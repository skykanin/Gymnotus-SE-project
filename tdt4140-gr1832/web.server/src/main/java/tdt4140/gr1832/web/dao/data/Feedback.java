package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

public class Feedback {
	Integer feedbackID;
	Integer userID;
	Date date;
	String content;
	
	public Integer getFeedbackID() {
		return feedbackID;
	}
	public void setFeedbackID(Integer feedbackID) {
		this.feedbackID = feedbackID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
