/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.services;

import com.firefalcon.dao.AssignmentDAO;
import com.firefalcon.model.Assignment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssignmentService {
    
    @Autowired
    private AssignmentDAO assignmentDAO;

    public void addAssignment(Assignment assignment) {
        assignmentDAO.addAssignment(assignment);
    }

    public void updateAssignment(Assignment assignment) {
        assignmentDAO.updateAssignment(assignment);
        
    }

    public Assignment getAssignment(int id) {
        return assignmentDAO.getAssignment(id);
    }

    public void deleteAssignment(int id) {
        assignmentDAO.deleteAssignment(id);
    }

    public List<Assignment> getAssignments() {
        return assignmentDAO.getAssignments();
    }

    public void storeAllAssignments(List<Assignment> assignments) {

        assignmentDAO.storeAllAssignments(assignments);

    }

}

