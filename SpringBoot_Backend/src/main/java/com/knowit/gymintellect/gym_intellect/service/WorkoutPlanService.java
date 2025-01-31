package com.knowit.gymintellect.gym_intellect.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.knowit.gymintellect.gym_intellect.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_intellect.repository.WorkoutPlanRespository;

public class WorkoutPlanService {
	@Autowired
	private WorkoutPlanRespository workoutPlanRepository;

	// Example: Fetch a WorkoutPlan by ID
	public WorkoutPlan getWorkoutPlanById(int planId) {
	    return workoutPlanRepository.findById(planId)
	            .orElseThrow(() -> new RuntimeException("WorkoutPlan not found with ID: " + planId));
	}
}
