package com.ets.SecurePharmacy.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//				request.getDescription(false));
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//				request.getDescription(false));
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnableToProcessException.class)
	public final ResponseEntity<Object> handleUserUnableToProcessException(UnableToProcessException ex, WebRequest request) {
		//ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
			//	request.getDescription(false));
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
//				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(value = RecordAlreadyPresentException.class)
    public ResponseEntity<Object> handleBlogAlreadyExistsException(RecordAlreadyPresentException recordAlreadyPresentException) {
		
        return new ResponseEntity<Object>(recordAlreadyPresentException.getMessage(), HttpStatus.CONFLICT);
    }
}
