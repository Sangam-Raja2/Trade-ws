package com.sangam.trading.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Sangam
 */
public class ErrorMessage {

	private HttpStatus status;
	private int code;
	private String message;
	private String developerMessage;

	public ErrorMessage() {
	}

	public ErrorMessage(HttpStatus status, int code, String message, String developerMessage) {

		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
	}

	public ErrorMessage(HttpStatus status, int code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public ErrorMessage(HttpStatus status, int code) {
		super();
		this.status = status;
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
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

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

}
