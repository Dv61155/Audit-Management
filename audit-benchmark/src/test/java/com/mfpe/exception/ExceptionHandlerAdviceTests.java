package com.mfpe.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mfpe.model.AuthenticationResponse;

@SpringBootTest
class ExceptionHandlerAdviceTests {

	@Autowired
	ExceptionHandlerAdvice exceptionHandlerAdvice;
	
	@Test
	public void contextLoads() throws Exception{
		assertNotNull(exceptionHandlerAdvice);
	}
	
	@Test
	public void Exceptiontest() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse("Invalid", "Invalid", false);
		Exception e=new Exception();
		assertEquals(new ResponseEntity<>(authenticationResponse,HttpStatus.FORBIDDEN),exceptionHandlerAdvice.exception(e));
	}

}
