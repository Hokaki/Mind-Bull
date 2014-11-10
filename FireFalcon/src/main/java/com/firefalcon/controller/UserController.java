package com.firefalcon.controller;

import com.firefalcon.model.User;
import com.firefalcon.services.UserService;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView userList() throws IOException {
        ModelAndView userListView = new ModelAndView("user/UserList");
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
    public ModelAndView userAdd(@ModelAttribute User user)  {

        ModelAndView userListView = new ModelAndView("user/UserList");
        userService.addUser(user);
        userListView.addObject("userList", userService.getUsers());

        return userListView;

    }
    
    @RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
    public ModelAndView userEditPage(@PathVariable String username) throws IOException {

        ModelAndView userEditView = new ModelAndView("user/EditUser");
        userEditView.addObject("user", userService.getUser(username));
        return userEditView;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView userEditAdd(@ModelAttribute User user)  {

        ModelAndView userListView = new ModelAndView("user/UserList");
        userService.updateUser(user);
        userListView.addObject("userList", userService.getUsers());

        return userListView;

    }
}
