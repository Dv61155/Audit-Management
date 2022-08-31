package com.mfpe.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Project Name is Required")
	private String projectName;
	
	private boolean isValid;

	
	
}
