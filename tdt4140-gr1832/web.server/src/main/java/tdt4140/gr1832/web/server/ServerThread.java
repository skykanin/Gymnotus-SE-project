package tdt4140.gr1832.web.server;

import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerThread {
	public static final short HTTPS_PORT = 8443;
	public static final short HTTP_PORT = 8080;
	private static final String KEYSTORE_PATH = ServerThread.class.getClassLoader().getResource("keystore.jks").toExternalForm();

	private static final String KEYSTORE_MANAGER_PASSWORD = "pugruppe32";
	private static final String KEYSTORE_PASSWORD = "pugruppe32";
	
	private Server server = null;
	
	public Server getServer() {
		return server;
	}
	
	public ServerThread(Server server) {
		this.server = server;
	}
	
    public static ServerThread start(Boolean doConnect) {
        Server server = new Server();
        
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(HTTP_PORT);
               
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());
        
        // Setup HTTP with SSL
        SslContextFactory sslContextFactory = new SslContextFactory(KEYSTORE_PATH);
        sslContextFactory.setKeyStorePassword(KEYSTORE_PASSWORD);
        sslContextFactory.setKeyManagerPassword(KEYSTORE_MANAGER_PASSWORD);
        sslContextFactory.setTrustAll(true);

        ServerConnector sslConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(HTTPS_PORT);
        
        server.setConnectors(new Connector[] { sslConnector, connector });
        
        ServletContextHandler context = 
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
                
        context.setContextPath("/");
        server.setHandler(context);
        
        // This essentially joins Jersey with Jetty.
        ServletHolder serHol = context.addServlet(ServletContainer.class, "/api/*");
        serHol.setInitOrder(1);
        //Here we tell Jersey where to look for resources.
        serHol.setInitParameter("jersey.config.server.provider.packages", 
        							"tdt4140.gr1832.web.dao");
        System.out.println("[INFO] Spawning server on port: " + HTTP_PORT);
        System.out.println("[INFO] Spawning secure server on port: " + HTTPS_PORT);
	
        if(doConnect) {
	        try {
	            server.start();
	            server.join();
	        } 
	        catch (Exception ex) {
	            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		    try(Writer w = new FileWriter("file.log", true)) {
			ex.printStackTrace(new PrintWriter(new BufferedWriter(w)));
			ex.printStackTrace();
		    }
		    catch (Exception e) {
			e.printStackTrace();
		    }
		    System.out.println("[INFO] ERROR date: " + (new java.util.Date()));
	        } 
	        finally {
	            server.destroy();
	        }
        }
        
        return new ServerThread(server);
    }
    
    public boolean close() {
	try {
	    server.stop();
	} 
	catch (Exception e) {
	    e.printStackTrace();
	}
	return server.isStopped();
    }
    
    public static void main(String[] args) throws InterruptedException, IOException {
    	java.util.Date start = new java.util.Date();
	DatabaseConnection.connectToDB();
        start(true);
    	java.util.Date end = new java.util.Date();

	System.out.println("[INFO] Start date: " + start);
	System.out.println("[INFO] End date: " + end);
    }
}
