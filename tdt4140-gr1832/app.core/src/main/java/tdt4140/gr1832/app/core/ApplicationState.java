package tdt4140.gr1832.app.core;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.sun.tools.corba.se.idl.constExpr.Not;

public class ApplicationState {
    private final String baseURI = "http://146.185.153.244:8080/api/user/";
    private ShowUserInfoContainer loggedInUser;

    private String window_name;

    public ApplicationState(String window_name) {
        this.window_name = window_name;
    }

    public ShowUserInfoContainer getLoggedInUser() {
        return loggedInUser;
    }

    public void verifyUsername(String username) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI + "user_exists?username=" + username);
        Response response = webTarget.request().get();
        if(response.getStatus() == 404) throw new NotFoundException();
    }

    public boolean verifyPassword(String username, String password) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI + username + "/verify_password?password=" + password);
        Response response = webTarget.request().get();
        return Boolean.parseBoolean(response.readEntity(String.class));
    }

    public void setCurrentUser(String username) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURI + username + "/user_info");
        String test = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        loggedInUser = gson.fromJson(test, ShowUserInfoContainer.class);
    }

    public void setWindow(String window_name) {
        this.window_name = window_name;
    }

    public String getWindow() {
        return this.window_name;
    }

}
