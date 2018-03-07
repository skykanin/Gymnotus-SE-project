package tdt4140.gr1832.app.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void setUp() throws Exception{
		this.user = new User("OlaN", "fortniteHhamstring","Ola Nordmann", 12, 0,  "ola.nordmann@gmail.com", "41546593");
		
	}
	
	@Test
		public void testGetPhone() {
		assertEquals("41546593", user.getPhone());
			 
		}
	
	@Test
	public void testSetGender() {
		try {
			user.setGender(9);
			System.out.println("Should have thrown IllegalARgumentException.");
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}		
	}
//	@Test
//	public void testSetMaxPuse() {
//athlete.setMaxPulse(230);
//assertTrue(230 == athlete.getMaxPulse);
		
	//}
}
	
		
		
		
	

	
	
	
	

