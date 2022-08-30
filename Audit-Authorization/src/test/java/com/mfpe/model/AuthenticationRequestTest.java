package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationRequestTest {

	@Test
	public void testGetterAndSetter()
	{
		AuthenticationRequest obj=new AuthenticationRequest();
		obj.setUsername("Dheeraj");
		obj.setPassword("dhee123");
		assertEquals("Dheeraj",obj.getUsername());
		assertEquals("dhee123",obj.getPassword());
		
	}
}
