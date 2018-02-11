package tdt4140.gr1832.app.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("response")
public class Response {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {        
        return "OK\n";
    }
}
