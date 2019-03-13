package org.samsung.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class UserToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String token;
	private String role;

	public UserToken(String username, String token, String role) {
		this.username = username;
		this.token = token;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserToken [username=" + username + ", token=" + token + ", role=" + role + "]";
	}

}
