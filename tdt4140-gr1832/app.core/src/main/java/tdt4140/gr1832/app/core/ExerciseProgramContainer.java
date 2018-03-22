package tdt4140.gr1832.app.core;

public class ExerciseProgramContainer {
	Integer programID;
	String name;
	String description;
	
	public ExerciseProgramContainer(Integer programID, String name, String description) {
		this.programID = programID;
		this.name = name;
		this.description = description;
	}
	
	public Integer getProgramID() {
		return programID;
	}
	public void setProgramID(Integer programID) {
		this.programID = programID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
