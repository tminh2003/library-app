package com.myapps.libraryapp_gui.session;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.myapps.libraryapp_gui.service.ActiveUserService;

public class LoggedUser implements HttpSessionBindingListener {

	private String username;
	private ActiveUserService activeUserService;

	public LoggedUser(String username, ActiveUserService activeUserService) {
		this.username = username;
		this.activeUserService = activeUserService;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> users = activeUserService.getAllActiveUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (!users.contains(user.getUsername())) {
			users.add(user.getUsername());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = activeUserService.getAllActiveUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (users.contains(user.getUsername())) {
			users.remove(user.getUsername());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}