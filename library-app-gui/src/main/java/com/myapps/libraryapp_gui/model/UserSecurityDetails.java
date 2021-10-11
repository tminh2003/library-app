package com.myapps.libraryapp_gui.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurityDetails implements UserDetails {
	private String username;
	private String password;
	private ArrayList<SimpleGrantedAuthority> authorities;

	public UserSecurityDetails(String username, String password, String... authorities) {
		this.username = username;
		this.password = password;
		this.authorities = new ArrayList<SimpleGrantedAuthority>();
		for(String s : authorities) {
			this.authorities.add(new SimpleGrantedAuthority(s));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
