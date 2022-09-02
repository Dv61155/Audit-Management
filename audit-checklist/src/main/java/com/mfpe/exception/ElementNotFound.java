/**
 * 
 */
package com.mfpe.exception;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rohit
 *
 */
@Component
@Data
@NoArgsConstructor
public class ElementNotFound  extends RuntimeException{

	/**
	 * if
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public ElementNotFound(String message) {
		super(message);
	}
	
	
   
}
