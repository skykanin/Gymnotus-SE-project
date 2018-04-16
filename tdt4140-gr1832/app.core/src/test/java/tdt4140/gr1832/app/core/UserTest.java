package tdt4140.gr1832.app.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserTest {
	
	private User user;
	
	@Before
	public void setUp() throws Exception{ 
		this.user = new User();
		this.user = new User("OlaN", "hamstring","Ola Nordmann", 12, 0,  "ola.nordmann@gmail.com", "41546593");
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals("Ola Nordmann" + "OlaN" + 12 + 0 + "hamstring", user.toString());
	}
	@Test
	public void testUserType() {
		user.setUserType("bruker");
		Assert.assertEquals("bruker", user.getBrukerType());
		user.setUserType("PT");
		Assert.assertEquals("PT", user.getBrukerType());
	}
	
	@Test
	public void testUserID() {
		user.setUserID("11");
		Assert.assertEquals("11", user.getUserID());
	}
	
	@Test
	public void testGetPhone() {
		assertEquals("41546593", user.getPhone());
	}
	
	@Test
	public void testSetPhone() {
		user.setPhone("41546593");
		assertTrue("41546593" == user.getPhone());
		try {
			user.setPhone("sdfsssds");
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}
		try {
			user.setPhone("");
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}
		try {
			user.setPhone("9");
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}	 
	}
	@Test
	public void testGetGender() {
		assertEquals(0, user.getGender());
	}
	
	
		 
	@Test
	public void testSetGender() {
		try {
			user.setGender(9);
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}
		user.setGender(1);
		Assert.assertEquals(1, user.getGender());
		user.setGender(2);
		Assert.assertEquals(2, user.getGender());
		
	}
	@Test
	public void testSetEmail() {
		try {
			user.setEmail("sdt.tds@gmail.c@om");
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}
		try {
			user.setEmail("stds");
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
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}
		try {
			user.setName("");
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
			assert false;
		}catch (IllegalArgumentException e) {
			assert true;
		}
		try {
			user.setUsername("");
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
	public void testEmail() {
		Assert.assertEquals("ola.nordmann@gmail.com", user.getEmail());
	}
		
	@Test
	public void testSetAge() {
		try {
			user.setAge(124);
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
