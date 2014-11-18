/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.firefalcon.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lex
 */
@Controller
@RequestMapping(value = "/RawData")
public class RawDataController {

    @RequestMapping(value = "/show")
    public ModelAndView AfflictionList() throws IOException {
        ModelAndView rawDataView = new ModelAndView("assignment/RawData");
       
        return rawDataView;
    }

}