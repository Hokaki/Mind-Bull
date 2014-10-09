package com.springmvc.controller;

import com.springmvc.editor.RoleEditor;
import com.springmvc.model.User;
import com.springmvc.model.Role;
import com.springmvc.service.UserService;
import com.springmvc.service.RoleService;
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
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleEditor roleEditor;

    private static String titelNieuw = "Nieuwe user";
    private static String titelWijzig = "Wijzigen user";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, this.roleEditor);
    }
    
    @RequestMapping(value = "/list")
    public ModelAndView userList() throws IOException {
        ModelAndView userListView = new ModelAndView("userList");
        userListView.addObject("userList", userService.getUsers());

        return userListView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView userAddPage() throws IOException {

        ModelAndView userAddView = new ModelAndView("userAdd");
        userAddView.addObject("paginaTitel", titelNieuw);
        userAddView.addObject("user", new User());
        userAddView.addObject("roleList", roleService.getRoles());
        return userAddView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView userAdd(@ModelAttribute User user)  {

        ModelAndView userListView = new ModelAndView("userList");
        userService.addUser(user);
        userListView.addObject("userList", userService.getUsers());
        String message = "User was successfully added.";
        userListView.addObject("message", message);

        return userListView;

    }

   @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable int id) {
		ModelAndView userEditView = new ModelAndView("userEdit");
		userEditView.addObject("paginaTitel", titelWijzig);
		userEditView.addObject("user",userService.getUser(id));
                userEditView.addObject("roleList", roleService.getRoles());
		return userEditView;
	}
        
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView userEdit(@ModelAttribute("user") User user)  {

        ModelAndView userListView = new ModelAndView("userList");
        userService.updateUser(user);

        userListView.addObject("userList", userService.getUsers());

        String message = "User was successfully edited.";
        userListView.addObject("message", message);

        return userListView;

    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeUser(@PathVariable int id) {
         ModelAndView userListView = new ModelAndView("userList");
        userService.deleteUser(id);
        userListView.addObject("userList", userService.getUsers());
        
        String message = "User was successfully deleted.";
        userListView.addObject("message", message);
        return userListView;
    }
}
