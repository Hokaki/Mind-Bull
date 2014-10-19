package com.firefalcon.services;

import com.firefalcon.dao.ExerciseDAO;
import com.firefalcon.model.Exercise;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jeff
 */
@Service
@Transactional
public class ExerciseService {

    @Autowired
    private ExerciseDAO exerciseDAO;

    public void addExercise(Exercise exercise) {
        exerciseDAO.addExercise(exercise);
    }

    public void updateExercise(Exercise exercise) {
        exerciseDAO.updateExercise(exercise);
    }

    public Exercise getExercise(int id) {
        return exerciseDAO.getExercise(id);
    }

    public void deleteExercise(int id) {
        exerciseDAO.deleteExercise(id);
    }

    public List<Exercise> getExercises() {
        return exerciseDAO.getExercises();
    }

    public void storeAllExercises(List<Exercise> exercises) {

        exerciseDAO.storeAllExercises(exercises);

    }

}
