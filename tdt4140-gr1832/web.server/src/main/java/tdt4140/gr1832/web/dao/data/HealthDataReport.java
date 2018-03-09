package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

public class HealthDataReport {
	Integer reportID;
	Integer userID;
	Date date;
	Integer bloodPressure;
	Integer dailySteps;
	Integer restingHeartRate;
	Integer height;
	Integer weight;
	
	public HealthDataReport() {
		
	}
	
	public HealthDataReport(int reportID, int userID,
							Date date, int bloodPressure,
							int dailySteps, int restingHeartRate,
							int height, int weight) {
		this.reportID = reportID;
		this.userID = userID;
		this.date = date;
		this.bloodPressure = bloodPressure;
		this.dailySteps = dailySteps;
		this.restingHeartRate = restingHeartRate;
		this.height = height;
		this.weight = weight;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(int bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public int getDailySteps() {
		return dailySteps;
	}
	public void setDailySteps(int dailySteps) {
		this.dailySteps = dailySteps;
	}
	public int getRestingHeartRate() {
		return restingHeartRate;
	}
	public void setRestingHeartRate(int restingHeartRate) {
		this.restingHeartRate = restingHeartRate;
	}
}
