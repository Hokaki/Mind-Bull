/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.controller;

import com.firefalcon.editor.ExerciseEditor;
import com.firefalcon.editor.PatientEditor;
import com.firefalcon.model.Assignment;
import com.firefalcon.model.Exercise;
import com.firefalcon.model.Patient;
import com.firefalcon.services.AssignmentService;
import com.firefalcon.services.ExerciseService;
import com.firefalcon.services.PatientService;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author chrisvanderheijden
 */

@Controller
@RequestMapping(value = "/assignment")
public class AssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientEditor patientEditor;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private ExerciseEditor exerciseEditor;
    
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Patient.class, this.patientEditor);
        binder.registerCustomEditor(Exercise.class, this.exerciseEditor);
    }
    
    @RequestMapping(value = "/list")
    public ModelAndView AssignmentList() throws IOException {
        ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentList");
        assignmentListView.addObject("assignment", assignmentService.getAssignments());

        return assignmentListView;
    }
    
//      @RequestMapping(value = "/assignment/add/{bsn}", method = RequestMethod.GET)
//    public ModelAndView assignmentAddPage(@PathVariable int bsn) throws IOException {
//
//        ModelAndView assignmentAddView = new ModelAndView("assignment/AddAssignment");
//        Patient patient = patientService.getPatient(bsn);
////        Exercise exercise = (Exercise) exerciseService.getExercises();
//        Assignment assignment =new Assignment();
//        assignment.setBsn(patient);
////        assignment.setDescription(exercise);
//        assignmentAddView.addObject("assignment", assignment);
//        return assignmentAddView;
//
//    }
    
//    @RequestMapping(value = "/assignment/add", method = RequestMethod.POST)
//    public ModelAndView assignmentAdd(@ModelAttribute Assignment assignment)  {
//
//       ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentList");
//
//       assignmentService.addAssignment(assignment);
//
//       assignmentListView.addObject("assignment", assignmentService.getAssignments());
//        
//       
//        String message = "assignment was successfully added.";
//        assignmentListView.addObject("message", message);
//
//        return assignmentListView;
//
//    }
    
    
//       @RequestMapping(value = "/edit/{bsn}", method = RequestMethod.GET)
//    public ModelAndView assignmentEditPage(@PathVariable int bsn) throws IOException {
//
//        ModelAndView assignmentAddView = new ModelAndView("assignment/EditAssignment");
//     //   userAddView.addObject("patient", patientService.getPatient(bsn));
//        assignmentAddView.addObject("assignment", assignmentService.getAssignment(bsn));
//        return assignmentAddView;
//
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public ModelAndView assignmentEditAdd(@ModelAttribute Assignment assignment)  {
//
//        ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentList");
//        //patientService.updatePatient(patient);
//        assignmentService.updateAssignment(assignment);
//       // patientListView.addObject("patientList", patientService.getPatients());
//        assignmentListView.addObject("assignment", assignmentService.getAssignments());
//
//        return assignmentListView;
//
//    }
    
}
