package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FeedbackTest {
	private Feedback feedback;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		feedback = new Feedback();
		feedback.setContent("test");
		feedback.setTrainerID(0);
		feedback.setDate(new Date(2018,1,1));
		feedback.setUserID(1);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetter() {
		Assert.assertEquals(1, (int)feedback.getUserID());
		Assert.assertEquals("test", feedback.getContent());
		Assert.assertEquals(0, (int)feedback.getTrainerID());
		Assert.assertEquals(new Date(2018,1,1), feedback.getDate());
		Assert.assertEquals(1, (int)feedback.getUserID());
	}
}
