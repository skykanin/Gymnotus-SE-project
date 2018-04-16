package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1832.app.containers.ExerciseContainer;
import tdt4140.gr1832.app.containers.ExerciseProgramContainer;
import tdt4140.gr1832.app.containers.ResultContainer;
import tdt4140.gr1832.app.containers.ShowAllUsersContainer;
import tdt4140.gr1832.app.containers.ShowHealthInfoContainer;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class TrainerTrainingProgramExercisesProgramsAppTest {
	ShowUserInfoContainer userContainer;
	ShowUserInfoContainer userContainer1;
	ShowUserInfoContainer userContainer2;
	ShowHealthInfoContainer healthContainer;
	List<ShowHealthInfoContainer> healthContainers = new ArrayList<>();
	ShowAllUsersContainer showAllUserContainer; 
	TrainerTrainingProgramExercisesProgramsApp testapp = new TrainerTrainingProgramExercisesProgramsApp();
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
		healthContainer = new ShowHealthInfoContainer(123, 007, "Mar, 2018", 85, 100, 60, 181, 80, false, true, true);
		
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
		healthContainers.add(healthContainer);
		testapp.setHealthContainers(healthContainers);
		testapp.setTest(true);
	}
	
	@Test
	public void testGetHeights() {
		testapp.requestHealthInformation_ID("1");
		List<Integer> heights = testapp.getHeights();
		Assert.assertNotNull(heights);
		Assert.assertEquals(1, heights.size());
		Assert.assertEquals(187, (int)heights.get(0));
	}
	
	@Test
	public void testUserStuff() {
		testapp.requestAllUserID();
		List<String> names = testapp.getNames();
		Assert.assertNotNull(names);
		Assert.assertEquals(2, names.size());
		Assert.assertEquals("Anonym#1", names.get(1));
		
		List<Integer> userIDS = testapp.requestUserIDsOnExercise(1);
		Assert.assertNotNull(userIDS);
		Assert.assertEquals(1, userIDS.size());
		Assert.assertEquals(1, (int)userIDS.get(0));
	}
	
	@Test
	public void testExerciseStuff() {
		testapp.requestExerciseProgramInformation();
		ExerciseProgramContainer ep = testapp.getExerciseProgramContainer(0);
		Assert.assertNotNull(ep);
		Assert.assertEquals(1, (int)ep.getProgramID());
		
		testapp.addContainerTocontainerExercisePrograms(ep);
		Assert.assertEquals(2, testapp.getContainerExcerciseProgramLength());
		Assert.assertEquals(2, testapp.getSizeResultsOfExercise(1));
		
		List<String> programNames = testapp.getNamesOfPrograms();
		Assert.assertNotNull(programNames);
		Assert.assertEquals(1, programNames.size());
		Assert.assertEquals("Bryst og skuldre", programNames.get(0));
		
		Assert.assertEquals(1, testapp.getProgramIDfromName("Bryst og skuldre"));
	}
	
	@Test
	public void testGetUserIDSOnProgram() {
		List<Integer> ids = testapp.getUserIDsOnProgram("Bryst og skyldre");
		Assert.assertNotNull(ids);
		Assert.assertEquals(1, ids.size());
		Assert.assertEquals(1, (int)ids.get(0));
	}
	
	@Test
	public void testGetExercisesOnAProgram() {
		testapp.getExercisesOnAProgram(1);
		List<ExerciseContainer> ec = testapp.getExContainers();
		
		Assert.assertNotNull(ec);
		Assert.assertEquals(1, ec.size());
		Assert.assertEquals(1, ec.get(0).getExerciseID());
	}
	
	@Test
	public void testGetResultsOfExerciseAndUser() {
		testapp.getResultsOfExcerciseAndUser(1, 1);
		List<ResultContainer> rc = testapp.getResContainers();
		Assert.assertNotNull(rc);
		Assert.assertEquals(1, rc.size());
		Assert.assertEquals(30, rc.get(0).getResultID());
	}
	
	@Test
	public void testProgramStuff() {
		testapp.getResultsOfExercise(1);
		List<String> dates = testapp.getDatesFromList(testapp.getResContainers());
		List<Integer> results = testapp.getResultsFromList(testapp.getResContainers());
		
		Assert.assertNotNull(dates);
		Assert.assertNotNull(results);
		Assert.assertEquals(2, dates.size());
		Assert.assertEquals(2, results.size());
		Assert.assertEquals(71, (int)results.get(1));
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
		testapp.setUserids(userids);
		Assert.assertEquals(exCon, testapp.getExContainers().get(0));
		Assert.assertEquals(resCon, testapp.getResContainers().get(0));
		Assert.assertEquals(exProgCon, testapp.getContainerExercisePrograms().get(0));
		Assert.assertEquals(Arrays.asList(1,2,3), testapp.getUserIDs());
		Assert.assertEquals(Arrays.asList("Mar 1, 2018"), testapp.getDates());
		Assert.assertEquals(Arrays.asList(10), testapp.getResults());
		Assert.assertEquals(Arrays.asList(healthContainer), testapp.getHealthContainers());
		showAllUserContainer.addUserInfo(userContainer);
		testapp.setContainerAllUsers(showAllUserContainer);
		userContainer.setUserId("007");
		Assert.assertEquals("-1", testapp.getIDfromName("feil"));
		Assert.assertEquals("007", testapp.getIDfromName("navn1"));
		
		
		
	}
	
	@Test
	public void testBaseUrl() {
		testapp.setBaseURI("http://146.185.153.244:8080/api/");
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
	}
}