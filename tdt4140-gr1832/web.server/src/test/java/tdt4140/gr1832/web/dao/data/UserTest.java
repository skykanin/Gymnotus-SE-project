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
		user.setIsTrainer(true);
	}
	
	@Test
	public void testGetterSetter() {
		Assert.assertEquals(1, user.getUserID());
		Assert.assertEquals("test", user.getUsername());
		Assert.assertEquals("test@test.com",  user.getEmail());
		Assert.assertEquals(0, user.getGender());
		Assert.assertEquals(1, user.getAge());
		Assert.assertEquals("123", user.getPhone());
		Assert.assertEquals("test", user.getName());
		Assert.assertEquals(true, user.getIsTrainer());
	}
}
