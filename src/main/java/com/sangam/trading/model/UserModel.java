package com.sangam.trading.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Sangam
 */
public class UserModel {

	private long userId;
	@NotBlank(message = "userName is Mandatory")
	@NotNull(message = "userName Can't be Null")
	@Size(min = 2, message = "first Name must have atleast two characters")
	private String userName;

	@NotBlank(message = "email is Mandatory")
	@NotNull(message = "email Can't be Null")
	@Email
	private String email;

	@NotBlank(message = "address is Mandatory")
	@NotNull(message = "address Can't be Null")
	private String address;

	public UserModel(long userId, String userName, String email, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.address = address;

	}
	public UserModel() {
		
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
