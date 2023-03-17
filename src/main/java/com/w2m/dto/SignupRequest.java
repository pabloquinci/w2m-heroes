package com.w2m.dto;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class SignupRequest {

	@NotBlank(message = "El username no puede estar en blanco")
	private String username;
	@NotBlank(message = "La password no puede estar en blanco")
	private String password;

	@NotBlank(message = "El email no puede estar en blanco")
	private String email;
	@NotNull
	private Set<String> role;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
