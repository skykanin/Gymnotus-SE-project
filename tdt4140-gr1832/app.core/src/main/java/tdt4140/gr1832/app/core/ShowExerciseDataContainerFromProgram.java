package tdt4140.gr1832.app.core;

public class ShowExerciseDataContainerFromProgram {

	private int resultID;
	private int userID;
	private int exerciseID;
	private String date;
	private int resultParameter;
	private String description;
	
	
	public ShowExerciseDataContainerFromProgram(int resultID, int userID, String description, int exerciseID, String date,
			int resultParameter) {
		this.resultID = resultID;
		this.userID = userID;
		this.exerciseID = exerciseID;
		this.date = date;
		this.resultParameter = resultParameter;
		this.description = description;
	}
	public int getResultID() {
		return resultID;
	}

	public int getExerciseID() {
		return exerciseID;
	}
	
	public int getResultParameter() {
		return resultParameter;
	}
	
	public String getExerciseName() {
		return description;
	}

	@Override
	public String toString() {
		return "ShowExerciseDataContainerFromProgram [exerciseID=" + exerciseID +", Navn=" +description +", date=" + date
				+ ", resultParameter=" + resultParameter + "]";
	}
	public String getDate() {
		return date;
	}
	
	
}
