package tdt4140.gr1832.web.dao;

import tdt4140.gr1832.web.server.DatabaseConnection;

import static org.junit.Assert.assertEquals;

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
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.web.dao.data.HealthDataReport;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseConnection.class)
public class HealthReportDAOTest {
	private Connection conn;
	private static int testNum = 0;
	private HealthDataReportDAO healthDAO = new HealthDataReportDAO();
	private UserDAO userDAO = new UserDAO();
	private List<HealthDataReport> reports;
	private HealthDataReport report = null;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		reports = new ArrayList<HealthDataReport>();
		report = new HealthDataReport();
		userDAO = new UserDAO();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		
		Response response1 = userDAO.createUser("test", "test", "test", "test@test.com", "1234", 1, 18, true, true, true, true);
		Assert.assertEquals(200, response1.getStatus());
		
		// Creates healthdata, and also tests if we succesfullly did so.
		Response response = healthDAO.createHealthData(0, "2018-01-01", 1, 1, 1, 1, 1);
		Assert.assertEquals(200, response.getStatus());
		
		report = ((List<HealthDataReport>)(gson.fromJson(HealthDataReportDAO.getHealthDataReportByID(0), new TypeToken<List<HealthDataReport>>(){}.getType()))).get(0);
		
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
    
    private boolean isEqual(HealthDataReport hdr1, HealthDataReport hdr2) {
    		if(hdr1.getUserID() == hdr2.getUserID() &&
    		   hdr1.getDate().equals(hdr2.getDate())&&
    		   hdr1.getBloodPressure() == hdr2.getBloodPressure() &&
    		   hdr1.getDailySteps() == hdr2.getDailySteps() &&
    		   hdr1.getWeight() == hdr2.getWeight() &&
    		   hdr1.getHeight() == hdr2.getHeight() &&
    		   hdr1.getRestingHeartRate() == hdr2.getRestingHeartRate()
    		   )
    			return true;
    		return false;
    }
    
    @Test 
    public void testCreateFalseHealthData() {
    		Response response = healthDAO.createHealthData(null, null, null, null, null, null, null);
    		Assert.assertEquals(400, response.getStatus());
    		Response response2 = healthDAO.createHealthData(10, "2018-01-01", 1, 1, 1, 1, 1);
    		Assert.assertEquals(400, response2.getStatus());
    }
    
    @Test
    public void testGetAllReports() {
    		String json = HealthDataReportDAO.getAllHealthDataReports();
    		Assert.assertNotNull(json);
    		
    		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
    		
    		Assert.assertNotNull(reports);
    		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    
    @Test
    public void testGetHealthRDataReportByID() {
    		String json = HealthDataReportDAO.getHealthDataReportByID(0);
    		Assert.assertNotNull(json);
    		
    		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
    		
    		Assert.assertNotNull(reports);
    		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    
    @Test
    public void testGetHealthRDataReportByMonth() {
    		String json = HealthDataReportDAO.getHealthDataReportByMonth(1);
    		Assert.assertNotNull(json);
    		
    		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
    		
    		Assert.assertNotNull(reports);
    		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    
    @Test
    public void testGetHealthRDataReportByMonthAndID() {
		String json = HealthDataReportDAO.getHealthDataReportByMonthAndID(1, 0);
		Assert.assertNotNull(json);
		
		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
		
		Assert.assertNotNull(reports);
		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    
    @Test
    public void getHealthDataSortedIDSortedByDateDesc() {
    	Response response = healthDAO.createHealthData(0, "2018-02-02", 1, 1, 1, 1, 1);
    	assertEquals(200, response.getStatus());
		String json = HealthDataReportDAO.getHealthDataSorted(false, 0);
		Assert.assertNotNull(json);
		
		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
		
		Assert.assertNotNull(reports);
		Assert.assertTrue(isEqual(report, reports.get(1)));
    }
}
