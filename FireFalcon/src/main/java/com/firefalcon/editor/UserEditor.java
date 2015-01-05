/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.editor;


import com.firefalcon.model.User;
import com.firefalcon.services.UserService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEditor extends PropertyEditorSupport {

    @Autowired
    private UserService userService;

    @Override
    public void setAsText(String text) {
       User u = this.userService.getUser(text);

        this.setValue(u);
    }

}

