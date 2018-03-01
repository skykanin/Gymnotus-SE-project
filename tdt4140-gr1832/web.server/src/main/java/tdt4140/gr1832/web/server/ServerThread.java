package tdt4140.gr1832.web.server;

import java.io.IOException;
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
	public static short HTTPS_PORT = 8443;
	public static short HTTP_PORT = 8080;
	private static final String KEYSTORE_PATH = ServerThread.class.getClassLoader().getResource("keystore.jks").toExternalForm();

	private static final String KEYSTORE_MANAGER_PASSWORD = "pugruppe32";
	private static final String KEYSTORE_PASSWORD = "pugruppe32";

	
	private final Server server;
	
	ServerThread(Server server) {
		this.server = server;
	}

    public static ServerThread start() {
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

        try {
            server.start();
            server.join();
        } 
        catch (Exception ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            server.destroy();
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
    		DatabaseConnection.connectToDB();
        start();

        System.in.read();
    }
}
