package com.firefalcon.controller;

/**
 *
 * @author Mohamed
 */
import com.firefalcon.model.User;
import com.firefalcon.services.UserService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView checkLogin(@ModelAttribute User user) {
        ModelAndView mavIndex = new ModelAndView("index");
        ModelAndView mavLogin = new ModelAndView("login");
        mavLogin.addObject("login", new User());

        int numRow = userService.checkRow(user);

        String message = "Unsuccesvol login";

        String userName = user.getUsername();
        if (numRow == 1) {
            mavIndex.addObject("user", userName);

            return mavIndex;
        } else {
            mavLogin.addObject("message", message);
            return mavLogin;
        }
    }
}
