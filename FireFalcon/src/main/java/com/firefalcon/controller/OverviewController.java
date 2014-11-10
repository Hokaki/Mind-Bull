/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.controller;

import com.firefalcon.model.Exercise;
import com.firefalcon.model.Overview;
import com.firefalcon.services.OverviewService;
import java.io.IOException;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hokaki
 */
@Controller
@RequestMapping(value = "/overview")
public class OverviewController implements Serializable{
    
    @Autowired
    private OverviewService overviewService;
    
    @RequestMapping(value = "/list")
    public ModelAndView overviewList() throws IOException {
        ModelAndView overviewListView = new ModelAndView("OverviewList");
        overviewListView.addObject("overviewList", overviewService.getOverviews());

        return overviewListView;
    }
//    
// @RequestMapping(value = "/overview/{bsn}", method = RequestMethod.GET)
//    public ModelAndView overviewPage(@PathVariable int bsn) throws IOException {
//
//        ModelAndView patientEditView = new ModelAndView("EditPatient");
//        patientEditView.addObject("patient", patientService.getPatient(bsn));
//        return patientEditView;
//
//    }
//
//    @RequestMapping(value = "/overview", method = RequestMethod.POST)
//    public ModelAndView overviewAdd(@ModelAttribute Overview overview)  {
//
//        ModelAndView overviewListView = new ModelAndView("overviewList");
//        overviewService.updateOverview(overview);
//        overviewListView.addObject("overviewList", overviewService.getOverviews());
//
//        return overviewListView;
//
//    }
}
