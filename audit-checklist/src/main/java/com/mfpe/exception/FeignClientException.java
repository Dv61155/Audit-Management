package com.mfpe.exception;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Component
@Data
public class FeignClientException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public FeignClientException(String message) {
		super();
		this.message = message;
	}

}
