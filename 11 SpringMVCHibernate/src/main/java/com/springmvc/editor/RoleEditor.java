/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springmvc.editor;

import com.springmvc.model.Role;
import com.springmvc.service.RoleService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService roleService;

    // Converts a String role id to a Role (when submitting form)
    @Override
    public void setAsText(String text) {
       Role r = this.roleService.getRole(Integer.valueOf(text));

        this.setValue(r);
    }

}

