package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mfpe.model.ProjectManager;
import com.mfpe.repository.ProjectManagerRepo;

@ExtendWith(MockitoExtension.class)
class ProjectManagerDetailsServiceTest {

	@Mock
	private ProjectManagerRepo projectManagerRepo;

	@InjectMocks
	private ProjectManagerDetailsService projectManagerDetailsService;
	
	@Test
	public void loadUserByUsernameTest() throws UsernameNotFoundException {
		
		String username1 = "user1";
		ProjectManager projectManager = null;
		// test ProjectManager object -- for correct
		projectManager = new ProjectManager();
		projectManager.setId(1);
		projectManager.setName("user1"); // same username
		projectManager.setUsername("user1");
		projectManager.setPassword("abcd1234");
		projectManager.setProjectName("Project1");
		when(projectManagerRepo.getProjectManagerByUserName(username1)).thenReturn(projectManager);
		assertEquals(projectManager, projectManagerDetailsService.loadUserByUsername("user1"));

		// test ProjectManager object -- for wrong
		final String username2 = "invalidUser1";
		projectManager= null;
		when(projectManagerRepo.getProjectManagerByUserName(username2))
				.thenThrow(UsernameNotFoundException.class);
		assertThrows(UsernameNotFoundException.class,
				() -> projectManagerDetailsService.loadUserByUsername(username2));

	}

}
