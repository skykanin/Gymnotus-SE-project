package tdt4140.gr1832.web.dao;

import tdt4140.gr1832.web.dao.data.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/health_data")
public class HealthDataReportDAO {
	
	private HealthDataReport createHealthDataReport(ResultSet rs) throws SQLException {
		HealthDataReport report = new HealthDataReport();
		report.setReportID(rs.getInt("reportID"));
		report.setUserID(rs.getInt("userID"));
		report.setDate(rs.getDate("date"));
		report.setBloodPressure(rs.getInt("bloodPressure"));
		report.setDailySteps(rs.getInt("dailySteps"));
		report.setRestingHeartRate(rs.getInt("restingHeartRate"));
		return report;
	}
	
	private String createHealthDataReportJson(String query) {
		Connection conn = DatabaseConnection.conn;
		
		List<HealthDataReport> reports = new ArrayList<HealthDataReport>();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				HealthDataReport report = createHealthDataReport(rs);
				reports.add(report);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(reports);  
		return json;
	}
	
	//gets all health reports in the database.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllHealthDataReports() {

		String query = "select * from HealthDataReport";
		String json = createHealthDataReportJson(query);
		
		return json;
	}
	
	// Get all reports for the person with given user id
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{id}")
	public String getHealthDataReportByID(@PathParam("id")int id) {
		String query = "select * from HealthDataReport where userID=" + Integer.toString(id);
		String json = createHealthDataReportJson(query); 
		return json;
	}
	
	// Get reports by month of year
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/month/{month}")
	public String getHealthDataReportByMonth(@PathParam("month")int month) {
		String query = "select * from HealthDataReport where month(date)=" + Integer.toString(month);
		String json = createHealthDataReportJson(query);
		return json;
	}
	
	// Get reports by month of year and user id
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("month_id")
	public String getHealthDataReportByMonthAndID(@QueryParam("month")int month, @QueryParam("id") int id) {
		String query = "select * from HealthDataReport where month(date)=" + Integer.toString(month) +
				" and userID=" + Integer.toString(id);
		String json = createHealthDataReportJson(query);
		return json;
	}
}
