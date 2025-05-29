
package com.mantprev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mantprev.exceptions.UserNotFoundException;


@ControllerAdvice
public class ExceptionsController {
	
	
	@ExceptionHandler(value = {UserNotFoundException.class})
	public ResponseEntity<Object> handlerUserNotFoundException(UserNotFoundException ex){
	/****************************************************************************/
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getErrorMessage());
	}
	
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handlerGenericExceptions(Exception ex){
	/******************************************************************************/
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la ejecución del servicio");
	}
	
	
	
}
