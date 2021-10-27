package com.myapps.libraryapp_gui.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.myapps.libraryapp_gui.service.ActiveUserService;
import com.myapps.libraryapp_gui.session.LoggedUser;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Autowired
	private ActiveUserService activeUserService;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
    									HttpServletResponse response,
    									Authentication authentication)
    									throws IOException, ServletException {
		HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("username", authentication.getName());
        }
		super.onAuthenticationSuccess(request, response, authentication);
    }
}
