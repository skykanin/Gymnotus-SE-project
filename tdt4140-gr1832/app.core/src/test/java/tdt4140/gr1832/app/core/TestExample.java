package tdt4140.gr1832.app.core;

import junit.framework.TestCase;

public class TestExample extends TestCase {
	private int a;
	private int b;
	public void setup() {
		a = 5;
		b = 5;
	}
	
	public void test() {
		TestCase.assertEquals(a, b);
	}
	
	public void tearDown() {
		System.out.println("jepp");
	}

}
