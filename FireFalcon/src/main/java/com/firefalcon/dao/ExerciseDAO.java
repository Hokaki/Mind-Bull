/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firefalcon.dao;

import com.firefalcon.model.Exercise;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jeff
 */
@Repository
public class ExerciseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addExercise(Exercise exercise) {
        getCurrentSession().save(exercise);
    }

    public void updateExercise(Exercise exercise) {
        Exercise exerciseToUpdate = getExercise(exercise.getId());
        exerciseToUpdate.setId(exercise.getId());
        exerciseToUpdate.setName(exercise.getName());
        exerciseToUpdate.setDescription(exercise.getDescription());
        getCurrentSession().update(exerciseToUpdate);
    }

    public Exercise getExercise(int id) {
        Exercise exercise = (Exercise) getCurrentSession().get(Exercise.class, id);
        return exercise;
    }

    public void deleteExercise(int id) {
        Exercise exercise = getExercise(id);
        if (exercise != null) {
            getCurrentSession().delete(exercise);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Exercise> getExercises() {
        return getCurrentSession().createQuery("from Exercise").list();
    }

    public void storeAllExercises(List<Exercise> exercises) {

        for (Exercise exercise : exercises) {
            getCurrentSession().save(exercise);
        }

    }
}
