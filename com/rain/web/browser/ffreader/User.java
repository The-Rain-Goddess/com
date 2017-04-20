package com.rain.web.browser.ffreader;

public class User {
	private String username;
	private String password;
	
	public User() {
		
	}

	public static User build(){
		User user = new User();
		user.setUsername("");
		user.setPassword("");
		return user;
	}
	
	public void setUsername(String name){ username = name; }
	
	public void setPassword(String pass){ password = pass; }
}
