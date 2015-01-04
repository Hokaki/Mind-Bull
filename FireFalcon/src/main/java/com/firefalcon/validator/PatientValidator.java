/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.validator;

import com.firefalcon.model.Patient;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Mohamed
 */
public class PatientValidator implements Validator{
    
    @Override
	public boolean supports(Class clazz) {
		//just validate the Customer instances
		return Patient.class.isAssignableFrom(clazz);

	}

        @Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bsn",
				"patient.bsn", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"patient.firstName", "Field name is required.");
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"patient.lastName", "Field name is required.");
                
                Patient patient = (Patient) target;
                
                if(patient.getBsn()==0){
			errors.rejectValue("bsn", "user.bsn", "Input can not be 0.");
		}

	}
    
}
