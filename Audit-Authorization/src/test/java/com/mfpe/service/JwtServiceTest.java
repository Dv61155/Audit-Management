package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfpe.model.AuthenticationRequest;
import com.mfpe.model.ProjectManager;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.repository.ProjectManagerRepo;

@SpringBootTest
class JwtServiceTest {

	ProjectManagerDetails request;

	@InjectMocks
	JwtService jwtService;

	@Mock
	ProjectManagerRepo projectRepo;

	
	@Test
	public void generateTokenTest() {
		request = new ProjectManagerDetails(1,"admin", "admin","pass","project");
		String generateToken = jwtService.generateToken(request);
		assertNotNull(generateToken);
	}

	@Test
	public void validateTokenTest() {
		request = new ProjectManagerDetails(1,"admin", "admin", "pass","project");
		String generateToken = jwtService.generateToken(request);
		Boolean validateToken = jwtService.validateToken(generateToken,request);
		assertEquals(true, validateToken);
	}

}
