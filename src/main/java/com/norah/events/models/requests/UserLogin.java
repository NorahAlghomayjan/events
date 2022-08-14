package com.norah.events.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserLogin {

	@Email(message = "Please enter a valid email address")
	@NotEmpty(message = "Please enter an email address")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
