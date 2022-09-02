package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationResponseTest {

	@Test
	public void testGetterAndSetter() {
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setName("Dheeraj");
		authResponse.setValid(false);
		assertEquals("Dheeraj",authResponse.getName());
		assertEquals(false,authResponse.isValid());
	}
	
	@Test
	public void testConstructorAndToString() {
		AuthenticationResponse authResponse = new AuthenticationResponse("Dheeraj",false);
		assertEquals("AuthenticationResponse(name=Dheeraj, isValid=false)",authResponse.toString());
	}

}
