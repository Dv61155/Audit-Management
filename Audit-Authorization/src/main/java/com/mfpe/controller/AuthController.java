package com.mfpe.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.model.AuthenticationRequest;
import com.mfpe.model.AuthenticationResponse;
import com.mfpe.service.JwtService;
import com.mfpe.service.ProjectManagerService;

@RestController
@RequestMapping("/auth")	
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ProjectManagerService projectManagerService;
	
	@Autowired
	private JwtService jwtService;
	
	private static final Logger lOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping(path = "/health")
	public ResponseEntity<Object> healthCheckup() {
		lOGGER.info("Health Check ");
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	// authentication - for the very first login
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateJwt(@Valid @RequestBody AuthenticationRequest request) throws Exception{
		
		ResponseEntity<String> response = null;
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userDetails = projectManagerService.loadUserByUsername(request.getUsername());
		String jwt = jwtService.generateToken(userDetails);
		response = new ResponseEntity<String>(jwt, HttpStatus.OK);
		lOGGER.info("Successfully Authenticated user!");
		return response;
	}
	
	// validate - for every request it validates the user-credentials from the provided Jwt token in Authorization req. header
	@PostMapping("/validate")
	public ResponseEntity<AuthenticationResponse> validateJwt(@RequestHeader("Authorization") String jwt){
		
		AuthenticationResponse authenticationResponse = new AuthenticationResponse("Invalid", false);
		ResponseEntity<AuthenticationResponse> response = null;

		//first remove Bearer from Header
		jwt = jwt.substring(7);
		
		//check token
		lOGGER.info("--------JWT :: "+jwt);
		
		
		// check the jwt is proper or not
		UserDetails user = projectManagerService.loadUserByUsername(jwtService.extractUsername(jwt));

		// now validating the jwt
		try {
			
			if(jwtService.validateToken(jwt, user)) {
				authenticationResponse.setName(user.getUsername());
				authenticationResponse.setValid(true);
				response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
				lOGGER.info("Successfully validated the jwt and sending response back!");
			}
			else {
				response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.FORBIDDEN);
				lOGGER.error("JWT Token validation failed!");
			}
		}catch (Exception e) {
			response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.BAD_REQUEST);
			lOGGER.error("Exception occured whil validating JWT : Exception info : " + e.getMessage());
		}
		lOGGER.info("-------- Exiting /validate");
		return response;
	}
	
}
