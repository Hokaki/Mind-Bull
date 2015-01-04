/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.validator;

import com.firefalcon.model.Exercise;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Mohamed
 */
public class ExerciseValidator implements Validator{
    
    @Override
	public boolean supports(Class clazz) {
		//just validate the Customer instances
		return Exercise.class.isAssignableFrom(clazz);

	}

        @Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",
				"", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"", "Field name is required.");
                
                Exercise exercise = (Exercise) target;
                
                if(exercise.getId()==0){
			errors.rejectValue("id", "", "Input can not be 0.");
		}

	}
    
}
