package tdt4140.gr1832.web.dao;

import tdt4140.gr1832.web.dao.data.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
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
	public static HealthDataReport createHealthDataReport(ResultSet rs) throws SQLException {
		HealthDataReport report = new HealthDataReport();
		report.setReportID(rs.getInt("reportID"));
		report.setUserID(rs.getInt("userID"));
		report.setDate(rs.getDate("date"));
		report.setBloodPressure(rs.getInt("bloodPressure"));
		report.setDailySteps(rs.getInt("dailySteps"));
		report.setWeight(rs.getInt("weight"));
		report.setHeight(rs.getInt("height"));
		report.setRestingHeartRate(rs.getInt("restingHeartRate"));
		return report;
	}
	
	private static String createHealthDataReportJson(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		List<HealthDataReport> reports = new ArrayList<HealthDataReport>();
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
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
	public static String getAllHealthDataReports() {

		String query = "select * from HealthDataReport";
		String json = createHealthDataReportJson(query);
		
		return json;
	}
	
	// Get all reports for the person with given user id
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{id}")
	public static String getHealthDataReportByID(@PathParam("id")int id) {
		String query = "select * from HealthDataReport where userID=" + Integer.toString(id);
		String json = createHealthDataReportJson(query); 
		return json;
	}
	
	// Get reports by month of year
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/month/{month}")
	public static String getHealthDataReportByMonth(@PathParam("month")int month) {
		String query = "select * from HealthDataReport where month(date)=" + Integer.toString(month);
		String json = createHealthDataReportJson(query);
		return json;
	}
	
	// Get reports by month of year and user id
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("month_id")
	public static String getHealthDataReportByMonthAndID(@QueryParam("month")int month, @QueryParam("id") int id) {
		String query = "select * from HealthDataReport where month(date)=" + Integer.toString(month) +
				" and userID=" + Integer.toString(id);
		String json = createHealthDataReportJson(query);
		return json;
	}
}
