package com.mfpe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mfpe.exception.EmptyInputException;
//import com.mfpe.model.AuditResponse;
import com.mfpe.model.AuditResponse;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger("Severity-Exception-Handler-Advice");

	// If Requested HTTP method is not valid
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>("Method not supported", HttpStatus.BAD_REQUEST);
	}

	// If Requested body is empty
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>("Input format is wrong", HttpStatus.BAD_REQUEST);
	}

	// if exception thrown by feign client
	@ExceptionHandler(value = FeignClientException.class)
	public ResponseEntity<Object> handelFeignClientException(FeignClientException e) {
		logger.error(e.getMessage());
		System.out.println("-------" + e.getMessage());
		return new ResponseEntity<Object>("" + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// if headers are not provided
	@ExceptionHandler(value = MissingRequestHeaderException.class)
	protected ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Required header is missing!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = EmptyInputException.class)
	public ResponseEntity<?> handleEmptyInputException(EmptyInputException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
