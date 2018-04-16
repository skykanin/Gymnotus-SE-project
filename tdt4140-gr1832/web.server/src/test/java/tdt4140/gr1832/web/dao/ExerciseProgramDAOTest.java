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

import tdt4140.gr1832.web.dao.data.ExerciseProgram;
import tdt4140.gr1832.web.server.DatabaseConnection;

public class ExerciseProgramDAOTest {
	private Connection conn;
	private static int testNum = 0;
	private ExerciseProgramDAO programDAO = new ExerciseProgramDAO();
	private ExerciseDAO exerciseDAO = new ExerciseDAO();
	private List<ExerciseProgram> programs;
	private ExerciseProgram program = null;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		programs = new ArrayList<ExerciseProgram>();
		gson = new Gson();
		program = new ExerciseProgram();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		
		// Creates exercise, and also tests if we succesfullly did so.
		Response response = programDAO.createExerciseProgram("test", "test");
		Assert.assertEquals(200, response.getStatus());
		
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
	public void testCreateExerciseProgram() {
		Response response = programDAO.createExerciseProgram("new_test", "new_test");
		Assert.assertEquals(200, response.getStatus());
		
		programs = gson.fromJson(programDAO.getAllExercisePrograms(),  new TypeToken<List<ExerciseProgram>>(){}.getType());
		Assert.assertEquals(2,programs.size());
		
		program = programs.get(1);
		Assert.assertEquals("new_test", program.getDescription());
		Assert.assertEquals("new_test", program.getName());
		
	}
	
	@Test
	public void testGetExerciseProgramID() {
		Response response = programDAO.getExerciseProgramID("test");
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(0, (int)response.getEntity());
	}
	
	@Test
	public void testGetExerciseProgram() {
		program = gson.fromJson(programDAO.getExerciseProgram(0), ExerciseProgram.class);
		Assert.assertEquals("test", program.getName());
		Assert.assertEquals("test", program.getDescription());
	}
	
	@Test
	public void testGetAllExercisePrograms() {
		programs = gson.fromJson(programDAO.getAllExercisePrograms(),  new TypeToken<List<ExerciseProgram>>(){}.getType());
		Assert.assertEquals(1, programs.size());
		
		program = programs.get(0);
		Assert.assertEquals("test", program.getName());
		Assert.assertEquals("test", program.getDescription());
	}
}
