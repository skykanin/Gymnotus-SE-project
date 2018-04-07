package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.Date;
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

import tdt4140.gr1832.web.dao.data.Comment;
import tdt4140.gr1832.web.dao.data.Exercise;
import tdt4140.gr1832.web.dao.data.ExerciseProgram;
import tdt4140.gr1832.web.server.DatabaseConnection;

public class CommentDAOTest {
	private Connection conn;
	private static int testNum = 0;
	private ExerciseProgramDAO programDAO = new ExerciseProgramDAO();
	private CommentDAO commentDAO = new CommentDAO();
	private UserDAO userDAO = new UserDAO();
	private List<Comment> comments;
	private Comment comment = null;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		comments = new ArrayList<Comment>();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		
		// Creates exercise, and also tests if we succesfullly did so.
		Response response = programDAO.createExerciseProgram("test", "test");
		Assert.assertEquals(200, response.getStatus());
		Response response4 = programDAO.createExerciseProgram("test1", "test1");
		Assert.assertEquals(200, response4.getStatus());
		
		Response response1 = userDAO.createUser("test", "test", "test", "test@test.com", "1234", 1, 18, false, true, true, false);
		Assert.assertEquals(200, response1.getStatus());
		
		Response response2 = commentDAO.createComment(0, 0, "test", "2018-01-01");
		Assert.assertEquals(200, response2.getStatus());
		Response response3 = commentDAO.createComment(0, 1, "test1", "2018-01-02");
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
	public void testUpdateUser() {
		Response response = commentDAO.updateComment(0, 0, 0, "test1", "2018-01-02");
		Assert.assertEquals(200, response.getStatus());
		
		comment = gson.fromJson(commentDAO.getCommentByID(0), Comment.class);
		Assert.assertEquals("test1", comment.getContent());
		Assert.assertEquals(0, (int)comment.getCommentID());
		Assert.assertEquals(0, (int)comment.getProgramID());
		Assert.assertEquals(0, (int)comment.getUserID());
		Assert.assertEquals("2018-01-02", comment.getDate().toString());
	}
	
	@Test
	public void testGetCommentByID() {
		comment = gson.fromJson(commentDAO.getCommentByID(0), Comment.class);
		Assert.assertEquals("test", comment.getContent());
		Assert.assertEquals(0, (int)comment.getCommentID());
		Assert.assertEquals(0, (int)comment.getProgramID());
		Assert.assertEquals(0, (int)comment.getUserID());
		Assert.assertEquals("2018-01-01", comment.getDate().toString());
	}
	
	@Test
	public void testGetCommentByUserID() {
		comments = gson.fromJson(commentDAO.getCommentByUserID(0),  new TypeToken<List<Comment>>(){}.getType());
		Assert.assertEquals(2, comments.size());
		comment = comments.get(1);
		Assert.assertEquals("test1", comment.getContent());
		Assert.assertEquals(1, (int)comment.getCommentID());
		Assert.assertEquals(1, (int)comment.getProgramID());
		Assert.assertEquals(0, (int)comment.getUserID());
		Assert.assertEquals("2018-01-02", comment.getDate().toString());
	}
	
	@Test
	public void testGetCommentByProgramID() {
		comments = gson.fromJson(commentDAO.getCommentByProgramID(1),  new TypeToken<List<Comment>>(){}.getType());
		Assert.assertEquals(1, comments.size());
		comment = comments.get(0);
		Assert.assertEquals("test1", comment.getContent());
		Assert.assertEquals(1, (int)comment.getCommentID());
		Assert.assertEquals(1, (int)comment.getProgramID());
		Assert.assertEquals(0, (int)comment.getUserID());
		Assert.assertEquals("2018-01-02", comment.getDate().toString());
	}
}
