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

import tdt4140.gr1832.web.dao.data.Exercise;
import tdt4140.gr1832.web.server.DatabaseConnection;

public class ExerciseDAOTest {
	private Connection conn;
	private static int testNum = 0;
	private ExerciseProgramDAO programDAO = new ExerciseProgramDAO();
	private ExerciseDAO exerciseDAO = new ExerciseDAO();
	private List<Exercise> exercises;
	private Exercise exercise = null;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		exercises = new ArrayList<Exercise>();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		
		// Creates exercise, and also tests if we succesfullly did so.
		Response response = programDAO.createExerciseProgram("test", "test");
		Assert.assertEquals(200, response.getStatus());
		
		Response response1 = exerciseDAO.createExercise(0, "test", 1, 1, 1, "test");
		Assert.assertEquals(200, response1.getStatus());
		
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
	public void testUpdateExercises() {
		Response response = exerciseDAO.updateExercise(0, "test", 1,2,3, "test");
		Assert.assertEquals(200, response.getStatus());
		
		exercise = gson.fromJson(exerciseDAO.getExercise(0), Exercise.class);
		Assert.assertEquals("test", exercise.getDescription());
		Assert.assertEquals(0, (int)exercise.getProgramID());
		Assert.assertEquals(1, (int)exercise.getSets());
		Assert.assertEquals(2, (int)exercise.getRepsPerSet());
		Assert.assertEquals(3, (int)exercise.getPauseBetweenSets());
		Assert.assertEquals("test", (String)exercise.getParameterDescription());
	}
	
	@Test
	public void testGetExercises() {
		exercises = gson.fromJson(exerciseDAO.getExercises(0),  new TypeToken<List<Exercise>>(){}.getType());
		Assert.assertEquals(1, exercises.size());
	}
	
	
	@Test
	public void testGetExercise() {
		exercise = gson.fromJson(exerciseDAO.getExercise(0), Exercise.class);
		Assert.assertEquals("test", exercise.getDescription());
		Assert.assertEquals(0, (int)exercise.getProgramID());
		Assert.assertEquals("test", (String)exercise.getParameterDescription());
		Assert.assertEquals(1, (int)exercise.getPauseBetweenSets());
		Assert.assertEquals(1, (int)exercise.getRepsPerSet());
		Assert.assertEquals(1, (int)exercise.getSets());
	}
	
}
