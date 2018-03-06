package tdt4140.gr1832.app.core;

import java.util.Date;

public class ShowHealthInfoContainer {
	
	private String date;
	private int height;
	private int weight;
	private int steps;
	private int restingHR;
	
	public ShowHealthInfoContainer(int reportID, int userID, String date,
		int bloodPressure,int dailySteps, int restingHeartRate, int height, int weight ) {
		this.date = date;
		this.restingHR = restingHeartRate;
		this.height = height;
		this.weight = weight;
		this.steps = dailySteps;
	}

	public String getDate() {
		System.out.println(date);
		return date;
	}

	
	public String getHeight() {
		return height +"";
	}


	public String getWeight() {
		return weight+ "";
	}

	
	public String getSteps() {
		return steps + "";
	}

	

	public String  getRestingHR() {
		return restingHR+ "";
	}

	

	
	
}
