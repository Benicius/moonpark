package com.moonpark.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(NoValidContentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDto handleNoValidContentException(NoValidContentException noValidContentException){
		final String messageError = noValidContentException.getMessage();
		return new ResponseDto(messageError);
	}
}
