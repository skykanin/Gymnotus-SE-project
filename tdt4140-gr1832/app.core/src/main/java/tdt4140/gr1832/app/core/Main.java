package tdt4140.gr1832.app.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(6034);

        ServletContextHandler ctx = 
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
                
        ctx.setContextPath("/");
        server.setHandler(ctx);
        
        	// This essentially joins Jersey with Jetty.
        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/api/*");
        serHol.setInitOrder(1);
        //Here we tell Jersey where to look for resources.
        serHol.setInitParameter("jersey.config.server.provider.packages", 
        							"tdt4140.gr1832.app.api");

        try {
            server.start();
            server.join();
        } 
        catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            server.destroy();
        }
    }
}