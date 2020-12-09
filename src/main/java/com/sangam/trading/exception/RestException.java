package com.sangam.trading.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Sangam
 */
@ControllerAdvice
@RestController
public class RestException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ErrorMessage(status, status.value(), error));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		StringBuilder message = new StringBuilder();
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();

		for (ObjectError error : allErrors) {
			message.append(error.getObjectName() + " : ");
			message.append(error.getDefaultMessage() + ";  ");
		}
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
				message.toString(), exceptionAsString);
		return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
		return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, ConstraintViolationException e,
			WebRequest request) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		String message = null;
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			message = constraintViolation.getMessage();
		}
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message,
				exceptionAsString);
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public final ResponseEntity<ErrorMessage> handleUserRestException(UserException exception, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(exception.getHttpStatus(), exception.getCode(),
				exception.getMessage(), exception.getDeveloperMessage());
		return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
	}


}
