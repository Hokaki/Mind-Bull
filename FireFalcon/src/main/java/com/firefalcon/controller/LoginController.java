package com.firefalcon.controller;

/**
 *
 * @author Mohamed
 */
import com.firefalcon.model.User;
import com.firefalcon.services.UserService;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.firefalcon.validator.ObjectValidator;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    ObjectValidator objectValidator;

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
        Boolean isAdmin = user.getIsAdmin();
        session.setAttribute(isAdmin.toString(), isAdmin);
        

        if (numRow == 1) {
            if (user.getIsAdmin()) {
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
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mavLogout = new ModelAndView("login");

        HttpSession session = request.getSession();
        session.invalidate();
        return mavLogout;
        
    }
}
