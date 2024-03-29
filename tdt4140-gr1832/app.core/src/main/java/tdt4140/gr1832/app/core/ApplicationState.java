package tdt4140.gr1832.app.core;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class ApplicationState {
    private final String baseURI = "http://146.185.153.244:8080/api/user/";
    private static ShowUserInfoContainer loggedInUser;
    private String window_name;
    private int programCounter = 0;
    
    private static boolean TEST = false;
    public static void setTest(boolean b) {
    	TEST = b;
    }
    
    public ApplicationState(String window_name) {
        this.window_name = window_name;
    }

    public static ShowUserInfoContainer getLoggedInUser() {
        return loggedInUser;
    }

    public void verifyUsername(String username) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI + "user_exists?username=" + username);
        Response response = TEST ? Response.status(200).build() : webTarget.request().get();
        if(response.getStatus() == 404) throw new NotFoundException();
    }

    public boolean verifyPassword(String username, String password) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI + username + "/verify_password?password=" + password); 
        Response response = TEST ? Response.status(200).entity("true").build() :  webTarget.request().get();        	
        return Boolean.parseBoolean(TEST ? (String)response.getEntity() : response.readEntity(String.class));
    }

    public void setCurrentUser(String username) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI + username + "/user_info");
        String test = TEST ? "{\"userID\":1,\"username\":\"testbruker\",\"name\":\"Henrik Giske Fosse\",\"email\":\"henrik@fosse.no\",\"phone\":\"23443443\",\"gender\":0,\"age\":23,\"isAnonymous\":false,\"shareExerciseData\":true,\"shareHealthData\":true,\"isTrainer\":true}" 
        					: webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        loggedInUser = gson.fromJson(test, ShowUserInfoContainer.class);
    }

    public void setWindow(String window_name) {
        this.window_name = window_name;
    }

    public String getWindowName() {
        return window_name;
    }
    
    public void DUMMYsetuser(ShowUserInfoContainer s) {
    	loggedInUser = s;
    }
    
    public String getBaseURI() {
    		return baseURI;
    }
    
    public void increaseProgramCounter() {
    	programCounter++;
    }
    
    public void decreaseProgramCounter() {
    	programCounter--;
    }
    
    public void setProgramCounter(int i) {
    	programCounter = i;
    }
    
    public int getProgramCounter() {
    	return programCounter;
    }
}
