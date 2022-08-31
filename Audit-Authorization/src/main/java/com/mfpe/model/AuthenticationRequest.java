package com.mfpe.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
public class AuthenticationRequest {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
