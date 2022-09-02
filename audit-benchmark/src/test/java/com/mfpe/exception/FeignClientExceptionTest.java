package com.mfpe.exception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class FeignClientExceptionTest {

	@Autowired
	private FeignClientException feignClient;
	
	@Test
	public void contextLoads() throws Exception{
		assertNotNull(feignClient);
	}
}
