package com.firefalcon.controller;
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
        ModelAndView patientListView = new ModelAndView("PatientList");
        patientListView.addObject("patientList", patientService.getPatients());

        return patientListView;
    }
}
