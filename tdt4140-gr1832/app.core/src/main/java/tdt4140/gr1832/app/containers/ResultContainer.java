package tdt4140.gr1832.app.containers;

public class ResultContainer {
	private int resultID;
	private int userID;
	private int exerciseID;
	private String date;
	private int resultParameter;
	
	public ResultContainer(int resultID, int userID, int exerciseID, String date, int resultParameter) {
		super();
		this.resultID = resultID;
		this.userID = userID;
		this.exerciseID = exerciseID;
		this.date = date;
		this.resultParameter = resultParameter;
	}
	
	public int getResultID() {
		return resultID;
	}

	public int getUserID() {
		return userID;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public String getDate() {
		return date;
	}

	public int getResultParameter() {
		return resultParameter;
	}

}
