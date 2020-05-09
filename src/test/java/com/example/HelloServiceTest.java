package com.example;

import junit.framework.TestCase;

public class HelloServiceTest extends TestCase {

	public void testSayHello() {
		HelloService service = new HelloService();
		assertEquals("hello", service.sayHello());
	}

	public void testSayHappy() {
		HelloService service = new HelloService();
		assertEquals("HAPPY", service.sayHappy());
	}

}
