package tdt4140.gr1832.web.dao.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExerciseProgramTest {
	private ExerciseProgram exerciseProgram;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		exerciseProgram = new ExerciseProgram();
		
		exerciseProgram.setDescription("test");
		exerciseProgram.setName("test");
		exerciseProgram.setProgramID(1);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetter() {
		Assert.assertEquals("test", exerciseProgram.getDescription());
		Assert.assertEquals("test", (String)exerciseProgram.getName());
		Assert.assertEquals(1, (int)exerciseProgram.getProgramID());
	}
}
