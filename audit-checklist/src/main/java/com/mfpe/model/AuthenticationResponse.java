package com.mfpe.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
	@NotEmpty(message = "Name is required")
	private String name;
	
	private boolean isValid;

	
	
}
