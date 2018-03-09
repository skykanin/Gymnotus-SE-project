package tdt4140.gr1832.web.dao;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import tdt4140.gr1832.web.server.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.Statement;

import tdt4140.gr1832.web.dao.data.HealthDataReport;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseConnection.class)
public class HealthReportDAOTest {
    @Mock
    private Connection c;
    
    @Mock
    private PreparedStatement prep_stmt;
    
    @Mock
    private Statement stmt;
    
    @Mock
    private ResultSet rs;
    
    HealthDataReport report;	
    
    @SuppressWarnings("deprecation")
	@Before
    public void setUp() throws Exception {
    		report = new HealthDataReport(1,1,new Date(2018,1,1),1,1,1,1,1);
    		
        when(c.prepareStatement(any(String.class))).thenReturn(prep_stmt);
        
        when(rs.first()).thenReturn(true);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("reportID")).thenReturn(1);
        when(rs.getInt("userID")).thenReturn(1);
        when(rs.getDate("date")).thenReturn(new Date(2018, 1, 1));
        when(rs.getInt("bloodPressure")).thenReturn(1);
        when(rs.getInt("dailySteps")).thenReturn(1);
        when(rs.getInt("weight")).thenReturn(1);
        when(rs.getInt("height")).thenReturn(1);
        when(rs.getInt("restingHeartRate")).thenReturn(1);
        
        mockStatic(DatabaseConnection.class);
        when(DatabaseConnection.getConnection()).thenReturn(c);
        
        when(prep_stmt.executeQuery()).thenReturn(rs);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
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
    public void testCreateHealthDataReport() {
    		HealthDataReport new_report = null;
    		try {
			new_report = HealthDataReportDAO.createHealthDataReport(rs);
		} 
    		catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
    		Assert.assertNotNull(new_report);
    		Assert.assertTrue(isEqual(report, new_report));
    }
    
    @Test
    public void testGetAllReports() {
    		Gson gson = new Gson();
    		String json = HealthDataReportDAO.getAllHealthDataReports();
    		Assert.assertNotNull(json);
    		
    	    List<HealthDataReport> reports = new ArrayList<HealthDataReport>();
    		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
    		
    		Assert.assertNotNull(reports);
    		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    
    @Test
    public void testGetHealthRDataReportByID() {
    		Gson gson = new Gson();
    		String json = HealthDataReportDAO.getHealthDataReportByID(1);
    		Assert.assertNotNull(json);
    		
    	    List<HealthDataReport> reports = new ArrayList<HealthDataReport>();
    		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
    		
    		Assert.assertNotNull(reports);
    		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    
    @Test
    public void testGetHealthRDataReportByMonth() {
    		Gson gson = new Gson();
    		String json = HealthDataReportDAO.getHealthDataReportByMonth(1);
    		Assert.assertNotNull(json);
    		
    	    List<HealthDataReport> reports = new ArrayList<HealthDataReport>();
    		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
    		
    		Assert.assertNotNull(reports);
    		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    
    @Test
    public void testGetHealthRDataReportByMonthAndID() {
    		Gson gson = new Gson();
    		String json = HealthDataReportDAO.getHealthDataReportByMonthAndID(1, 1);
    		Assert.assertNotNull(json);
    		
    	    List<HealthDataReport> reports = new ArrayList<HealthDataReport>();
    		reports = gson.fromJson(json, new TypeToken<List<HealthDataReport>>(){}.getType());
    		
    		Assert.assertNotNull(reports);
    		Assert.assertTrue(isEqual(report, reports.get(0)));
    }
    

}
