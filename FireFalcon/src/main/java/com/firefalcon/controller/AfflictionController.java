/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.controller;

import com.firefalcon.editor.PatientEditor;
import com.firefalcon.model.Affliction;
import com.firefalcon.model.Patient;
import com.firefalcon.services.AfflictionService;
import com.firefalcon.services.PatientService;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author chrisvanderheijden
 */

@Controller
@RequestMapping(value = "/affliction")
public class AfflictionController {
    
    @Autowired
    private AfflictionService afflictionService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientEditor patientEditor;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Patient.class, this.patientEditor);
    }
    
    @RequestMapping(value = "/list")
    public ModelAndView AfflictionList() throws IOException {
        ModelAndView afflictionListView = new ModelAndView("affliction/AfflictionList");
        afflictionListView.addObject("affliction", afflictionService.getAfflictions());

        return afflictionListView;
    }
    
      @RequestMapping(value = "/add/{bsn}", method = RequestMethod.GET)
    public ModelAndView afflictionAddPage(@PathVariable int bsn) throws IOException {

        ModelAndView afflictionAddView = new ModelAndView("affliction/AddAffliction");
        Patient patient = patientService.getPatient(bsn);
        Affliction affliction =new Affliction();
        affliction.setBsn(patient);
        afflictionAddView.addObject("affliction", affliction);
        return afflictionAddView;

    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView afflictionAdd(@ModelAttribute Affliction affliction)  {

       ModelAndView afflictionListView = new ModelAndView("affliction/AfflictionList");

       afflictionService.addAffliction(affliction);

       afflictionListView.addObject("affliction", afflictionService.getAfflictions());
        
       
        String message = "affliction was successfully added.";
        afflictionListView.addObject("message", message);

        return afflictionListView;

    }
    
    
       @RequestMapping(value = "/edit/{bsn}", method = RequestMethod.GET)
    public ModelAndView afflictionEditPage(@PathVariable int bsn) throws IOException {

        ModelAndView afflictionAddView = new ModelAndView("affliction/EditAffliction");
     //   userAddView.addObject("patient", patientService.getPatient(bsn));
        afflictionAddView.addObject("affliction", afflictionService.getAffliction(bsn));
        return afflictionAddView;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView afflictionEditAdd(@ModelAttribute Affliction affliction)  {

        ModelAndView afflictionListView = new ModelAndView("affliction/AfflictionList");
        //patientService.updatePatient(patient);
        afflictionService.updateAffliction(affliction);
       // patientListView.addObject("patientList", patientService.getPatients());
        afflictionListView.addObject("affliction", afflictionService.getAfflictions());

        return afflictionListView;

    }
    
}
