/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.editor;


import com.firefalcon.model.Exercise;
import com.firefalcon.services.ExerciseService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseEditor extends PropertyEditorSupport {

    @Autowired
    private ExerciseService ExerciseService;

    // Converts a String patient bsn to a Patient (when submitting form)
    @Override
    public void setAsText(String text) {
       Exercise E = this.ExerciseService.getExercise(Integer.valueOf(text));
  
        this.setValue(E);
    }

}
