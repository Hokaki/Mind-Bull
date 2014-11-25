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
    
    
        @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public ModelAndView index(@Valid @ModelAttribute("user") User user, BindingResult result,
            HttpServletRequest request) throws IOException{
            
            ModelAndView mavLogin = new ModelAndView("login");
        mavLogin.addObject("user", new User());

        objectValidator = new ObjectValidator();
        objectValidator.validate(user, result);

        int numRow = userService.checkRow(user);
        user = userService.getUser(user.getUsername());
        
        
        
       

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        user = (User) session.getAttribute("user");
        String name = user.getUsername();
        session.setAttribute(name, name);
        Boolean isAdmin = user.isAdmin();
        session.setAttribute(isAdmin.toString(), isAdmin);
        

        if (numRow == 1) {
            if (user.isAdmin()) {
                ModelAndView mavIndex = new ModelAndView("index/adminIndex");
                mavIndex.addObject("name", name);
                mavIndex.addObject("isAdmin", isAdmin);
                return mavIndex;
            } else {
                ModelAndView mavIndex = new ModelAndView("index/therapistIndex");
                mavIndex.addObject("name", name);
                mavIndex.addObject("isAdmin", isAdmin);
                return mavIndex;
            }

        } else if (result.hasErrors()) {

            return mavLogin;
        } else {
            return mavLogin;
        }
            
         //   return new ModelAndView("index");
            
        }
        
        
}
