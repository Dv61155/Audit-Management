package com.mfpe.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger("Checklist-Exception-Handler-Advice");
	List<String> dummyList = new ArrayList<>();

	// here it handles if any exception occurs during validation...
	// we we send a Invalid response to the app if exception occurs

	// If Inputs were provided wrong
	@ExceptionHandler(value = ElementNotFound.class)
	public ResponseEntity<?> handelElementNotFound(ElementNotFound e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// If Requested body is empty
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Input is empty!", HttpStatus.BAD_REQUEST);
	}

	// If Requested HTTP method is not valid
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Requested http method is not allowed!", HttpStatus.BAD_REQUEST);
	}

	// If inputs are empty
	@ExceptionHandler(value = EmptyInputException.class)
	protected ResponseEntity<?> handelEmptyInputException(EmptyInputException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// if headers are not provided
	@ExceptionHandler(value = MissingRequestHeaderException.class)
	protected ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<Object>("Required header is missing!", HttpStatus.BAD_REQUEST);
	}

	// if exception thrown by feign client
	@ExceptionHandler(value = FeignClientException.class)
	public ResponseEntity<Object> handelFeignClientException(FeignClientException e) {
		logger.error(e.getMessage());
		System.out.println("-------"+e.getMessage());
		return new ResponseEntity<Object>("" + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			logger.error(error.getDefaultMessage());
		});
		return new ResponseEntity<Object>("Give Inputs in proper-format", HttpStatus.FORBIDDEN);

	}

//	// If No such data available in the database
//	@ExceptionHandler(value = NoSuchElementException.class)
//	protected ResponseEntity<?> handelNoSuchElementException(NoSuchElementException e) {
//		logger.error(e.getMessage());
//		return new ResponseEntity<String>("No such element available in the database!", HttpStatus.NOT_FOUND);
//	}

}
