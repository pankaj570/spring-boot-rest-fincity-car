package com.fincity.car.model;

public class LoginUser {

	private Long id;
	private String userName;
	private String uaserPassword;
	private String role;
	

	public LoginUser() {
		super();
	}

	public LoginUser(String userName, String uaserPassword) {
		super();
		this.userName = userName;
		this.uaserPassword = uaserPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUaserPassword() {
		return uaserPassword;
	}

	public void setUaserPassword(String uaserPassword) {
		this.uaserPassword = uaserPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
