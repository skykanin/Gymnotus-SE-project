package tdt4140.gr1832.app.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void setUp() throws Exception{
		this.user = new User("OlaN", "hamstring","Ola Nordmann", 12, 0,  "ola.nordmann@gmail.com", "41546593");
		
	}
	
	@Test
		public void testGetPhone() {
		assertEquals("41546593", user.getPhone());
			 
		}
	@Test
	public void testSetPhone() {
	user.setPhone("41546593");
	assertTrue("41546593" == user.getPhone());
		 
	}
	@Test
	public void testGetGender() {
	assertEquals(0, user.getGender());
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
	@Test
	public void testSetEmail() {
		try {
			user.setEmail("sdt.tds@gmail.c@om");
			System.out.println("Should have thrown IllegalARgumentException.");
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
	}
	}
	@Test
	public void testGetEmail() {
	assertEquals("ola.nordmann@gmail.com", user.getEmail());
	}
	
	@Test
	public void testSetName() {
		try {
			user.setName("gdsgs7gdsg");
			System.out.println("Should have thrown IllegalARgumentException.");
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}
	}
		
		@Test
		public void testGetName() {
		assertEquals("Ola Nordmann", user.getName());
		}
		
		@Test
		public void testSetUsername() {
			try {
				user.setUsername("gdsgsd gsdg");
				System.out.println("Should have thrown IllegalARgumentException.");
				assert false;
			}catch (IllegalArgumentException e) {
				assert true;
			}
		}
		
		@Test
		public void testGetUsername() {
		assertEquals("OlaN", user.getUsername());
		}
		
		
		@Test
		public void testSetAge() {
			try {
				user.setAge(124);
				System.out.println("Should have thrown IllegalARgumentException.");
				assert false;
			}catch (IllegalArgumentException e) {
				assert true;
			}
		}
		@Test
		public void testGetAge() {
		assertEquals(12, user.getAge());
		}
		
		@Test
		public void testSetPassword() {
			try {
				user.setPassword("gs");
				System.out.println("Should have thrown IllegalARgumentException.");
				assert false;
			}catch (IllegalArgumentException e) {
				assert true;
			}
		}
		@Test
		public void testGetPassword() {
		assertEquals("hamstring", user.getPassword());
		}
		
}
	
	
	
	
	
//	@Test
//	public void testSetMaxPuse() {
//athlete.setMaxPulse(230);
//assertTrue(230 == athlete.getMaxPulse);
		
	//}

	
		
		
		
	

	
	
	
	
