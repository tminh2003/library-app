package com.myapps.libraryapp_db.util;

import java.util.ArrayList;
import java.util.List;

import com.myapps.library_app_shared.model.UserDTO;
import com.myapps.libraryapp_db.model.User;

public class UserDTOMapper {
	
	public UserDTO toDTO(User user) {
		return new UserDTO(	user.getUsername(),
							user.getEmail(),
							user.getPassword(),
							user.getAuthority());
	}
	
	public List<UserDTO> toDTO(List<User> allUsers){
		
		List<UserDTO> allDTOs= new ArrayList<UserDTO>();
		for(User user : allUsers) {
			allDTOs.add(toDTO(user));
		}
		
		return allDTOs;
	}
	
	public User toEntity(UserDTO userDTO) {
		return new User(userDTO.getUsername(),
						userDTO.getEmail(),
						userDTO.getPassword(),
						userDTO.getAuthority());
	}
}
