package tdt4140.gr1832.app.core;

import java.util.Date;

public class ShowHealthInfoContainer {
	
	private String date;
	private int height;
	private int weight;
	private int dailySteps;
	private int restingHeartRate;
	private int userID;

	
	public ShowHealthInfoContainer(int reportID, int userID, String date,
								   int bloodPressure,int dailySteps, int restingHeartRate,
								   int height, int weight, boolean isAnonymous,
								   boolean shareHealthData, boolean shareExerciseData) {

		this.date = date;
		this.restingHeartRate = restingHeartRate;
		this.height = height;
		this.weight = weight;
		this.dailySteps = dailySteps;
		this.userID = userID;
	}

	public String getDate() {
		return date;
	}

	
	public String getHeight() {
		return height +"";
	}


	public String getWeight() {
		return weight+ "";
	}

	
	public String getSteps() {
		return dailySteps + "";
	}

	

	public String  getRestingHR() {
		return restingHeartRate+ "";
	}
	

	

	
	
}
