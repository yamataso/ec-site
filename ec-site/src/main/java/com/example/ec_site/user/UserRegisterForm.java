package com.example.ec_site.user;

public class UserRegisterForm {
	private String userName;
    private String email;
    private String passwords;
    private String confirmPassword;
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
	public String getPassword() {
		return passwords;
	}
	public void setPassword(String password) {
		this.passwords = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}