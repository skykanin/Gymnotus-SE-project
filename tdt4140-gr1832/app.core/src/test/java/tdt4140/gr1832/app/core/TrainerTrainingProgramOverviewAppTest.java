package tdt4140.gr1832.app.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import containers.ExerciseProgramContainer;


public class TrainerTrainingProgramOverviewAppTest {
	ExerciseProgramContainer container; 
	TrainerTrainingProgramOverviewApp testapp;
	
	 
	
	@Before
	public void setUp() {
		container = new ExerciseProgramContainer(11, "Overskrift", "Dette beskriver programmet");
		testapp = new TrainerTrainingProgramOverviewApp();
	}
	
	
	
	@Test
	public void testContainerConstructor() {
		Assert.assertEquals("Overskrift", container.getName());
		Assert.assertEquals( "Dette beskriver programmet", container.getDescription());
		Assert.assertEquals(11, (int)container.getProgramID());
	}
	
	@Test
	public void testGettersApp() {
		testapp.addContainerTocontainerExercisePrograms(container);
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
		Assert.assertEquals(container, testapp.getExerciseProgramContainer(0));
		Assert.assertEquals(1, (int) testapp.getContainerExcerciseProgramLength());
	}
}
