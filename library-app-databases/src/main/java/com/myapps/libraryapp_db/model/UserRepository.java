package com.myapps.libraryapp_db.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapps.library_app_shared.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByName(String username);
	
}