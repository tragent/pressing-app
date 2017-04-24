package com.tragent.pressing.model;

public class LoggedInUser {
	private CustomUser user;
	private String token;
	
	public LoggedInUser(CustomUser user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	public CustomUser getUser() {
		return this.user;
	}

	public void setUser(CustomUser user) {
		this.user = user;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
