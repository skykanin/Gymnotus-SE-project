package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

public class Comment {
	Integer commentID;
	Integer userID;
	Integer programID;
	Date date;
	String content;
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
