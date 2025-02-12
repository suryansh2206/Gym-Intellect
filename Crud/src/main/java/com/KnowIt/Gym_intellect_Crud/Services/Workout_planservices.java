package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AotInitializerNotFoundException;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout;
import com.KnowIt.Gym_intellect_Crud.Entity.WorkoutPlan;
import com.KnowIt.Gym_intellect_Crud.Entity.Workout_join;
import com.KnowIt.Gym_intellect_Crud.Repository.Workout_joinrepo;
import com.KnowIt.Gym_intellect_Crud.Repository.Workout_planrepo;
import com.KnowIt.Gym_intellect_Crud.Repository.Workoutrepo;

@Service
public class Workout_planservices {

    @Autowired
    private Workout_planrepo workoutPlanRepository;
    
    @Autowired
    private Workoutrepo workoutRepository; 
    
    @Autowired
    private Workout_joinrepo workoutJoinRepository;

    // Create a new plan
    public WorkoutPlan createPlan(WorkoutPlan plan) {
        return workoutPlanRepository.save(plan);
    }

    // Get all plans
    public List<WorkoutPlan> getAllPlans() {
        return workoutPlanRepository.findAll();
    }

    // Get plan by ID
    public WorkoutPlan getPlanById(Long planId) {
        return workoutPlanRepository.findById(planId)
                .orElseThrow(() -> new AotInitializerNotFoundException(null, "Plan not found with ID: " + planId));
    }

    // Update plan
    public WorkoutPlan updatePlan(Long planId, WorkoutPlan updatedPlan) {
        WorkoutPlan existingPlan = getPlanById(planId);
        existingPlan.setPlanName(updatedPlan.getPlanName());
        existingPlan.setDuration(updatedPlan.getDuration());
        existingPlan.setDescription(updatedPlan.getDescription());
        return workoutPlanRepository.save(existingPlan);
    }

    // Delete plan
    public void deletePlan(Long planId) {
        workoutPlanRepository.deleteById(planId);
    }
	

    public void addWorkoutsToPlan(Long planId, List<Long> workoutIds) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(planId).orElseThrow(() -> new RuntimeException("Workout Plan not found"));
        for (Long workoutId : workoutIds) {
            Workout workout = workoutRepository.findById(workoutId).orElseThrow(() -> new RuntimeException("Workout not found"));
            
            // Ensure the workout is not already linked
            if (workoutJoinRepository.findByWorkoutPlan_PlanId(planId).stream().noneMatch(wj -> wj.getWorkout().getWorkoutId().equals(workoutId))) {
            	Workout_join workoutJoin = new Workout_join();
                workoutJoin.setWorkout(workout);
                workoutJoin.setPlan(workoutPlan);
                workoutJoinRepository.save(workoutJoin);
            }
        }
    }
}
