package com.firefalcon.controller;

import com.firefalcon.services.ExerciseService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
