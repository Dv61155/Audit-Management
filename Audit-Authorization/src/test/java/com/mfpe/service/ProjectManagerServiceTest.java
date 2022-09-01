package com.mfpe.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.repository.ProjectManagerRepo;

@ExtendWith(MockitoExtension.class)
public class ProjectManagerServiceTest {

	@Mock
	private ProjectManagerRepo projectManagerRepo;

	@InjectMocks
	private ProjectManagerService projectManagerService; // the real one to assert

	@Test
	public void testLoadUserByUsernameBadCredentials() {
		assertThatThrownBy(() -> projectManagerService.loadUserByUsername("fake"))
				.isInstanceOf(UsernameNotFoundException.class);
	}

	@Test
	public void testLoadUserByUsernameRightCredentials() {
		UserDetails userDetails = projectManagerService.loadUserByUsername("admin1");
		assertEquals("admin1", userDetails.getUsername());
	}
}
