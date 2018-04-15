package tdt4140.gr1832.app.containers;

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
	
	public int getExerciseID() {
		return exerciseID;
	}

	public int getProgramID() {
		return programID;
	}

	public String getDescription() {
		return description;
	}

	public int getSets() {
		return sets;
	}

	public int getRepsPerSet() {
		return repsPerSet;
	}

	public int getPauseBetweenSets() {
		return pauseBetweenSets;
	}

	public String getParameterDescription() {
		return parameterDescription;
	}
	
	public String toString() {
		String result = "";
		result += "Navn: "+description + "\n";
		result += "Antall sett: " + sets + "\n";
		result += "Antall repitisjoner: " + repsPerSet + "\n";
		result += "Pause mellom settene: " + sets + " min\n";
		result += "Øvelsebeskrivelse: " + parameterDescription;
		return result;
		
	}
	}
