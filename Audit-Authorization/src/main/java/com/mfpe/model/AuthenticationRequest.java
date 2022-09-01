package com.mfpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Component
@Data
@Entity
@Table(name="project_manager")
public class AuthenticationRequest {
	
	@Column(name="name")
	private String name;

	@Id
	@Column(name = "username")
	@NotEmpty
	private String username;
	
	@NotEmpty
	@Column(name ="password")
	private String password;
	
	@Column(name="project_name")
	private String projectName;
	
}
