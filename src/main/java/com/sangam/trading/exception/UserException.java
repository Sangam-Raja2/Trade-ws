package com.sangam.trading.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Sangam
 */
public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private int code;
	private String message;
	private String developerMessage;

	public UserException(HttpStatus status, int code, String message, String developerMessage) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
	}

	public UserException(HttpStatus status, String developerMessage) {
		this.status = status;
		this.developerMessage = developerMessage;
	}

	public HttpStatus getHttpStatus() {
		return status;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.status = httpStatus;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
