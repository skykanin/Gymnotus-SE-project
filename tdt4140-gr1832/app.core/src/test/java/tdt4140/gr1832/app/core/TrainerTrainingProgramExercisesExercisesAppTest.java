package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import containers.ExerciseContainer;
import containers.ExerciseProgramContainer;
import containers.ResultContainer;
import containers.ShowAllUsersContainer;
import containers.ShowUserInfoContainer;

public class TrainerTrainingProgramExercisesExercisesAppTest{
	
	ShowUserInfoContainer userContainer;
	ShowUserInfoContainer userContainer1;
	ShowUserInfoContainer userContainer2;
	
	
	ShowAllUsersContainer showAllUserContainer; 
	TrainerTrainingProgramExercisesExercisesApp testapp = new TrainerTrainingProgramExercisesExercisesApp();
	List<ExerciseContainer> exCons = new ArrayList<>();
	ExerciseContainer exCon;
	ExerciseProgramContainer exProgCon;
	List<ExerciseProgramContainer> exProgCons = new ArrayList<>();
	ResultContainer resCon;
	List<ResultContainer> resCons = new ArrayList<>();
	List<Integer> userids = new ArrayList<>();
	ResultContainer resCon2;
	
	
	
	@Before
	public void setup() {
		
		
		
		showAllUserContainer = new ShowAllUsersContainer(); 
		userContainer = new ShowUserInfoContainer("brukernavn", "passord", "navn1",11, 0, "email", "telefon", false, true, true, false);
		userContainer1 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 1, "email", "telefon", false, true, true, false);
		userContainer2 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 2, "email", "telefon", false, true, true, false);
		
		
		exCon = new ExerciseContainer(1, 2, "biceps", 3, 10, 2, "antallKg");
		exCons.add(exCon);
	
		exProgCon = new ExerciseProgramContainer(1, "etNavn", "enBeskrivelse");
		exProgCons.add(exProgCon);
		
		resCon = new ResultContainer(2, 3, 1, "Mar 1, 2018", 10);
		resCons.add(resCon);
		
		userids.add(1);
		userids.add(2);
		userids.add(3);
		
		testapp.setContainerUser(userContainer);
		
		
	}
	
	@Test
	public void testEverything() {
		Assert.assertEquals(userContainer,testapp.getContainerUser());
		Assert.assertEquals(null,testapp.getDates());
		Assert.assertEquals(null,testapp.getResults());
		testapp.setExContainers(exCons);
		testapp.setResContainers(new ArrayList<ResultContainer>());
		Assert.assertEquals(null,testapp.getDates());
		Assert.assertEquals(null,testapp.getResults());
		testapp.setResContainers(resCons);
		testapp.setContainerExercisePrograms(exProgCons);
		Assert.assertEquals(Arrays.asList(), testapp.getNamesOfExercises());
		Assert.assertEquals(exCon, testapp.getExContainers().get(0));
		Assert.assertEquals(resCon, testapp.getResContainers().get(0));
		Assert.assertEquals(exProgCon, testapp.getContainerExercisePrograms().get(0));
		
		Assert.assertEquals(Arrays.asList("Mar 1, 2018"), testapp.getDates());
		Assert.assertEquals(Arrays.asList(10), testapp.getResults());
		
		showAllUserContainer.addUserInfo(userContainer);
		
		
		
		
		
		
		
	}
	
	@Test
	public void testBaseUrl() {
		testapp.setBaseURI("http://146.185.153.244:8080/api/");
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
	}
}