package containers;

//
public class ShowCommentsContainer {
	
	Integer commentID;
    Integer userID;
    Integer programID;
    String date;
    String content;
    
    
    
    public ShowCommentsContainer(Integer commentID, Integer userID, Integer programID, String date, String content) {
        this.commentID = commentID;
        this.userID = userID;
        this.programID = programID;
        this.date = date;
        this.content = content;
    }
    
    public Integer getCommentID() {
        return commentID;
    }
   
    public Integer getUserID() {
        return userID;
    }

    public Integer getProgramID() {
        return programID;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getContent() {
        return content;
    }
   
	
	
	
	
	
}
