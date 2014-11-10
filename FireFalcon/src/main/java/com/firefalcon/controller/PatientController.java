package com.firefalcon.controller;
import com.firefalcon.model.Patient;
import com.firefalcon.services.PatientService;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @RequestMapping(value = "/list")
    public ModelAndView patientList() throws IOException {
        ModelAndView patientListView = new ModelAndView("patient/PatientList");
        patientListView.addObject("patientList", patientService.getPatients());

        return patientListView;
    }
    
     @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView patientAddPage() throws IOException {

        ModelAndView patientAddView = new ModelAndView("patient/AddPatient");
        patientAddView.addObject("patient", new Patient());
        return patientAddView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView patientAdd(@ModelAttribute Patient patient)  {

        ModelAndView patientListView = new ModelAndView("patient/PatientList");
        patientService.addPatient(patient);
        patientListView.addObject("patientList", patientService.getPatients());

        return patientListView;

    }
    
    @RequestMapping(value = "/edit/{bsn}", method = RequestMethod.GET)
    public ModelAndView patientEditPage(@PathVariable int bsn) throws IOException {

        ModelAndView patientEditView = new ModelAndView("patient/EditPatient");
        patientEditView.addObject("patient", patientService.getPatient(bsn));
        return patientEditView;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView patientEditAdd(@ModelAttribute Patient patient)  {

        ModelAndView patientListView = new ModelAndView("patient/PatientList");
        patientService.updatePatient(patient);
        patientListView.addObject("patientList", patientService.getPatients());

        return patientListView;

    }
    
        @RequestMapping(value = "/view/{bsn}", method = RequestMethod.GET)
    public ModelAndView patienGraph(@PathVariable int bsn) throws IOException {

        ModelAndView patientGraphView = new ModelAndView("patient/ResultView");
        patientGraphView.addObject("patient", patientService.getPatient(bsn));
        patientGraphView.addObject("resultList", patientService.getResults(bsn));

        return patientGraphView;

    }

}
