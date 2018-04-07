package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommentTest {
	
	private Comment comment;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		comment = new Comment();
		comment.setContent("test");
		comment.setDate(new Date(2018,1,1));
		comment.setProgramID(1);
		comment.setUserID(1);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetter() {
		Assert.assertEquals(1, (int)comment.getUserID());
		Assert.assertEquals("test", comment.getContent());
		Assert.assertEquals(new Date(2018,1,1), comment.getDate());
		Assert.assertEquals(1, (int)comment.getProgramID());
		Assert.assertEquals(1, (int)comment.getUserID());
	}
}
