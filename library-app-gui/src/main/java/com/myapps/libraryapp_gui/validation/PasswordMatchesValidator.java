package com.myapps.libraryapp_gui.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.myapps.libraryapp_gui.model.UserDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		UserDTO user = (UserDTO) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}
}