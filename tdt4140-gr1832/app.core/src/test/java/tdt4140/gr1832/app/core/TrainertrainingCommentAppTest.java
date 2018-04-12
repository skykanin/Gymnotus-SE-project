package tdt4140.gr1832.app.core;

import java.awt.Container;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TrainertrainingCommentAppTest {
	ExerciseProgramContainer programContainer1;
	ExerciseProgramContainer programContainer2;
	FeedbackContainer feedbackContainer1;
	FeedbackContainer feedbackContainer2;
	CommentContainer commentContainer1;
	CommentContainer commentContainer2;
	ShowUserInfoContainer userContainer1;
	ShowUserInfoContainer userContainer2;
	TrainerTrainingCommentApp testApp;
	
	
	@Before
	public void setup() {
		testApp = new TrainerTrainingCommentApp();
		programContainer1 = new ExerciseProgramContainer(1, "Program1", "Beskrivelse1");
		programContainer2 = new ExerciseProgramContainer(2, "Program2", "Beskrivelse2");
		feedbackContainer1 = new FeedbackContainer(1, 1, 11, "Mar 01, 2018", "Feedback1");
		feedbackContainer2 = new FeedbackContainer(2, 1, 2, "Mar 04, 2018", "Feedback2");
		commentContainer1 = new CommentContainer(1, 1, 1, "Mar 03, 2018", "Comment1" );
		commentContainer2 = new CommentContainer(2, 1, 2, "Mar 04, 2018", "Comment2");
		userContainer1 = new ShowUserInfoContainer("brukernavnT", "123456", "trener", 22, 0, "email@email.com", "99887766", false, true, true, true);
		userContainer2 = new ShowUserInfoContainer("brukernavnM", "123456", "Medlem", 22, 0, "email@email.com", "99887766", false, true, true, false);
		userContainer1.setUserId("1");
		userContainer2.setUserId("2");
	}
	
	@Test
	public void testCommentFunctionality() {
		testApp.getCommentsFromTrainer().add(commentContainer1);
		testApp.getCommentsFromTrainer().add(commentContainer2);
		Assert.assertEquals(2, testApp.getCommentId("Mar 04, 2018", "Comment2"));
		Assert.assertEquals(1, testApp.getCommentId("Mar 03, 2018", "Comment1"));
		Assert.assertEquals(-1, testApp.getCommentId("Mar 04, 2018", "C"));
		Assert.assertEquals(-1, testApp.getCommentId("Mar 01, 2018", "Comment1"));
		
	}
	
	@Test
	public void testFeedbackFunctionality() {
		testApp.getFeedbacksFromTrainer().add(feedbackContainer1);
		testApp.getFeedbacksFromTrainer().add(feedbackContainer2);
		Assert.assertEquals(2, testApp.getFeedbackId("Mar 04, 2018", "Feedback2"));
		Assert.assertEquals(-1, testApp.getFeedbackId("Mar 14, 2018", "Feedback2"));
		Assert.assertEquals(-1, testApp.getFeedbackId("Mar 04, 2018", "Feedback"));
		Assert.assertEquals(1, testApp.getFeedbackId("Mar 01, 2018", "Feedback1"));
	}
	
	@Test
	public void testUsersFunctionality() {
		testApp.getUsers().put(1, "trener");
		testApp.getUsers().put(2, "Medlem");
		Assert.assertEquals("trener", testApp.getUserById(1));
		Assert.assertEquals("Medlem", testApp.getUserById(2));
		Assert.assertEquals(Arrays.asList("trener", "Medlem"), testApp.getUserNames());
		Assert.assertEquals(1, testApp.getIdFromUsername("trener"));
		Assert.assertEquals(-1, testApp.getIdFromUsername(null));
		Assert.assertEquals(-1, testApp.getIdFromUsername("Trener"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFromDate() {
		Date date = new Date();
		date.setMonth(3);
		date.setYear(118);
		date.setDate(2);
		Assert.assertEquals("2018-04-02", testApp.fromDate(date));
	}
	
	
	@Test
	public void testProgramsFunctionality() {
		testApp.getPrograms().put(programContainer1.getProgramID(), programContainer1.getName());
		testApp.getPrograms().put(programContainer2.getProgramID(), programContainer2.getName());
		Assert.assertEquals("Program2", testApp.getProgramById(2));
		Assert.assertEquals("Program1", testApp.getProgramById(1));
		Assert.assertEquals(1, testApp.getIdFromProgramName("Program1"));
		Assert.assertEquals(-1, testApp.getIdFromProgramName("Program"));
		Assert.assertEquals(Arrays.asList("Program1", "Program2"),testApp.getProgramNames());
	}
	
	@Test
	public void testFeedbackContainer() {
		Assert.assertEquals(1, ((int)feedbackContainer1.getFeedbackID()));
		Assert.assertEquals(1, ((int)feedbackContainer1.getTrainerID()));
		Assert.assertEquals(11, ((int)feedbackContainer1.getUserID()));
		Assert.assertEquals("Mar 01, 2018", feedbackContainer1.getDate());
		Assert.assertEquals("Feedback1", feedbackContainer1.getContent());
	}
	
	@Test
	public void testCommentContainer() {
		Assert.assertEquals(1, ((int)commentContainer1.getCommentID()));
		Assert.assertEquals(1, ((int)commentContainer1.getProgramID()));
		Assert.assertEquals(1, ((int)commentContainer1.getUserID()));
		Assert.assertEquals("Mar 03, 2018", commentContainer1.getDate());
		Assert.assertEquals("Comment1", commentContainer1.getContent());
	}
	
}
