package com.mfpe.exception;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class EmptyInputException extends RuntimeException {
	/**
	 * If you post empty inputs
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyInputException(String message) {
		super(message);
	}
	
	

}
