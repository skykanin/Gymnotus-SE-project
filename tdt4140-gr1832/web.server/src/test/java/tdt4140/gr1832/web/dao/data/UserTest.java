package tdt4140.gr1832.web.dao.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserTest {
	
	private User user;
	@Before
	public void setUp() {
		user = new User();
		user.setUserID(1);
		user.setUsername("test");
		user.setEmail("test@test.com");
		user.setGender(0);
		user.setAge(1);
		user.setPhone("123");
		user.setName("test");
	}
	
	@Test
	public void testGetterSetter() {
		Assert.assertEquals(user.getUserID(), 1);
		Assert.assertEquals(user.getUsername(), "test");
		Assert.assertEquals(user.getEmail(), "test@test.com");
		Assert.assertEquals(user.getGender(), 0);
		Assert.assertEquals(user.getAge(), 1);
		Assert.assertEquals(user.getPhone(), "123");
		Assert.assertEquals(user.getName(), "test");
	}
}
