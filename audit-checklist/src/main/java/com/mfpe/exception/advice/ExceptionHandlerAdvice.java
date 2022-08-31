package com.mfpe.exception.advice;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mfpe.exception.ElementNotFound;
import com.mfpe.exception.EmptyInputException;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	

	Logger logger = LoggerFactory.getLogger("Benchmark-Exception-Handler-Advice");

	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<?> handelNoSuchElementException(NoSuchElementException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>("No such element available in the database!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmptyInputException.class)
	public ResponseEntity<?> handelEmptyInputException(EmptyInputException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ElementNotFound.class)
	public ResponseEntity<?> handelElementNotFound(ElementNotFound e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Input is empty!", HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Requested http method is not allowed!", HttpStatus.BAD_REQUEST);
	}
	
	
}
