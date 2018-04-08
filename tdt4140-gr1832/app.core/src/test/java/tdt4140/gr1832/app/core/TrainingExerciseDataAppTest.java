package tdt4140.gr1832.app.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TrainingExerciseDataAppTest {
	TrainingExerciseDataApp testApp;
	ShowExerciseDataContainerFromProgram exerciseContainer1;
	ShowExerciseDataContainerFromProgram exerciseContainer2;
	ShowExerciseDataContainerFromProgram exerciseContainer3;
	ShowHealthInfoContainer healthContainer1;
	ShowHealthInfoContainer healthContainer2;
	ShowHealthInfoContainer healthContainer3;
	
	
	@Before
	public void setup() {
		testApp = new TrainingExerciseDataApp();
		exerciseContainer1 = new ShowExerciseDataContainerFromProgram(11, 1, "Beskrivelse1", 1, "Mar 01, 2018", 111);
		exerciseContainer2 = new ShowExerciseDataContainerFromProgram(2, 1, "Beskrivelse2", 1, "Mar, 04 2018", 22);
		exerciseContainer3 = new ShowExerciseDataContainerFromProgram(3, 1, "Beskrivelse3", 1, "Mar, 11 2018", 33);
		healthContainer1 = new ShowHealthInfoContainer(4, 1, "Mar 02, 2018", 1111, 11, 333, 181, 81, false, true, true);
		healthContainer2 = new ShowHealthInfoContainer(5, 1, "Mar 04, 2018", 2222, 11, 333, 182, 82, false, true, true);
		healthContainer3 = new ShowHealthInfoContainer(6, 1, "Mar 011, 2018", 3333, 33, 333, 183, 83, false, true, true);
	}
	
	
	
	@Test
	public void testExerciseContainer() {
		Assert.assertEquals("Mar 01, 2018", exerciseContainer1.getDate());
		Assert.assertEquals(1, exerciseContainer1.getExerciseID());
		Assert.assertEquals("Beskrivelse1", exerciseContainer1.getExerciseName());
		Assert.assertEquals(11, exerciseContainer1.getResultID());
		Assert.assertEquals(111, exerciseContainer1.getResultParameter());
	}
}
