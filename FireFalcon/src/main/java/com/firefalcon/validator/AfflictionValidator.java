/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.firefalcon.model.Affliction;


public class AfflictionValidator implements Validator{

    	@Override
	public boolean supports(Class clazz) {
		//just validate the Customer instances
		return Affliction.class.isAssignableFrom(clazz);

	}

        @Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"affliction.description", "Field name is required.");

	}
}
