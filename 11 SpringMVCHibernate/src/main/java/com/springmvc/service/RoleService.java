

package com.springmvc.service;

import com.springmvc.dao.RoleDAO;
import com.springmvc.model.Role;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {
   
    @Autowired
    private RoleDAO roleDAO;

    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }

    public void deleteRole(int id) {
        roleDAO.deleteRole(id);
    }
     

    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    public void storeAllRoles(List<Role> roles) {

        roleDAO.storeAllRoles(roles);

    }
}
