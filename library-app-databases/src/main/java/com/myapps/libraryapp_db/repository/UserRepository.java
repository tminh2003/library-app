
package com.myapps.libraryapp_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapps.libraryapp_db.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
}