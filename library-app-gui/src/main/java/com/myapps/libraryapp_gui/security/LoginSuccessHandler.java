package com.myapps.libraryapp_gui.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
    									HttpServletResponse response,
    									Authentication authentication)
    									throws IOException, ServletException {
		String username = authentication.getName();
		Cookie cookie = new Cookie("username", username);
		
		response.addCookie(cookie);
		super.onAuthenticationSuccess(request, response, authentication);
    }
}
