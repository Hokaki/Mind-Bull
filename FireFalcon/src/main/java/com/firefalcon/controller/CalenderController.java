/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.controller;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mohamed
 */
@Controller
@RequestMapping(value = "/calender")
public class CalenderController {
    
    @RequestMapping(value = "/view")
    public ModelAndView getPlanner() throws IOException, Exception {
        ModelAndView planner = new ModelAndView("JavaCalender");
        
           DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
           s.setWidth(900);
           s.setInitialDate(2013, 0, 21);
           s.load("./events.jsp", DHXDataFormat.JSON);
           planner.addObject("body", s.render());

        return planner;
    }
    
}
