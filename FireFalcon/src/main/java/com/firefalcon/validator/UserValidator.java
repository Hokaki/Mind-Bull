/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.validator;

import com.firefalcon.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Mohamed
 */
public class UserValidator implements Validator{
    
        @Override
	public boolean supports(Class clazz) {
		//just validate the Customer instances
		return User.class.isAssignableFrom(clazz);

	}

        @Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"user.firstname", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"user.lastname", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"user.username", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"user.password", "Field name is required.");

	}
    
}
