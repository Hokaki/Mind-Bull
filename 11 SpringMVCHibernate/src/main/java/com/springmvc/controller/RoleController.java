package com.springmvc.controller;

import com.springmvc.model.Role;
import com.springmvc.service.RoleService;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private static String titelNieuw = "New role";
    private static String titelWijzig = "Edit role";
    private static String titelVerwijder = "Remove role";

    @RequestMapping(value = "/list")
    public ModelAndView roleList() throws IOException {
        ModelAndView roleListView = new ModelAndView("roleList");
        roleListView.addObject("roleList", roleService.getRoles());

        return roleListView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView roleAddPage() throws IOException {

        ModelAndView roleAddView = new ModelAndView("roleAdd");
        roleAddView.addObject("paginaTitel", titelNieuw);
        roleAddView.addObject("role", new Role());
        return roleAddView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView roleAdd(@ModelAttribute Role role) throws IOException {

        ModelAndView roleListView = new ModelAndView("roleList");
        roleService.addRole(role);

        roleListView.addObject("roleList", roleService.getRoles());

        String message = "Role was successfully added.";
        roleListView.addObject("message", message);

        return roleListView;

    }

   @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editRolePage(@PathVariable int id) {
		ModelAndView roleEditView = new ModelAndView("roleEdit");
		roleEditView.addObject("paginaTitel", titelWijzig);
		roleEditView.addObject("role",roleService.getRole(id));
		return roleEditView;
	}

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView roleEdit(@ModelAttribute("role") Role role)  {

        ModelAndView roleListView = new ModelAndView("roleList");
        roleService.updateRole(role);

        roleListView.addObject("roleList", roleService.getRoles());

        String message = "Role was successfully edited.";
        roleListView.addObject("message", message);

        return roleListView;

    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeRole(@PathVariable int id) {
        ModelAndView roleListView = new ModelAndView("roleList");
        roleService.deleteRole(id);
        roleListView.addObject("roleList", roleService.getRoles());
        
        String message = "Role was successfully deleted.";
        roleListView.addObject("message", message);
        return roleListView;
    }

}
