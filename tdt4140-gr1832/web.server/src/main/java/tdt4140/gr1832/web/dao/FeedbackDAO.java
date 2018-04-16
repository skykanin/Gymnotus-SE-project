package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import tdt4140.gr1832.web.dao.data.Feedback;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/feedback")
public class FeedbackDAO {
	
	public static Feedback createFeedback(ResultSet rs) throws SQLException {
		Feedback f = new Feedback();
		f.setFeedbackID(rs.getInt("feedbackID"));
		f.setUserID(rs.getInt("userID"));
		f.setTrainerID(rs.getInt("trainerID"));
		f.setContent(rs.getString("content"));
		f.setDate(rs.getDate("date"));
		return f;
	}
	
	private static String createFeedbackList(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Feedback feedback= createFeedback(rs);
				feedbacks.add(feedback);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(feedbacks);  
		return json;
	}
	
	private static String createFeedback(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		Feedback feedback = new Feedback();
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				feedback = createFeedback(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(feedback);  
		return json;
	}
	
	public static Response updateColumn(Integer feedbackID, String columnLabel, Object newValue, String type) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;

		String insertUserSQL = "update Feedback set " + columnLabel + "=? where feedbackID=?";
		if(feedbackID != null) {
			try {
				prepared_stmt = conn.prepareStatement(insertUserSQL);
				
				if(type.equals("int")) 			prepared_stmt.setInt(1, (Integer)newValue);
				else if(type.equals("bool")) 	prepared_stmt.setBoolean(1, (Boolean)newValue);
				else if(type.equals("string"))	prepared_stmt.setString(1, (String)newValue);
				
				prepared_stmt.setInt(2, feedbackID);
				System.out.println(prepared_stmt.toString());
				prepared_stmt.executeUpdate();
				status = 200;
			} catch (SQLException e) {
				e.printStackTrace();
				status = 400;
			}
		}
		
		return Response.status(status).build();
	}
	
	@POST
	@Path("/update_feedback")
	public Response updateFeedback(@FormParam("feedback_id") Integer feedbackID, 
			   @FormParam("content") String content,
			   @FormParam("date") String date)
	{
		int status = 400;
		Integer num_rows_affected = 0;
		
		if(feedbackID != null) {
			if(content != null) num_rows_affected += updateColumn(feedbackID, "content", content, "string").getStatus() == 200 ? 1 : 0;
			if(date != null) num_rows_affected += updateColumn(feedbackID, "date", date, "string").getStatus() == 200 ? 1 : 0;
			status = 200;
		}
		
		return Response.status(status).entity(num_rows_affected).build();
	}
	
	@POST
	@Path("/create_feedback")
	public Response createFeedback(@FormParam("user_id") Integer userID,
			   @FormParam("trainer_id") Integer trainerID,
			   @FormParam("content") String content,
			   @FormParam("date") String date)
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;
		
		if(userID == null)
			return Response.status(status).build();

		String query = "insert into Feedback (trainerID, userID, content, date) "
						+ "values (?, ?, ?, ?)";

		// Execute query
		try {
			prepared_stmt = conn.prepareStatement(query);
			
			prepared_stmt.setInt(1, trainerID);
			prepared_stmt.setInt(2, userID);
			prepared_stmt.setString(3, content);
			prepared_stmt.setString(4, date);
			prepared_stmt.executeUpdate();
			status = 200;
		} catch (SQLException e) {
			e.printStackTrace();
			status = 400;
		}
		return Response.status(status).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get_feedback")
	public static String getFeedbackByID(@QueryParam("feedback_id")int id) {
		String query = "select * from Feedback where feedbackID=" + Integer.toString(id);
		String json = createFeedback(query); 	
		return json;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get_feedbacks_from_trainer")
	public static String getFeedbackByTrainer(@QueryParam("trainer_id")int id) {
		String query = "select * from Feedback where trainerID=" + Integer.toString(id);
		String json = createFeedbackList(query); 
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get_feedbacks_from_user")
	public static String getFeedbackByUserID(@QueryParam("user_id")int id) {
		String query = "select * from Feedback where userID=" + Integer.toString(id);
		String json = createFeedbackList(query); 
		return json;
	}
}
