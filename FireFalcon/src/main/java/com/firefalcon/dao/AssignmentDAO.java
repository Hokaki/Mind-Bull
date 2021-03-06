/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.dao;

//import com.firefalcon.model.
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.firefalcon.model.Assignment;
import org.springframework.stereotype.Repository;


/**
 *
 * @author chrisvanderheijden
 */
@Repository
public class AssignmentDAO{
    
    @Autowired
    private SessionFactory sessionFactory;


    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addAssignment(Assignment assignment) {
        
        getCurrentSession().save(assignment);
    }

     public void updateAssignment(Assignment assignment) {
         Assignment assignmentToUpdate = getAssignment(assignment.getIdAssignment());
         assignmentToUpdate.setExerciseId(assignment.getExerciseId());
         assignmentToUpdate.setRepetitions(assignment.getRepetitions());
         assignmentToUpdate.setDays(assignment.getDays());
         assignmentToUpdate.setStart_date(assignment.getStart_date());
         assignmentToUpdate.setEnd_date(assignment.getEnd_date());
         getCurrentSession().update(assignmentToUpdate);
     }

    public Assignment getAssignment(int id) {
        Assignment assignment = (Assignment) getCurrentSession().get(Assignment.class, id);
        return assignment;
    }

    public void deleteAssignment(int idAssignment) {
        Assignment assignment = getAssignment(idAssignment);
        if (assignment != null) {
            getCurrentSession().delete(assignment);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Assignment> getAssignments() {
        return getCurrentSession().createQuery("from Assignment").list();
    }

    public void storeAllAssignments(List<Assignment> assignments) {

        for (Assignment assignment : assignments) {
            getCurrentSession().save(assignment);
        }

    }
   
}
