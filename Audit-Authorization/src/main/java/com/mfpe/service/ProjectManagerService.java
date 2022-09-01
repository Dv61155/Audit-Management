package com.mfpe.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mfpe.model.AuthenticationRequest;
import com.mfpe.repository.ProjectManagerRepo;

@Service
public class ProjectManagerService implements UserDetailsService{

	@Autowired
	private ProjectManagerRepo projectManagerRepo;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			AuthenticationRequest user = projectManagerRepo.findById(username).orElse(null);
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} catch (Exception e) {
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
	}
	
}
