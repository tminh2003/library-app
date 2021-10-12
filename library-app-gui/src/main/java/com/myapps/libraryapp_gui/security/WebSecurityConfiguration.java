package com.myapps.libraryapp_gui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationSuccessHandler loginSuccessHandler;
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true")
			.successHandler(loginSuccessHandler)
			.and()
			.logout()
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID", "username")
			.logoutSuccessUrl("/");
	}

}