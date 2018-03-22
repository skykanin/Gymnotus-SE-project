package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExerciseTest {
	private Exercise exercise;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		exercise = new Exercise();
		
		exercise.setExerciseID(1);
		exercise.setDescription("test");
		exercise.setParameterDescription("test");
		exercise.setPauseBetweenSets(1);
		exercise.setProgramID(1);
		exercise.setRepsPerSet(1);
		exercise.setSets(1);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetter() {
		Assert.assertEquals(1, (int)exercise.getExerciseID());
		Assert.assertEquals("test", exercise.getDescription());
		Assert.assertEquals("test", (String)exercise.getParameterDescription());
		Assert.assertEquals(1, (int)exercise.getPauseBetweenSets());
		Assert.assertEquals(1, (int)exercise.getProgramID());
		Assert.assertEquals(1, (int)exercise.getRepsPerSet());
		Assert.assertEquals(1, (int)exercise.getSets());
	}
}
