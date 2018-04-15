package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

public class Result {
	Integer resultID;
	Integer userID;
	Integer exerciseID;
	Date date;
	Integer resultParameter;
	String description;
	public Integer getResultID() {
		return resultID;
	}
	public void setResultID(Integer resultID) {
		this.resultID = resultID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getExerciseID() {
		return exerciseID;
	}
	public void setExerciseID(Integer exerciseID) {
		this.exerciseID = exerciseID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getResultParameter() {
		return resultParameter;
	}
	public void setResultParameter(Integer resultParameter) {
		this.resultParameter = resultParameter;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}
	
}
