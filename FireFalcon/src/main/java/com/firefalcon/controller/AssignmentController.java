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
import com.firefalcon.validator.AssignmentValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/view/{bsn}", method = RequestMethod.GET)
    public ModelAndView AssigntmentView(@PathVariable int bsn) throws IOException {
        ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentView");

        List<Assignment> assignment = assignmentService.getAssignments();
        List<Assignment> newAssignment = new ArrayList<Assignment>();
        for (Assignment x : assignment) {
            if ((x.getBsn().getBsn() == bsn)) {
                newAssignment.add(x);
            }
        }

        assignmentListView.addObject("assignment", newAssignment);

        return assignmentListView;
    }

    @RequestMapping(value = "/add/{bsn}", method = RequestMethod.GET)
    public ModelAndView assignmentAddPage(@PathVariable int bsn) throws IOException, Exception {

        ModelAndView assignmentAddView = new ModelAndView("assignment/AddAssignment");
        Patient patient = patientService.getPatient(bsn);
        List<Exercise> exercises = exerciseService.getExercises();

        Assignment assignment = new Assignment();
        assignment.setBsn(patient);

        assignmentAddView.addObject("assignment", assignment);
        assignmentAddView.addObject("exercises", exercises);
        return assignmentAddView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView assignmentAdd(@ModelAttribute Assignment assignment, BindingResult result) throws Exception {
        
        ModelAndView assignmentAddView = new ModelAndView("assignment/AddAssignment");
        
        AssignmentValidator assignmentValidator = new AssignmentValidator();
        assignmentValidator.validate(assignment, result);

        if (result.hasErrors()) {
            return assignmentAddView;
        } else {

            assignmentService.addAssignment(assignment);
            int bsn = (Integer) assignment.getBsn().getBsn();
            return assignmentAddPage(bsn);
        }
    }

//    @RequestMapping(value = "/finish")
//    public ModelAndView assignmentFinish() {
//
//        ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentList");
//        assignmentListView.addObject("assignment", assignmentService.getAssignments());
//        return assignmentListView;
//    }
    
       @RequestMapping(value = "/finish/{bsn}", method = RequestMethod.GET)
    public ModelAndView assignmentFinish(@PathVariable int bsn) {

        ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentList");

        List<Assignment> assignment = assignmentService.getAssignments();
        List<Assignment> newAssignment = new ArrayList<Assignment>();
        for (Assignment x : assignment) {
            if ((x.getBsn().getBsn() == bsn)) {
                newAssignment.add(x);
            }
        }
        assignmentListView.addObject("assignment", newAssignment);

        return assignmentListView;
    }

    @RequestMapping(value = "/edit/{idAssignment}", method = RequestMethod.GET)
    public ModelAndView assignmentEditPage(@PathVariable int idAssignment) throws IOException {
        List<Exercise> exercises = exerciseService.getExercises();

        ModelAndView assignmentAddView = new ModelAndView("assignment/EditAssignment");
        assignmentAddView.addObject("assignment", assignmentService.getAssignment(idAssignment));
        assignmentAddView.addObject("exercises", exercises);
        return assignmentAddView;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView assignmentEditAdd(@ModelAttribute Assignment assignment) {

        ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentList");

        assignmentService.updateAssignment(assignment);

        assignmentListView.addObject("assignment", assignmentService.getAssignments());

        return assignmentListView;

    }

    @RequestMapping(value = "/delete/{idAssignment}", method = RequestMethod.GET)
    public ModelAndView deleteAssignment(@PathVariable Integer idAssignment) {
        ModelAndView assignmentListView = new ModelAndView("assignment/AssignmentList");
        assignmentService.deleteAssignment(idAssignment);
        assignmentListView.addObject("assignment", assignmentService.getAssignments());

        return assignmentListView;
    }

}
