package com.mfpe.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.repository.ProjectManagerRepo;

@SpringBootTest
public class ProjectManagerServiceTest {

	@Autowired
	private ProjectManagerService projectManagerService; // the real one to assert

	@Test
	public void testLoadUserByUsernameBadCredentials() {
		assertThatThrownBy(() -> projectManagerService.loadUserByUsername("fake"))
				.isInstanceOf(UsernameNotFoundException.class);
	}

	@Test
	public void testLoadUserByUsernameRightCredentials() {
		UserDetails userDetails = projectManagerService.loadUserByUsername("dheeraj");
		assertEquals("dheeraj", userDetails.getUsername());
	}
}
