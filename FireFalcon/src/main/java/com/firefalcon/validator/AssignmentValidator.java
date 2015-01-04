/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.validator;

import com.firefalcon.model.Assignment;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Mohamed
 */
public class AssignmentValidator implements Validator{
    
    @Override
	public boolean supports(Class clazz) {
		//just validate the Customer instances
		return Assignment.class.isAssignableFrom(clazz);

	}

        @Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "start_date",
				"", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "end_date",
				"", "Field name is required.");
                
                

	}
    
}
