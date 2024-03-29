package tdt4140.gr1832.app.containers;

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

	
	public int getHeight() {
		return height;
	}


	public int getWeight() {
		return weight;
	}
	
	public int getSteps() {
		return dailySteps;
	}

	public int  getRestingHR() {
		return restingHeartRate;
	}
	
	public void viewNoHealthData() {
		//-1, so trainerMemberInfoApp can check, and show right values
		this.restingHeartRate = -1;
		this.height = -1;
		this.weight = -1;
		this.dailySteps = -1;
	}
}
