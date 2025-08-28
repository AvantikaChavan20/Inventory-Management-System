package com.product.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<Exception_Response> handleBusNotFoundException(ProductNotFoundException ex)
	{
		Exception_Response exp = new Exception_Response(LocalDate.now(),ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Exception_Response>(exp,HttpStatus.NOT_FOUND);
	}
}
