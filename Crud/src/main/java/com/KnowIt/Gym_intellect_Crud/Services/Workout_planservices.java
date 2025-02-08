package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AotInitializerNotFoundException;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout_plan;
import com.KnowIt.Gym_intellect_Crud.Repository.Workout_planrepo;

@Service
public class Workout_planservices {

    @Autowired
    private Workout_planrepo workoutPlanRepository;

    // Create a new plan
    public Workout_plan createPlan(Workout_plan plan) {
        return workoutPlanRepository.save(plan);
    }

    // Get all plans
    public List<Workout_plan> getAllPlans() {
        return workoutPlanRepository.findAll();
    }

    // Get plan by ID
    public Workout_plan getPlanById(int planId) {
        return workoutPlanRepository.findById(planId)
                .orElseThrow(() -> new AotInitializerNotFoundException(null, "Plan not found with ID: " + planId));
    }

    // Update plan
    public Workout_plan updatePlan(int planId, Workout_plan updatedPlan) {
        Workout_plan existingPlan = getPlanById(planId);
        existingPlan.setPlanName(updatedPlan.getPlanName());
        existingPlan.setDuration(updatedPlan.getDuration());
        existingPlan.setDescription(updatedPlan.getDescription());
        return workoutPlanRepository.save(existingPlan);
    }

    // Delete plan
    public void deletePlan(int planId) {
        workoutPlanRepository.deleteById(planId);
    }
	

}
