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
		authResponse.setProjectName("Project-1");
		authResponse.setValid(false);
		assertEquals("Dheeraj",authResponse.getName());
		assertEquals("Project-1",authResponse.getProjectName());
		assertEquals(false,authResponse.isValid());
	}
	
	@Test
	public void testConstructorAndToString() {
		AuthenticationResponse authResponse = new AuthenticationResponse("Dheeraj","Project-1",false);
		assertEquals("AuthenticationResponse(name=Dheeraj, projectName=Project-1, isValid=false)",authResponse.toString());
	}

}
