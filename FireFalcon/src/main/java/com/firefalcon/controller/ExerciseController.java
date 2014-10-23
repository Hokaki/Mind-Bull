package com.firefalcon.controller;

import com.firefalcon.model.Exercise;
import com.firefalcon.services.ExerciseService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jeff
 */
@Controller
@RequestMapping(value = "/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(value = "/list")
    public ModelAndView exerciseList() throws IOException {
        ModelAndView exerciseListView = new ModelAndView("ExerciseList");
        exerciseListView.addObject("exerciseList", exerciseService.getExercises());

        return exerciseListView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView exerciseAddPage() throws IOException {

        ModelAndView exerciseAddView = new ModelAndView("AddExercise");
        exerciseAddView.addObject("exercise", new Exercise());
        return exerciseAddView;

    }
    
     @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView exerciseAdd(@ModelAttribute Exercise exercise)  {

        ModelAndView exerciseAddView = new ModelAndView("ExerciseList");
        exerciseService.addExercise(exercise);
        exerciseAddView.addObject("exerciseList", exerciseService.getExercises());

        return exerciseAddView;
    }
}
