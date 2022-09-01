package com.mfpe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.mfpe.model.AuthenticationRequest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

	@InjectMocks
	private AuthController authController; // from where we will assert

	@Test
	void authenticateTest() throws Exception {
		AuthenticationRequest user = new AuthenticationRequest("Dheeraj Vishwakarma", "dheeraj", "dhee123", "project");
		assertThrows(Exception.class, () -> {
			ResponseEntity<?> response = authController.generateJwt(user);
			assertEquals(200, response.getStatusCodeValue());
		});
	}

	@Test
	void authenticateTestFailed() {
		AuthenticationRequest user = new AuthenticationRequest("admin", "admin", "pass", "project");
		assertThrows(Exception.class, () -> {
			ResponseEntity<?> response = authController.generateJwt(user);
			assertEquals(403, response.getStatusCodeValue());
		});
	}

	@Test
	void validateTestValidtoken() throws Exception {

		AuthenticationRequest user = new AuthenticationRequest("admin", "admin", "pass", "project");
		assertThrows(Exception.class, () -> {
			ResponseEntity<?> response = authController.generateJwt(user);
		ResponseEntity<?> validity = authController.validateJwt("Bearer "+response);
		assertEquals(true, validity.getBody().toString().contains("true"));
		});
	}

	@Test
	void validateTestInValidtoken() {
		assertThrows(Exception.class, () -> {
			ResponseEntity<?> validity = authController.validateJwt("bearer token");
			assertEquals(true, validity.getBody().toString().contains("false"));
		});	
	}
	
}
