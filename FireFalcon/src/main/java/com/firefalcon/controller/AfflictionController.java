/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.controller;

import com.firefalcon.model.Affliction;
import com.firefalcon.model.Patient;
import com.firefalcon.services.AfflictionService;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping(value = "/list")
    public ModelAndView AfflictionList() throws IOException {
        ModelAndView afflictionListView = new ModelAndView("AfflictionList");
        afflictionListView.addObject("Affliction", afflictionService.getAfflictions());

        return afflictionListView;
    }
    
      @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView afflictionAddPage() throws IOException {

        ModelAndView afflictionAddView = new ModelAndView("Affliction");
    
        afflictionAddView.addObject("affliction", new Affliction());
       
      
        return afflictionAddView;

    }
    
      @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView afflictionAdd(@ModelAttribute Affliction affliction)  {

        ModelAndView afflictionListView = new ModelAndView("AfflictionList");
       // userService.addUser(user);
        afflictionService.addAffliction(affliction);
        //userListView.addObject("userList", userService.getUsers());
       // afflictionListView.addObject("afflictionList", affliction);
       afflictionListView.addObject("afflictionList", afflictionService.getAfflictions());
        
       
        String message = "Affliction was successfully added.";
        afflictionListView.addObject("message", message);

        return afflictionListView;

    }
    
       @RequestMapping(value = "/edit/{bsn}", method = RequestMethod.GET)
    public ModelAndView afflictionEditPage(@PathVariable int bsn) throws IOException {

        ModelAndView afflictionAddView = new ModelAndView("EditAffliction");
     //   userAddView.addObject("patient", patientService.getPatient(bsn));
        afflictionAddView.addObject("affliction", afflictionService.getAffliction(bsn));
        return afflictionAddView;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView afflictionEditAdd(@ModelAttribute Affliction affliction)  {

        ModelAndView afflictionListView = new ModelAndView("afflictionList");
        //patientService.updatePatient(patient);
        afflictionService.updateAffliction(affliction);
       // patientListView.addObject("patientList", patientService.getPatients());
        afflictionListView.addObject("afflictionList", afflictionService.getAfflictions());

        return afflictionListView;

    }
    
}
