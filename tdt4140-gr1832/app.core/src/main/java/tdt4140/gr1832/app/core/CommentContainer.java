package tdt4140.gr1832.app.core;

public class CommentContainer {
	Integer commentID;
	Integer userID;
	Integer programID;
	String date;
	String content;
	
	
	
	public CommentContainer(Integer commentID, Integer userID, Integer programID, String date, String content) {
		this.commentID = commentID;
		this.userID = userID;
		this.programID = programID;
		this.date = date;
		this.content = content;
	}
	
	public Integer getCommentID() {
		return commentID;
	}
	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getProgramID() {
		return programID;
	}
	public void setProgramID(Integer programID) {
		this.programID = programID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
