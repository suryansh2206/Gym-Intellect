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

import com.KnowIt.Gym_intellect_Crud.Entity.Workout_plan;
import com.KnowIt.Gym_intellect_Crud.Services.Workout_planservices;

@RestController
@RequestMapping("/api/workout-plans")
public class Workout_plancontroller {
	@Autowired
    private Workout_planservices workoutPlanService;

    // Create a new workout plan
    @PostMapping
    public ResponseEntity<Workout_plan> createPlan(@RequestBody Workout_plan plan) {
        Workout_plan createdPlan = workoutPlanService.createPlan(plan);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    // Get all workout plans
    @GetMapping
    public ResponseEntity<List<Workout_plan>> getAllPlans() {
        List<Workout_plan> plans = workoutPlanService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    // Get workout plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<Workout_plan> getPlanById(@PathVariable int id) {
        Workout_plan plan = workoutPlanService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    // Update workout plan
    @PutMapping("/{id}")
    public ResponseEntity<Workout_plan> updatePlan(@PathVariable int id, @RequestBody Workout_plan updatedPlan) {
        Workout_plan plan = workoutPlanService.updatePlan(id, updatedPlan);
        return ResponseEntity.ok(plan);
    }

    // Delete workout plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable int id) {
        workoutPlanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }

}
