package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.web.dao.data.Feedback;
import tdt4140.gr1832.web.server.DatabaseConnection;

public class FeedbackDAOTest {
	private Connection conn;
	private static int testNum = 0;
	private FeedbackDAO feedbackDAO = new FeedbackDAO();
	private UserDAO userDAO = new UserDAO();
	private List<Feedback> feedbacks;
	private  Feedback feedback = null;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		feedbacks = new ArrayList<Feedback>();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);	
		
		//User
		Response response1 = userDAO.createUser("test", "test", "test", "test@test.com", "1234", 1, 18, false, true, true, false);
		Assert.assertEquals(200, response1.getStatus());
		
		//Trainer 
		Response response2 = userDAO.createUser("test1", "test1", "test1", "test@test.com", "1234", 1, 18, false, true, true, false);
		Assert.assertEquals(200, response2.getStatus());
		
		Response response3 = feedbackDAO.createFeedback(0, 1, "test", "2018-01-01");
		Assert.assertEquals(200, response3.getStatus());
		testNum++;
	}
	
	@After
	public void tearDown() {
		if (conn != null) {
			try {
				conn.close();
			} 
			catch (final SQLException e) { }
		}
	}
	
	@Test
	public void testUpdateFeedback() {
		Response response = feedbackDAO.updateFeedback(0,"test1", "2018-01-02");
		Assert.assertEquals(200, response.getStatus());
		
		feedback = gson.fromJson(feedbackDAO.getFeedbackByID(0), Feedback.class);
		Assert.assertEquals("test1", feedback.getContent());
		Assert.assertEquals(0, (int)feedback.getFeedbackID());
		Assert.assertEquals(0, (int)feedback.getUserID());
		Assert.assertEquals(1, (int)feedback.getTrainerID());
		Assert.assertEquals("2018-01-02", feedback.getDate().toString());
	}
	
	@Test
	public void testGetFeedbackByID() {
		feedback = gson.fromJson(feedbackDAO.getFeedbackByID(0), Feedback.class);
		Assert.assertEquals("test", feedback.getContent());
		Assert.assertEquals(0, (int)feedback.getFeedbackID());
		Assert.assertEquals(0, (int)feedback.getUserID());
		Assert.assertEquals(1, (int)feedback.getTrainerID());
		Assert.assertEquals("2018-01-01", feedback.getDate().toString());
	}
	
	@Test
	public void testGetFeedbackByUserID() {
		feedbacks = gson.fromJson(feedbackDAO.getFeedbackByUserID(0),  new TypeToken<List<Feedback>>(){}.getType());
		Assert.assertEquals(1, feedbacks.size());
		feedback = feedbacks.get(0);
		Assert.assertEquals("test", feedback.getContent());
		Assert.assertEquals(0, (int)feedback.getFeedbackID());
		Assert.assertEquals(0, (int)feedback.getUserID());
		Assert.assertEquals(1, (int)feedback.getTrainerID());
		Assert.assertEquals("2018-01-01", feedback.getDate().toString());
	}
}
