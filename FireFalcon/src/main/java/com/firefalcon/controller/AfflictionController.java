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
import com.firefalcon.validator.AfflictionValidator;

import java.io.IOException;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

//    @RequestMapping(value = "/list/{bsn}")
//    public ModelAndView AfflictionList(@PathVariable String bsn) throws IOException {
//        Patient patient = new Patient();
//        patient = patientService.getPatient(Integer.parseInt(patient.decrypt(bsn)));
//        ModelAndView afflictionListView = new ModelAndView("affliction/AfflictionList");
//        afflictionListView.addObject("affliction", afflictionService.getAfflictionsByPatients(bsn));
//
//        return afflictionListView;
//    }
    @RequestMapping(value = "/list/{bsn}")

    public ModelAndView AfflictionList(@PathVariable String bsn) throws IOException {

        Patient patient = new Patient();
        patient = patientService.getPatient(Integer.parseInt(patient.decrypt(bsn)));
        ModelAndView afflictionListView = new ModelAndView("affliction/AfflictionList");

        afflictionListView.addObject("affliction", afflictionService.getAfflictionsByPatients(bsn));
        afflictionListView.addObject("BSN", bsn);

        return afflictionListView;
    }

    @RequestMapping(value = "/add/{bsn}", method = RequestMethod.GET)
    public ModelAndView afflictionAddPage(@PathVariable String bsn) throws IOException {
        Patient patient = new Patient();
        ModelAndView afflictionAddView = new ModelAndView("affliction/AddAffliction");
        patient = patientService.getPatient(Integer.parseInt(patient.decrypt(bsn)));
        Affliction affliction = new Affliction();
        affliction.setBsn(patient);
        afflictionAddView.addObject("affliction", affliction);
        return afflictionAddView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView afflictionAdd(@ModelAttribute Affliction affliction, BindingResult result) throws IOException {
        System.out.println("rhgidbgdbgjdfgdfhggdhgsd---------------------------------------------");
        ModelAndView afflictionListView = new ModelAndView("affliction/AfflictionList");
        ModelAndView afflictionAddView = new ModelAndView("affliction/AddAffliction");

        AfflictionValidator afflictionValidator = new AfflictionValidator();
        afflictionValidator.validate(affliction, result);
        if (result.hasErrors()) {
            return afflictionAddView;
        } else {
            afflictionService.addAffliction(affliction);
            afflictionListView.addObject("affliction", afflictionService.getAfflictionsByPatients(affliction.getBsn().encrypt()));
            afflictionListView.addObject("BSN", affliction.getBsn().encrypt());

            String message = "affliction was successfully added.";
            afflictionListView.addObject("message", message);
            return afflictionListView;
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView afflictionEditPage(@PathVariable int id) throws IOException {

        ModelAndView afflictionAddView = new ModelAndView("affliction/EditAffliction");

        afflictionAddView.addObject("affliction", afflictionService.getAffliction(id));
        return afflictionAddView;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView afflictionEditAdd(@ModelAttribute Affliction affliction) {

        ModelAndView afflictionListView = new ModelAndView("affliction/AfflictionList");

        afflictionService.updateAffliction(affliction);
        afflictionListView.addObject("affliction", afflictionService.getAfflictions());

        return afflictionListView;

    }

}
