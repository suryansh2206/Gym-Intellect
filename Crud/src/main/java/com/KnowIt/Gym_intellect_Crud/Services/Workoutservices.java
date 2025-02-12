package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AotInitializerNotFoundException;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout;
import com.KnowIt.Gym_intellect_Crud.Repository.Workoutrepo;

@Service
public class Workoutservices {
	 @Autowired
	    private Workoutrepo workoutRepository;

	    // Create a new workout
	    public Workout createWorkout(Workout workout) {
	        return workoutRepository.save(workout);
	    }

	    // Get all workouts
	    public List<Workout> getAllWorkouts() {
	        return workoutRepository.findAll();
	    }

	    // Get workout by ID
	    public Workout getWorkoutById(Long workoutId) {
	        return workoutRepository.findById(workoutId)
	                .orElseThrow(() -> new AotInitializerNotFoundException(null, "Workout not found with ID: " + workoutId));
	    }

	    // Update workout
	    public Workout updateWorkout(Long workoutId, Workout updatedWorkout) {
	        Workout existingWorkout = getWorkoutById(workoutId);
	        existingWorkout.setExerciseName(updatedWorkout.getExerciseName());
	        existingWorkout.setSets(updatedWorkout.getSets());
	        existingWorkout.setReps(updatedWorkout.getReps());
	        existingWorkout.setDuration(updatedWorkout.getDuration());
	        existingWorkout.setRestTime(updatedWorkout.getRestTime());
	        return workoutRepository.save(existingWorkout);
	    }

	    // Delete workout
	    public void deleteWorkout(Long workoutId) {
	        workoutRepository.deleteById(workoutId);
	    }

}

