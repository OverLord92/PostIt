package com.postit.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User {
	public static final String USER = "USER";
	
	///// treba dodat hibernate validaciju 
	
	@Id
	private String username;

	private String authority;
	private boolean enabled;
	
	@Transient
	private String password;
	
	@Column(name="password")
	private String encodedPassword;

	

	public User() {
	}

	public User(String username, String password, String encodedPassword, boolean enabled, String authority) {
		this.username = username;
		this.password = password;
		this.encodedPassword = encodedPassword;
		this.enabled = enabled;
		this.authority = authority;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", encodedPassword=" + encodedPassword
				+ ", authority=" + authority + ", enabled=" + enabled + "]";
	}
	
	

}
