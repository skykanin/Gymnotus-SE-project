package tdt4140.gr1832.web.server;

import org.eclipse.jetty.server.Server;
import static org.mockito.Mockito.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import junit.framework.Assert;

public class ServerThreadTest {

	@Mock
	private ServerThread server = new ServerThread(new Server());

	@Before
	public void startUp() {
		server = new ServerThread(new Server());
	}
	
	@Test
	public void testGetServer() {
		Assert.assertNotNull(server.getServer());
	}
	
	@Test
	public void testCloseServer() {
		Assert.assertTrue(server.close());
		server = new ServerThread(null);
		try {
			server.close();
		}
		catch(NullPointerException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testStart() {
		server.start(false);
	}
}
