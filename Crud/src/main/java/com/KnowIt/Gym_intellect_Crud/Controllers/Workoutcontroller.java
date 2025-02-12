package com.KnowIt.Gym_intellect_Crud.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout;
import com.KnowIt.Gym_intellect_Crud.Services.Workoutservices;

@RestController
@RequestMapping("/api/workouts")
public class Workoutcontroller {
	 @Autowired
	    private Workoutservices workoutService;

	    // Create a new workout
	    @PostMapping
	    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
	        Workout createdWorkout = workoutService.createWorkout(workout);
	        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
	    }

	    // Get all workouts
	    @GetMapping
	    public ResponseEntity<List<Workout>> getAllWorkouts() {
	        List<Workout> workouts = workoutService.getAllWorkouts();
	        return ResponseEntity.ok(workouts);
	    }

	    // Get workout by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
	        Workout workout = workoutService.getWorkoutById(id);
	        return ResponseEntity.ok(workout);
	    }

	    // Update workout
	    @PutMapping("/{id}")
	    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {
	        Workout workout = workoutService.updateWorkout(id, updatedWorkout);
	        return ResponseEntity.ok(workout);
	    }

	    // Delete workout
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
	        workoutService.deleteWorkout(id);
	        return ResponseEntity.noContent().build();
	    }

}
