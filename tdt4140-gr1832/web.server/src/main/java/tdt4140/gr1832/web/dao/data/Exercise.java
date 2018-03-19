package tdt4140.gr1832.web.dao.data;

public class Exercise {
	Integer exerciseID;
	Integer programID;
	String description;
	Integer sets;
	Integer repsPerSet;
	Integer pauseBetweenSets;
	String parameterDescription;
	public Integer getExerciseID() {
		return exerciseID;
	}
	public void setExerciseID(Integer exerciseID) {
		this.exerciseID = exerciseID;
	}
	public Integer getProgramID() {
		return programID;
	}
	public void setProgramID(Integer programID) {
		this.programID = programID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSets() {
		return sets;
	}
	public void setSets(Integer sets) {
		this.sets = sets;
	}
	public Integer getRepsPerSet() {
		return repsPerSet;
	}
	public void setRepsPerSet(Integer repsPerSet) {
		this.repsPerSet = repsPerSet;
	}
	public Integer getPauseBetweenSets() {
		return pauseBetweenSets;
	}
	public void setPauseBetweenSets(Integer pauseBetweenSets) {
		this.pauseBetweenSets = pauseBetweenSets;
	}
	public String getParameterDescription() {
		return parameterDescription;
	}
	public void setParameterDescription(String parameter) {
		this.parameterDescription = parameter;
	}
}
