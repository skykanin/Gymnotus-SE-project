package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResultTest {
	
	private Result result;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		result = new Result();
		
		result.setResultID(1);
		result.setExerciseID(1);
		result.setUserID(1);
		result.setDate(new Date(2018,1,1));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetter() {
		Assert.assertEquals(1, (int)result.getResultID());
		Assert.assertEquals(1, (int)result.getExerciseID());
		Assert.assertEquals(1, (int)result.getUserID());
		Assert.assertEquals(new Date(2018,1,1), result.getDate());
	}
}
