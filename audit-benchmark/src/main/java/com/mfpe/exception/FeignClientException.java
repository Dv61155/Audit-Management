package com.mfpe.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@ResponseStatus
public class FeignClientException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public FeignClientException(String message) {
		super(message);
	}

}
