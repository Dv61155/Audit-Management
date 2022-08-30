package com.mfpe.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExceptionHandlerAdviceTest {

	@Autowired
	ExceptionHandlerAdvice exceptionHandlerAdvice;
	
	@Test
	public void contextLoads() throws Exception{
		assertNotNull(exceptionHandlerAdvice);
	}
	
	@Test
	public void Exceptiontest() {
		List<String> dummyList=new ArrayList<>();
		Exception e=new Exception();
		assertEquals(new ResponseEntity<>(dummyList,HttpStatus.FORBIDDEN),exceptionHandlerAdvice.exception(e));
	}


}
