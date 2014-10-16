package com.firefalcon.controller;

/**
 *
 * @author Mohamed
 */
import com.firefalcon.model.User;
import com.firefalcon.services.UserService;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    
    UserService userService;
    
    @RequestMapping(value = {"/", "/login"})
    public ModelAndView login(@ModelAttribute User user) throws IOException {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new User());
        
        return mav;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView checkLogin(@ModelAttribute User user) {
        ModelAndView mavIndex = new ModelAndView("index");
        ModelAndView mavLogin = new ModelAndView("login");
        int numRow = userService.checkRow(user);
        String message = "User was successfully loged in.";

        if (numRow == 0) {
            mavIndex.addObject("message", message);

            return mavIndex;
        } else {
            return mavLogin;
        }
    }
}
