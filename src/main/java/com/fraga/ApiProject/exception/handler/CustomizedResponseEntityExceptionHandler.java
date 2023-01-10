package com.fraga.ApiProject.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fraga.ApiProject.data.error.ErrorMessage;
import com.fraga.ApiProject.exception.RequiredObjectIsNullException;
import com.fraga.ApiProject.exception.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorMessage> handleBadRequestExceptions(Exception ex) {
		ErrorMessage error = new ErrorMessage("Not Found!",HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ErrorMessage> handleRequiredObjectIsNullException(Exception ex) {
		ErrorMessage error = new ErrorMessage("Not Found!",HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
