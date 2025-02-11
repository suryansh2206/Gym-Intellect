package com.knowit.gymintellect.gym_member.gym_member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_member.gym_member.entity.Workout;
import com.knowit.gymintellect.gym_member.gym_member.service.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
	@Autowired
    private WorkoutService workoutService;
	
	@GetMapping("/{userId}")
    public ResponseEntity<List<Workout>> getWorkouts(@PathVariable int userId) {
        List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
        return ResponseEntity.ok(workouts);
    }
}
