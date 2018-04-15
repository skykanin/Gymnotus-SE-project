package tdt4140.gr1832.app.core;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import containers.ExerciseProgramContainer;


public class TrainerTrainingProgramOverviewAppTest {
	ExerciseProgramContainer container; 
	TrainerTrainingProgramOverviewApp testapp;
	
	 
	
	@Before
	public void setUp() {
		testapp.setTest(true);
		testapp = new TrainerTrainingProgramOverviewApp();
		testapp.requestExerciseProgramInformation();
		testapp.requestExerciseInformationFromProgramID(1);
		container = new ExerciseProgramContainer(11, "Overskrift", "Dette beskriver programmet");
	}
	
	
	
	@Test
	public void testContainerConstructor() {
		Assert.assertEquals("Overskrift", container.getName());
		Assert.assertEquals( "Dette beskriver programmet", container.getDescription());
		Assert.assertEquals(11, (int)container.getProgramID());
	}
	
	@Test
	public void testGettersApp() {
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
		Assert.assertEquals("test for programbeskrivelse", testapp.getExerciseProgramContainer(0).getDescription());
		Assert.assertEquals(1, (int) testapp.getContainerExcerciseProgramLength());
		Assert.assertEquals("Navn: Benkpress\nAntall sett: 5\nLengde på intervall: 8\nPause mellom settene: 5 min\nØvelsebeskrivelse: test for beskrivelse av ovelse",testapp.getExerciseContainertoString("Benkpress"));
		Assert.assertEquals("Benkpress", testapp.getExerciseContainer(0).getDescription());
		Assert.assertEquals(Arrays.asList("Benkpress"), testapp.getExerciseList());
	}
}
