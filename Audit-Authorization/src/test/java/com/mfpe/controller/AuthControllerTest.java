package com.mfpe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.mfpe.model.AuthenticationRequest;
import com.mfpe.model.AuthenticationResponse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

	@Autowired
	private AuthController authController; // from where we will assert

	@Test
	void loginTest() throws Exception {
		AuthenticationRequest user = new AuthenticationRequest("Aditi Debnath", "aditi","aditi123");
		ResponseEntity<String> response = authController.generateJwt(user);
			assertEquals(200, response.getStatusCodeValue());
		
	}

	@Test
	void loginTestFailed() {
		AuthenticationRequest user = new AuthenticationRequest("name", "username","pass");
		Exception e = assertThrows(Exception.class, () -> {
			ResponseEntity<?> response = authController.generateJwt(user);
			assertEquals(403, response.getStatusCodeValue()); // 403 forbidden
		});
	}

	@Test
	void validateTestValidtoken() throws Exception {

		AuthenticationRequest user = new AuthenticationRequest("Aditi Debnath", "aditi","aditi123");
		ResponseEntity<String> response = authController.generateJwt(user);
		String token= response.getBody();
		ResponseEntity<AuthenticationResponse> validity = authController.validateJwt("Bearer "+token);
		assertEquals(true, validity.getBody().isValid());

	}

	@Test
	void validateTestInValidtoken() {
		ResponseEntity<?> validity = authController.validateJwt("bearer token");
		assertEquals(true, validity.getBody().toString().contains("false"));
	}

	@Test
	void testhealth() {
		ResponseEntity<?> healthCheckup = authController.healthCheckup();
		assertEquals(200, healthCheckup.getStatusCodeValue());

	}
}
