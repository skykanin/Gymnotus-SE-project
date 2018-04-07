package tdt4140.gr1832.app.core;

public class ExerciseContainer {
	private int exerciseID;
	private int programID;
	private String description;
	private int sets;
	private int repsPerSet;
	private int pauseBetweenSets;
	private String parameterDescription;
	
	public ExerciseContainer(int exerciseID, int programID, String description, int sets, int repsPerSet,
			int pauseBetweenSets, String parameterDescription) {
		this.exerciseID = exerciseID;
		this.programID = programID;
		this.description = description;
		this.sets = sets;
		this.repsPerSet = repsPerSet;
		this.pauseBetweenSets = pauseBetweenSets;
		this.parameterDescription = parameterDescription;
	}
	
	
	}
