package com.KnowIt.Gym_intellect_Crud.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KnowIt.Gym_intellect_Crud.Entity.WorkoutRoutine;
import com.KnowIt.Gym_intellect_Crud.Services.WorkoutRoutineService;

@RestController
@RequestMapping("/workout-routine")
public class WorkoutRoutineController {
    
    @Autowired
    private WorkoutRoutineService workoutRoutineService;

    // Create a new workout routine
    @PostMapping
    public WorkoutRoutine createWorkoutRoutine(@RequestBody WorkoutRoutine routine) {
        return workoutRoutineService.createWorkoutRoutine(routine);
    }

    // Get all workout routines
    @GetMapping
    public List<WorkoutRoutine> getAllWorkoutRoutines() {
        return workoutRoutineService.getAllWorkoutRoutines();
    }

    // Get all workout routines for a specific member
    @GetMapping("/member/{memberId}")
    public List<WorkoutRoutine> getWorkoutRoutines(@PathVariable Long memberId) {
        return workoutRoutineService.getWorkoutRoutinesByMember(memberId);
    }

    // Update a workout routine
    @PutMapping("/{routineId}")
    public WorkoutRoutine updateWorkoutRoutine(@PathVariable Long routineId, @RequestBody WorkoutRoutine updatedRoutine) {
        return workoutRoutineService.updateWorkoutRoutine(routineId, updatedRoutine);
    }

    // Delete a workout routine
    @DeleteMapping("/{routineId}")
    public void deleteWorkoutRoutine(@PathVariable Long routineId) {
        workoutRoutineService.deleteWorkoutRoutine(routineId);
    }
}