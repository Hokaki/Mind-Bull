package com.firefalcon.controller;

/**
 *
 * @author Mohamed
 */
import com.firefalcon.model.User;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    
        @RequestMapping(value="/")
	public ModelAndView login() throws IOException{
              ModelAndView mav = new ModelAndView("login");
              mav.addObject("login", new User());
              
              return mav;
	}
        
        @RequestMapping(value="/login")
	public ModelAndView loginSecond() throws IOException{
              ModelAndView mav = new ModelAndView("login");
              mav.addObject("login", new User());
              
              return mav;
	}
}
