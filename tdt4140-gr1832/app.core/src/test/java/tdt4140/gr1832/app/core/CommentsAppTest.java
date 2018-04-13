package tdt4140.gr1832.app.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommentsAppTest {
	ShowCommentsContainer container;
	CommentsApp testapp;

	
			 
		@Before
		public void setUp() {
			container = new ShowCommentsContainer(1, 2, 3, "date", "content");
			//Integer commentID, Integer userID, Integer programID, String date, String content
			testapp = new CommentsApp();
			
		}
		
		@Test
		//OK
		public void testContainerConstructor() {
			Assert.assertEquals(1, (int)container.getCommentID());
			Assert.assertEquals( 2, (int)container.getUserID());
			Assert.assertEquals(3, (int)container.getProgramID());
			Assert.assertEquals("date", container.getDate());
			Assert.assertEquals("content", container.getContent());
			
		}
		
		@Test
		public void testGettersApp() {
			Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
			
		}
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

