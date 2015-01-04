package com.firefalcon.controller;

import com.firefalcon.model.User;
import com.firefalcon.services.UserService;
import com.firefalcon.validator.UserValidator;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/list")
    public ModelAndView userList(HttpServletRequest request, User user) throws IOException {
        ModelAndView userListView = new ModelAndView("user/UserList");
        
        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        String name = user.getUsername();
        
        userListView.addObject("oldUser", userService.getUser(name));
        userListView.addObject("userList", userService.getUsers());

        return userListView;
    }
    
     @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView userAddPage() throws IOException {

        ModelAndView userAddView = new ModelAndView("user/AddUser");
        userAddView.addObject("user", new User());
        return userAddView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView userAdd(@ModelAttribute User user, 
            HttpServletRequest request, BindingResult result)  {

        ModelAndView userListView = new ModelAndView("user/UserList");
        ModelAndView userAddView = new ModelAndView("user/AddUser");

       UserValidator userValidator = new UserValidator();
       userValidator.validate(user, result);
        
        if(result.hasErrors()){
           return userAddView;
       }else{

        userService.addUser(user);
        userListView.addObject("userList", userService.getUsers());

        return userListView;
        }

    }
    
    @RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
    public ModelAndView userEditPage(@PathVariable String username, User oldUser,  HttpServletRequest request) throws IOException {
        
        ModelAndView userEditView = new ModelAndView("user/EditUser");
        
        HttpSession session = request.getSession();
        oldUser = (User)session.getAttribute("user");
        String name = oldUser.getUsername();
        
        userEditView.addObject("oldUser", userService.getUser(name));
        
        userEditView.addObject("user", userService.getUser(username));
        return userEditView;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView userEditAdd(@ModelAttribute User user, User oldUser,  HttpServletRequest request)  {
        
        ModelAndView userListView = new ModelAndView("user/UserList");
        
        HttpSession session = request.getSession();
        oldUser = (User)session.getAttribute("user");
        String name = oldUser.getUsername();
        
        userListView.addObject("oldUser", userService.getUser(name));
        userService.updateUser(user);
        userListView.addObject("userList", userService.getUsers());

        return userListView;

    }
}
