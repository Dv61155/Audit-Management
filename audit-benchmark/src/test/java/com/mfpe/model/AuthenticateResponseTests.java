package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticateResponseTests {

	
	@Test
	public void testGetterAndSetter()
	{
		AuthenticationResponse obj=new AuthenticationResponse();
		obj.setName("Harsh");;
		obj.setValid(true);;
		assertEquals("Harsh",obj.getName());
		assertEquals(true,obj.isValid());
	}
	
	@Test
	public void testconstructorAndTostring()
	{
		AuthenticationResponse obj=new AuthenticationResponse("Harsh",true);
		assertEquals("AuthenticationResponse(name=Harsh, isValid=true)",obj.toString());
		
		
	}
	
}
