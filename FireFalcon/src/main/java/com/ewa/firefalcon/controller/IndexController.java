/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ewa.firefalcon.controller;

/**
 *
 * @author Mohamed
 */
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    
        @RequestMapping(value="/")
	public ModelAndView home() throws IOException{
		return new ModelAndView("index");
	}
        @RequestMapping(value="/index")
	public ModelAndView index() throws IOException{
		return new ModelAndView("index");
	}
    
}
