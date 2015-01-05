package com.firefalcon.controller;

import com.firefalcon.model.Assignment;
import com.firefalcon.model.Exercise;
import com.firefalcon.services.AssignmentService;
import com.firefalcon.services.ExerciseService;
import com.firefalcon.validator.ExerciseValidator;
import com.firefalcon.validator.PatientValidator;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import java.util.List;
//import org.hibernate.Query;
//import org.hibernate.SessionFactory;
//import org.hibernate.Session;
//import org.hibernate.cfg.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jeff
 */
@Controller
@RequestMapping(value = "/exercise")
public class ExerciseController implements Serializable{

    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(value = "/list")
    public ModelAndView exerciseList() throws IOException {
        ModelAndView exerciseListView = new ModelAndView("exercise/ExerciseList");
        exerciseListView.addObject("exerciseList", exerciseService.getExercises());

        return exerciseListView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView exerciseAddPage() throws IOException {

        ModelAndView exerciseAddView = new ModelAndView("exercise/AddExercise");
        exerciseAddView.addObject("exercise", new Exercise());
        return exerciseAddView;

    }
    
     @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView exerciseAdd(@ModelAttribute Exercise exercise, BindingResult result)  {
        
        ModelAndView exerciseAddView = new ModelAndView("exercise/AddExercise");
        
        ExerciseValidator exerciseValidator = new ExerciseValidator();
        exerciseValidator.validate(exercise, result);
        
        if(result.hasErrors()){
         return exerciseAddView;
        }else{
        ModelAndView exerciseListView = new ModelAndView("exercise/ExerciseList");
        exerciseService.addExercise(exercise);
        exerciseListView.addObject("exerciseList", exerciseService.getExercises());

        return exerciseListView;
        }
    }
    
      @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView exerciseEditPage(@PathVariable int id) throws IOException {
        
        ModelAndView exerciseEditView = new ModelAndView("exercise/EditExercise");
        exerciseEditView.addObject("exercise", exerciseService.getExercise(id));
        return exerciseEditView;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView exerciseEditAdd(@ModelAttribute Exercise exercise) {
        
        ModelAndView exerciseListView = new ModelAndView("exercise/ExerciseList");
        exerciseService.updateExercise(exercise);
        exerciseListView.addObject("exerciseList", exerciseService.getExercises());
        
        return exerciseListView;
 
    }
    
       @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteExercise(@PathVariable int id) {
        ModelAndView exerciseListView = new ModelAndView("exercise/ExerciseList");
        List<Assignment> assignment = assignmentService.getAssignments();
        List<Assignment> newAssignment = new ArrayList<Assignment>();
        for (Assignment x : assignment) {
                 newAssignment.add(x);
        }
                 for (int i = 0; i < newAssignment.size();) {
                    if(newAssignment.get(i).getBsn().getBsn() != id){        
                        i++;
                    }else{                                       
                    String message = "This exercise is being used.";
                    exerciseListView.addObject("message", message);
                    exerciseListView.addObject("exerciseList", exerciseService.getExercises()); 
                    return exerciseListView;
                }
              
            }
                 exerciseService.deleteExercise(id);  
                    exerciseListView.addObject("exerciseList", exerciseService.getExercises()); 
                    return exerciseListView;

    }
}

