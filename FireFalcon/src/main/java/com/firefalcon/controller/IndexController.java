/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.controller;

/**
 *
 * @author Mohamed
 */

import com.firefalcon.model.User;
import com.firefalcon.services.UserService;
import com.firefalcon.validator.ObjectValidator;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.firefalcon.validator.ObjectValidator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    ObjectValidator objectValidator;    
    
    
        @RequestMapping(value = {"/Activeindex"}, method = RequestMethod.GET)
	public ModelAndView index(@Valid @ModelAttribute("user") User user, BindingResult result,
            HttpServletRequest request) throws IOException{
            
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("user");
            Boolean isAdmin = user.getIsAdmin();
            session.setAttribute(isAdmin.toString(), isAdmin);
            
            ModelAndView mavIndex = new ModelAndView("index");
            mavIndex.addObject("isAdmin", isAdmin);
            
            if (user.getIsAdmin()) {
                mavIndex = new ModelAndView("index/adminIndex");
                mavIndex.addObject("isAdmin", isAdmin);
                return mavIndex;
            } else {
                mavIndex = new ModelAndView("index/therapistIndex");
                mavIndex.addObject("isAdmin", isAdmin);
                return mavIndex;
            }
        }
        
        
}
