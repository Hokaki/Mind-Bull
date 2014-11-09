/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.editor;


import com.firefalcon.model.Patient;
import com.firefalcon.services.PatientService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientEditor extends PropertyEditorSupport {

    @Autowired
    private PatientService patientService;

    // Converts a String patient bsn to a Patient (when submitting form)
    @Override
    public void setAsText(String text) {
       Patient p = this.patientService.getPatient(Integer.valueOf(text));

        this.setValue(p);
    }

}

