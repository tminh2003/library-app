package com.myapps.libraryapp_gui.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.myapps.libraryapp_gui.validation.PasswordMatches;
import com.myapps.libraryapp_gui.validation.ValidEmail;

@PasswordMatches
public class UserValidationDetails {
	@NotNull
    @NotEmpty
    private String username;
    
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
    
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
    
}
