package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.web.dao.data.Result;
import tdt4140.gr1832.web.server.DatabaseConnection;

public class ResultDAOTest {
	private Connection conn;
	private static int testNum = 0;
	
	private UserDAO userDAO = new UserDAO();
	private ExerciseDAO exerciseDAO = new ExerciseDAO();
	private ExerciseProgramDAO programDAO = new ExerciseProgramDAO();
	private ResultDAO resultDAO = new ResultDAO();
	
	private Result result = null;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		result = new Result();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		
		Response response = userDAO.createUser("test", "test", "test", "test@test.com", "1234", 1, 18, true, true, true, true);
		Assert.assertEquals(200, response.getStatus());
		
		Response response1 = programDAO.createExerciseProgram("test", "test");
		Assert.assertEquals(200, response1.getStatus());
			
		Response response2 = exerciseDAO.createExercise(0, "test", 1, 1, 1, "test");
		Assert.assertEquals(200, response2.getStatus());
		
		Response response3 = resultDAO.createResult(0, 0, 1, "2018-01-01");
		Assert.assertEquals(200, response3.getStatus());
		
		result = gson.fromJson(resultDAO.getResult(0), Result.class);
		//Check if getResult worked
		Assert.assertNotNull(result);
		
		
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
	
	private void verifyEqualResult(Result r1, Result r2) {
		Assert.assertEquals(r1.getDate(), r2.getDate());
		Assert.assertEquals(r1.getExerciseID(), r2.getExerciseID());
		Assert.assertEquals(r1.getResultID(), r2.getResultID());
		Assert.assertEquals(r1.getResultParameter(), r2.getResultParameter());
		Assert.assertEquals(r1.getUserID(), r2.getUserID());
	}
	
	@Test
	public void testCreateResult() {
		Response response = resultDAO.createResult(0, 0, 2, "2018-02-02");
		Assert.assertEquals(200, response.getStatus());
		
		Result new_result = gson.fromJson(resultDAO.getResult(1), Result.class);
		Assert.assertEquals(1, (int)new_result.getResultID());
		Assert.assertEquals(0, (int)new_result.getUserID());
		Assert.assertEquals(0, (int)new_result.getExerciseID());
		Assert.assertEquals(2, (int)new_result.getResultParameter());
		Assert.assertEquals("2018-02-02", new_result.getDate().toString());	
	}

	@Test
	public void testGetResultsByUser() {
		String json = resultDAO.getResultsByUser(0);
		List<Result> results = gson.fromJson(json,new TypeToken<List<Result>>(){}.getType());
		
		Result new_result = results.get(0);
		verifyEqualResult(result, new_result);
	}
	
	@Test
	public void testGetResultsByExercise() {
		String json = resultDAO.getResultsByExercise(0);
		List<Result> results = gson.fromJson(json,new TypeToken<List<Result>>(){}.getType());
		
		Result new_result = results.get(0);
		verifyEqualResult(result, new_result);
	}
	
	@Test
	public void testGetResultsByUserExercise() {
		String json = resultDAO.getResultsByUserExercise(0, 0);
		List<Result> results = gson.fromJson(json,new TypeToken<List<Result>>(){}.getType());
		
		Result new_result = results.get(0);
		verifyEqualResult(result, new_result);
	}
	
	@Test
	public void getResultsByDate() {
		String json = resultDAO.getResultsByDate("2018-01-01");
		List<Result> results = gson.fromJson(json,new TypeToken<List<Result>>(){}.getType());
		
		Result new_result = results.get(0);
		verifyEqualResult(result, new_result);
	}
	
	@Test
	public void testGetResultsByMonth() {
		String json = resultDAO.getResultsByMonth(1);
		List<Result> results = gson.fromJson(json,new TypeToken<List<Result>>(){}.getType());
		
		Result new_result = results.get(0);
		verifyEqualResult(result, new_result);
	}
	
	@Test
	public void testGetResultsByMonthAndDay() {
		String json = resultDAO.getResultsByMonthAndDay(1,1);
		List<Result> results = gson.fromJson(json,new TypeToken<List<Result>>(){}.getType());
		
		Result new_result = results.get(0);
		verifyEqualResult(result, new_result);
	}
	
	@Test
	public void testGetResultsByProgramUser() {
		Response response = exerciseDAO.createExercise(0, "test1", 2, 2, 2, "test1");
		Assert.assertEquals(200, response.getStatus());
		
		Response response1 = resultDAO.createResult(0, 1, 100, "2018-02-02");
		Assert.assertEquals(200, response1.getStatus());
		
		String json = resultDAO.getResultsByProgramUser(0, 0);
		System.out.println(json);
		List<Result> results = gson.fromJson(json,new TypeToken<List<Result>>(){}.getType());
		
		Assert.assertEquals(2, results.size());
		Assert.assertEquals("test1", results.get(1).getDescription());
	}

}
