package com.springmvc.controller;

import com.springmvc.model.User;
import com.springmvc.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView home() throws IOException{
		return new ModelAndView("index");
	}
        @RequestMapping(value="/index")
	public ModelAndView index() throws IOException{
		return new ModelAndView("index");
	}
        
        
}
