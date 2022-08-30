package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationResponseTest {

	@Test
	public void testGetterAndSetter()
	{
		AuthenticationResponse obj=new AuthenticationResponse();
		obj.setName("Harsh");;
		obj.setProjectName("audit-authenticate");;
		obj.setValid(true);;
		assertEquals("Harsh",obj.getName());
		assertEquals("audit-authenticate",obj.getProjectName());
		assertEquals(true,obj.isValid());
	}
	
	@Test
	public void testconstructorAndTostring()
	{
		AuthenticationResponse obj=new AuthenticationResponse("Harsh","audit-authenticate",true);
		assertEquals("AuthenticationResponse(name=Harsh, projectName=audit-authenticate, isValid=true)",obj.toString());
	}
}
