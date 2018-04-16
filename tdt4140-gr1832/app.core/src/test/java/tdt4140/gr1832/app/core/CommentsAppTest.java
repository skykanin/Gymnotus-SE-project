package tdt4140.gr1832.app.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1832.app.containers.CommentContainer;

public class CommentsAppTest {
	CommentContainer container;
	TrainerTrainingProgramSeeCommentsApp testapp;

		@Before
		public void setUp() {
			testapp.setTest(true);
			container = new CommentContainer(1, 2, 3, "date", "content");
			testapp = new TrainerTrainingProgramSeeCommentsApp();
		}
		
		@Test
		public void testRequestProgramComments() {
			testapp.requestProgramComments(1);
			Assert.assertEquals(1, (int)testapp.getCommentList().size());
		}
		
		@Test
		public void testContainerConstructor() {
			Assert.assertEquals(1, (int)container.getCommentID());
			Assert.assertEquals(2, (int)container.getUserID());
			Assert.assertEquals(3, (int)container.getProgramID());
			Assert.assertEquals("date", container.getDate());
			Assert.assertEquals("content", container.getContent());
		}
		
		@Test
		public void testGettersApp() {
			Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
		}
	}