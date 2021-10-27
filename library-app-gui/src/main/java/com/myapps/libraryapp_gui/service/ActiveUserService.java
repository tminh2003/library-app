package com.myapps.libraryapp_gui.service;

import java.util.ArrayList;
import java.util.List;

public class ActiveUserService {
	public List<String> users;

    public ActiveUserService() {
        users = new ArrayList<String>();
    }
    
    public List<String> getAllActiveUsers(){
    	return users;
    }
    
    public void addActiveUser(String username) {
    	users.add(username);
    }

}
