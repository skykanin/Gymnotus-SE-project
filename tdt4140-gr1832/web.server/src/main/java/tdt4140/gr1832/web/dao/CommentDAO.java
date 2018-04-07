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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import tdt4140.gr1832.web.dao.data.Comment;
import tdt4140.gr1832.web.dao.data.HealthDataReport;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/comment")
public class CommentDAO {
	
	public static Comment createComment(ResultSet rs) throws SQLException {
		Comment c = new Comment();
		c.setCommentID(rs.getInt("commentID"));
		c.setUserID(rs.getInt("userID"));
		c.setProgramID(rs.getInt("programID"));
		c.setContent(rs.getString("content"));
		c.setDate(rs.getDate("date"));
		return c;
	}
	
	private static String createCommentList(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		List<Comment> comments = new ArrayList<Comment>();
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Comment comment = createComment(rs);
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(comments);  
		return json;
	}
	
	private static String createComment(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		Comment comment = new Comment();
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				comment = createComment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(comment);  
		return json;
	}
	
	public static Response updateColumn(Integer commentID, String columnLabel, Object newValue, String type) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;

		String insertUserSQL = "update UserCommentOnExerciseprogram set " + columnLabel + "=? where commentID=?";
		if(commentID != null) {
			try {
				prepared_stmt = conn.prepareStatement(insertUserSQL);
				
				if(type.equals("int")) 			prepared_stmt.setInt(1, (Integer)newValue);
				else if(type.equals("bool")) 	prepared_stmt.setBoolean(1, (Boolean)newValue);
				else if(type.equals("string"))	prepared_stmt.setString(1, (String)newValue);
				
				prepared_stmt.setInt(2, commentID);
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
	@Path("/update_comment")
	public Response updateComment(@FormParam("comment_id") Integer commentID, 
			   @FormParam("content") String content,
			   @FormParam("date") String date)
	{
		int status = 400;
		Integer num_rows_affected = 0;
		
		if(commentID != null) {
			if(content != null) num_rows_affected += updateColumn(commentID, "content", content, "string").getStatus() == 200 ? 1 : 0;
			if(date != null) num_rows_affected += updateColumn(commentID, "date", date, "string").getStatus() == 200 ? 1 : 0;
			status = 200;
		}
		
		return Response.status(status).entity(num_rows_affected).build();
	}
	
	@POST
	@Path("/create_comment")
	public Response createComment(@FormParam("user_id") Integer userID,
			   @FormParam("program_id") Integer programID,
			   @FormParam("content") String content,
			   @FormParam("date") String date)
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;
		
		if(userID == null && programID == null)
			return Response.status(status).build();

		String query = "insert into UserCommentOnExerciseprogram (userID, programID, content, date) "
						+ "values (?, ?, ?, ?)";

		// Execute query
		try {
			prepared_stmt = conn.prepareStatement(query);
			
			prepared_stmt.setInt(1, userID);
			prepared_stmt.setInt(2, programID);
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
	@Path("get_comment")
	public static String getCommentByID(@QueryParam("comment_id")int id) {
		String query = "select * from UserCommentOnExerciseprogram where commentID=" + Integer.toString(id);
		String json = createComment(query); 
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get_comments_from_program")
	public static String getCommentByProgramID(@QueryParam("program_id")int id) {
		String query = "select * from UserCommentOnExerciseprogram where programID=" + Integer.toString(id);
		String json = createCommentList(query); 
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get_comments_from_user")
	public static String getCommentByUserID(@QueryParam("user_id")int id) {
		String query = "select * from UserCommentOnExerciseprogram where userID=" + Integer.toString(id);
		String json = createCommentList(query); 
		return json;
	}
}
