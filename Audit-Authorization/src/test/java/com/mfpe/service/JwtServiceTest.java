package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class JwtServiceTest {

	@Autowired
	JwtService jwtUtil;

	String generateToken = "";
	UserDetails userdetails;

	@BeforeEach
	void beforEach() {
		userdetails = new User("admin1", "admin1", new ArrayList<>());
		generateToken = jwtUtil.generateToken(userdetails);
	}

	@Test
	void generateTokenTest() {
		assertNotNull(generateToken);
	}

	@Test
	void validateTokenTestWithValidToken() {
		Boolean validateToken = jwtUtil.validateToken(generateToken, userdetails);
		assertEquals(true, validateToken);
	}

	@Test
	void validateTokenTestWithInvalidToken() {
		try {
			Boolean validateToken = jwtUtil.validateToken("token", userdetails);
			assertEquals(false, validateToken);
		} catch (Exception e) {

		}
	}

	@Test
	void validateTokenWithNameTest() {
		Boolean validateToken = jwtUtil.validateToken(generateToken, userdetails);
		assertEquals(true, validateToken);
	}

	@Test
	void getUsernameFromTokenTest() {
		assertEquals("admin1", jwtUtil.extractUsername(generateToken));
	}

}
