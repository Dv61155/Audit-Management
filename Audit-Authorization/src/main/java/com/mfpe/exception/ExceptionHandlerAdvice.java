package com.mfpe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger("Auth-Exception-Handler-Advice");

	// For bad credentials
	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {

		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Given credentials are wrong", HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			logger.error(error.getDefaultMessage());
		});
		return new ResponseEntity<Object>("Give Inputs in proper-format", HttpStatus.FORBIDDEN);

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Give Inputs in proper-format!", HttpStatus.BAD_REQUEST);
	}

	// If input details are wrong
	@ExceptionHandler(value = ProjectManagerNotFoundException.class)
	public ResponseEntity<?> handelProjectManagerNotFoundException(ProjectManagerNotFoundException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// If Requested HTTP method is not valid
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Requested http method is not allowed!", HttpStatus.BAD_REQUEST);
	}

	// If Jwt token is not valid!
	@ExceptionHandler(value = JsonParseException.class)
	protected ResponseEntity<Object> handleJsonParseException(JsonParseException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Jwt token is not valid!", HttpStatus.BAD_REQUEST);
	}

	// If Jwt token is not valid!
	@ExceptionHandler(value = SignatureException.class)
	protected ResponseEntity<Object> handleSignatureException(SignatureException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Jwt token is not valid!", HttpStatus.BAD_REQUEST);
	}

	// if Jwt token is expired!
	@ExceptionHandler(value = ExpiredJwtException.class)
	protected ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Jwt token is expired!", HttpStatus.BAD_REQUEST);
	}

	// if headers are not provided
	@ExceptionHandler(value = MissingRequestHeaderException.class)
	protected ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Required header is missing!", HttpStatus.BAD_REQUEST);
	}

}
