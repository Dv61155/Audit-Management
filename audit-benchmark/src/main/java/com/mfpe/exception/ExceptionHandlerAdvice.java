package com.mfpe.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger("Benchmark-Exception-Handler-Advice");



	// If headers are missing
	@ExceptionHandler(value = MissingRequestHeaderException.class)
	protected ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Required header is missing!", HttpStatus.BAD_REQUEST);
	}

	//If requested method is not valid
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Method not allowed!", HttpStatus.BAD_REQUEST);
	}

	// if exception thrown by feign client
	@ExceptionHandler(value = FeignClientException.class)
	public ResponseEntity<Object> handelFeignClientException(FeignClientException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<Object>("" + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
