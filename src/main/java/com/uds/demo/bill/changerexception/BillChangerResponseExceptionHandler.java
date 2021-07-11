package com.uds.demo.bill.changerexception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BillChangerResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {InternalServerError.class})
	public ResponseEntity<String> handleInternalServerError() {
	    return new ResponseEntity<String>("Unexpected Error Occured. Check logs.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value =  {MethodNotAllowedException.class})
	public ResponseEntity<String> handleMethodNotSupported() {
	    return new ResponseEntity<String>("Method does not support action. Choose correct method. e.g. GET/POST/PUT", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	  @ExceptionHandler(value = {ResourceNotFoundException.class}) 
	  public ResponseEntity<String> handleResourceNotFound() { 
		  return new ResponseEntity<String>("Resource/Request not found.", HttpStatus.NOT_FOUND);
	  }
	 
	/*
	 * @ExceptionHandler(value = {NoHandlerFoundException.class}) public
	 * ResponseEntity<String> handleNoHandlerFound() { return new
	 * ResponseEntity<String>("No handler for found.", HttpStatus.NOT_FOUND); }
	 */
}