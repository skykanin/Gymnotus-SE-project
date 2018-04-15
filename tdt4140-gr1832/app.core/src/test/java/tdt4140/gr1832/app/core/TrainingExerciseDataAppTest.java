package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1832.app.containers.ExerciseProgramContainer;
import tdt4140.gr1832.app.containers.ShowExerciseDataContainerFromProgram;
import tdt4140.gr1832.app.containers.ShowHealthInfoContainer;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;


public class TrainingExerciseDataAppTest {
	
	TrainingExerciseDataApp testApp;
	ShowExerciseDataContainerFromProgram exerciseContainer1;
	ShowExerciseDataContainerFromProgram exerciseContainer2;
	ShowExerciseDataContainerFromProgram exerciseContainer3;
	ShowExerciseDataContainerFromProgram exerciseContainer4;
	ShowExerciseDataContainerFromProgram exerciseContainer5;
	ShowExerciseDataContainerFromProgram exerciseContainer6;
	ShowExerciseDataContainerFromProgram exerciseContainer7;
	ShowUserInfoContainer userContainer;
	ShowHealthInfoContainer healthContainer1;
	ShowHealthInfoContainer healthContainer2;
	ShowHealthInfoContainer healthContainer3;
	List<ShowUserInfoContainer> users;
	ExerciseProgramContainer exerciseProgramContainer1;
	ExerciseProgramContainer exerciseProgramContainer2;
	
	
	@Before
	public void setup() {
		testApp = new TrainingExerciseDataApp();
		testApp.setTestTrue();
		this.users = new ArrayList<>(Arrays.asList(new ShowUserInfoContainer("1", "Passord", "Navn", 20, 0, "Email", "1234", false, true, true,false), new ShowUserInfoContainer("2", "Passord2", "Navn2", 20, 0, "Email2", "12342", true, true, true,false)));
		this.users.get(0).setUserId("1");
		this.users.get(1).setUserId("2");
		userContainer = new ShowUserInfoContainer("Brukernavn", "passord", "navn", 22, 0, "email","1234", false, true, true, false);
		testApp.setUser(userContainer);
		exerciseProgramContainer1 = new ExerciseProgramContainer(1, "Program1", "Jay");
		exerciseProgramContainer2 = new ExerciseProgramContainer(2, "Program2", "Jei");
		exerciseContainer1 = new ShowExerciseDataContainerFromProgram(11, 1, "Beskrivelse1", 1, "mar 1, 2018", 111);
		exerciseContainer2 = new ShowExerciseDataContainerFromProgram(2, 1, "Beskrivelse2", 2, "mar 1, 2018", 22);
		exerciseContainer3 = new ShowExerciseDataContainerFromProgram(3, 1, "Beskrivelse3", 3, "mar 11, 2018", 33);
		exerciseContainer4 = new ShowExerciseDataContainerFromProgram(4, 1, "Beskrivelse4", 4, "mar 14, 2018", 44);
		exerciseContainer5 = new ShowExerciseDataContainerFromProgram(11, 1, "Beskrivelse12", 1, "mar 11, 2018", 112);
		exerciseContainer6 = new ShowExerciseDataContainerFromProgram(2, 1, "Beskrivelse22", 22, "mar 11, 2018", 222);
		exerciseContainer7 = new ShowExerciseDataContainerFromProgram(3, 1, "Beskrivelse32", 32, "mar 11, 2018", 332);
		healthContainer1 = new ShowHealthInfoContainer(4, 1, "mar 1, 2018", 1111, 11, 111, 181, 81, false, true, true);
		healthContainer2 = new ShowHealthInfoContainer(5, 1, "mar 04, 2018", 2222, 22, 222, 182, 82, false, true, true);
		healthContainer3 = new ShowHealthInfoContainer(6, 1, "mar 11, 2018", 3333, 33, 333, 183, 83, false, true, true);
		
		testApp.addContainerExerciseList(Arrays.asList(exerciseContainer3, exerciseContainer4, exerciseContainer5, exerciseContainer6, exerciseContainer7, exerciseContainer1, exerciseContainer2));
		testApp.addContainerHealthList(Arrays.asList(healthContainer1, healthContainer2, healthContainer3));
		testApp.makeResultList();
		
	}
	
	@Test
	public void testTrainingExerciseDataAppSetup() {
		testApp.TrainingExerciseDataAppSetup();
		Assert.assertNotNull(testApp.getUsersInProgram(0));
		Assert.assertEquals(1, testApp.getUsersInProgram(0).size());
	}
	
	@Test
	public void testRequestHealthExercixeDataByProgramUserID() {
		testApp.requestHealthExerciseDataByProgramUserID(1, 1);
		Assert.assertNotNull(testApp.getHealthList());
	}
	
	@Test
	public void testClearResultMap() {
		Assert.assertTrue(testApp.getResultMapSize()> 0); 
		testApp.clearSortedResultMap();
		Assert.assertEquals(0, testApp.getResultMapSize());
	}
	
	@Test
	public void testGetProgram() {
		Assert.assertEquals(null, testApp.getProgram(0));
		testApp.addPrograms(Arrays.asList(exerciseProgramContainer1, exerciseProgramContainer2));
		Assert.assertEquals(2, testApp.getProgramsListSize());
		Assert.assertEquals(exerciseProgramContainer1, testApp.getProgram(0));
		Assert.assertEquals(exerciseProgramContainer2, testApp.getProgram(1));
	}
	
	@Test
	public void testGetUsersInProgram() {
		Assert.assertEquals(null, testApp.getUsersInProgram(0));
		testApp.addUsersInProgram(users);
		Assert.assertEquals(users, testApp.getUsersInProgram(0));
	}
	
	@Test
	public void testMemberInformation() {
		Assert.assertEquals("22", testApp.getAge());
		Assert.assertEquals("Mann", testApp.getGender());
		Assert.assertEquals("22", testApp.getSteps(0));
		Assert.assertEquals("222", testApp.getRestingHR(0));
		Assert.assertEquals("82", testApp.getWeight(0));
		Assert.assertEquals("11", testApp.getSteps(1));
		Assert.assertEquals("111", testApp.getRestingHR(1));
		Assert.assertEquals("81", testApp.getWeight(1));
		Assert.assertEquals("33", testApp.getSteps(2));
		Assert.assertEquals("333", testApp.getRestingHR(2));
		Assert.assertEquals("83", testApp.getWeight(2));
		Assert.assertEquals(Arrays.asList("mar 04, 2018","mar 1, 2018","mar 11, 2018","mar 14, 2018"),testApp.getDates());
		Assert.assertEquals("Ikke spesifisert", testApp.getSteps(3));
		Assert.assertEquals("Ikke spesifisert", testApp.getRestingHR(3));
		Assert.assertEquals("Ikke spesifisert", testApp.getWeight(3));
		testApp.setUser(new  ShowUserInfoContainer("test", "test", "test", 12, 1, "test", "test", false, false, false, false));
		Assert.assertEquals("Kvinne", testApp.getGender());
		testApp.setUser(new  ShowUserInfoContainer("test", "test", "test", 12, 11, "test", "test", false, false, false, false));
		Assert.assertEquals("Ikke spesifisert", testApp.getGender());
	}
	
	@Test
	public void testShareExercise() {
		Assert.assertEquals(true, testApp.userIsSharingExerciseData());
		testApp.setUser(new ShowUserInfoContainer("test", "test", "test", 12, 0, "test", "test", false, false, false, false));
		Assert.assertEquals(false, testApp.userIsSharingExerciseData());
		testApp.setUser(null); 
		Assert.assertEquals(false, testApp.userIsSharingExerciseData());
		
	}
	
	@Test
	public void testGetExercisesAndResultsAndMakeResults() {
		Assert.assertEquals("mar 04, 2018", testApp.getDate(0));
		Assert.assertEquals("mar 1, 2018", testApp.getDate(1));
		Assert.assertEquals("mar 11, 2018", testApp.getDate(2));
		Assert.assertEquals("mar 14, 2018", testApp.getDate(3));
		Assert.assertEquals(null, testApp.getDate(4));
		testApp.getExercises(0);
		Assert.assertEquals(null, testApp.getExercise1());
		Assert.assertEquals(null, testApp.getExercise2());
		Assert.assertEquals(null, testApp.getExercise3());
		Assert.assertEquals(null, testApp.getExercise4());
		Assert.assertEquals(null, testApp.getResult1());
		Assert.assertEquals(null, testApp.getResult2());
		Assert.assertEquals(null, testApp.getResult3());
		Assert.assertEquals(null, testApp.getResult4());
		testApp.getExercises(1);
		Assert.assertEquals("Beskrivelse1", testApp.getExercise1());
		Assert.assertEquals("Beskrivelse2", testApp.getExercise2());
		Assert.assertEquals(null, testApp.getExercise3());
		Assert.assertEquals(null, testApp.getExercise4());
		Assert.assertEquals("111", testApp.getResult1());
		Assert.assertEquals("22", testApp.getResult2());
		Assert.assertEquals(null, testApp.getResult3());
		Assert.assertEquals(null, testApp.getResult4());
		testApp.getExercises(2);
		Assert.assertEquals("Beskrivelse3", testApp.getExercise1());
		Assert.assertEquals("Beskrivelse12", testApp.getExercise2());
		Assert.assertEquals("Beskrivelse22", testApp.getExercise3());
		Assert.assertEquals("Beskrivelse32", testApp.getExercise4());	
		Assert.assertEquals("33", testApp.getResult1());
		Assert.assertEquals("112", testApp.getResult2());
		Assert.assertEquals("222", testApp.getResult3());
		Assert.assertEquals("332", testApp.getResult4());
	}
	
	@Test
	public void testCheckAndMakeAnonymous() {
	List<ShowUserInfoContainer> users = testApp.checkAndMakeAnonymous(this.users);
	Assert.assertEquals("Navn", users.get(0).getName());
	Assert.assertEquals("Anonym#2", users.get(1).getName());
	
	}
	
	
	@Test
	public void testBaseURI () {
		Assert.assertEquals("http://146.185.153.244:8080/api/", testApp.baseURI);
	}
	
	@Test
	public void testExerciseContainer() {
		Assert.assertEquals("mar 1, 2018", exerciseContainer1.getDate());
		Assert.assertEquals(1, exerciseContainer1.getExerciseID());
		Assert.assertEquals("Beskrivelse1", exerciseContainer1.getExerciseName());
		Assert.assertEquals(11, exerciseContainer1.getResultID());
		Assert.assertEquals(111, exerciseContainer1.getResultParameter());
	}
	
}
