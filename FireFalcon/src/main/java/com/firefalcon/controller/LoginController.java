package com.firefalcon.controller;

/**
 *
 * @author Mohamed
 */
import com.firefalcon.model.User;
import com.firefalcon.services.UserService;
import com.firefalcon.validator.LoginValidator;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/login"})
    public ModelAndView login(@ModelAttribute User user) throws IOException {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new User());

        return mav;
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.POST)
    public ModelAndView checkLogin(@Valid @ModelAttribute("user") User user, BindingResult result,
            HttpServletRequest request) {
        
        ModelAndView mavLogin = new ModelAndView("login");

        LoginValidator loginValidator = new LoginValidator();
        loginValidator.validate(user, result);

        int numRow = userService.checkRow(user);
        user = userService.getUser(user.getUsername());
        
       

        HttpSession session = request.getSession();
        session.setAttribute("user", user);    

        if (numRow == 1) {
            if (user.getIsAdmin()) {
                ModelAndView mavIndex = new ModelAndView("index/adminIndex");
                user = (User) session.getAttribute("user");
                Boolean isAdmin = user.getIsAdmin();
                String name = user.getUsername();
                session.setAttribute(name, name);
                session.setAttribute(isAdmin.toString(), isAdmin);
                mavIndex.addObject("name", name);
                mavIndex.addObject("isAdmin", isAdmin);
                return mavIndex;
            } else {
                ModelAndView mavIndex = new ModelAndView("index/therapistIndex");
                user = (User) session.getAttribute("user");
                Boolean isAdmin = user.getIsAdmin();
                String name = user.getUsername();
                session.setAttribute(isAdmin.toString(), isAdmin);
                session.setAttribute(name, name);
                mavIndex.addObject("name", name);
                mavIndex.addObject("isAdmin", isAdmin);
                return mavIndex;
            }

        } else if(numRow == 0 && !(result.hasErrors())){
            String message = "The user doesn't exsist.";
            mavLogin.addObject("message", message);
            return mavLogin;
            
        } else if (result.hasErrors()) {

            return mavLogin;
        } else {
            return mavLogin;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, @ModelAttribute("user") User user) {
        ModelAndView mavLogout = new ModelAndView("login");

        HttpSession session = request.getSession();
        session.invalidate();
        return mavLogout;
        
    }
}
