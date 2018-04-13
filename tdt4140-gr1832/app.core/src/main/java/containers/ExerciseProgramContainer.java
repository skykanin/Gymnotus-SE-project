package containers;

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
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
}
