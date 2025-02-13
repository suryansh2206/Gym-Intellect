package com.KnowIt.Gym_intellect_Crud.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout_join;
import com.KnowIt.Gym_intellect_Crud.Services.Workout_joinservices;
import com.KnowIt.Gym_intellect_Crud.dto.WorkoutJoinDTO;

@RestController
@RequestMapping("/api/workout-joins")
public class Workout_joincontroller {
	@Autowired
    private Workout_joinservices workoutJoinService;

//    // Link a workout to a plan
//    @PostMapping("/link")
//    public ResponseEntity<Workout_join> linkWorkoutToPlan(
//            @RequestParam Long workoutId,
//            @RequestParam Long planId) {
//        Workout_join join = workoutJoinService.linkWorkoutToPlan(workoutId, planId);
//        return ResponseEntity.ok(join);
//    }
//
//    // Unlink a workout from a plan
//    @DeleteMapping("/unlink")
//    public ResponseEntity<String> unlinkWorkoutFromPlan(
//            @RequestParam Long workoutId,
//            @RequestParam Long planId) {
//    	workoutJoinService.unlinkWorkoutFromPlan(workoutId, planId);
//    	return ResponseEntity.ok("Workout unlinked from plan successfully");
//    }

    @GetMapping("/workouts-by-plan/{planId}")
    public ResponseEntity<List<WorkoutJoinDTO>> getWorkoutsByPlan(@PathVariable Long planId) {
        List<Workout_join> joins = workoutJoinService.getWorkoutsByPlan(planId);
        List<WorkoutJoinDTO> dtos = joins.stream()
            .map(join -> {
                WorkoutJoinDTO dto = new WorkoutJoinDTO();
                dto.setWojId(join.getWojId());
                dto.setWorkoutId(join.getWorkout().getWorkoutId());
                dto.setExerciseName(join.getWorkout().getExerciseName());
                dto.setPlanId(join.getPlan().getPlanId());
                dto.setPlanName(join.getPlan().getPlanName());
                return dto;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Get all plans containing a workout
    @GetMapping("/plans-by-workout/{workoutId}")
    public ResponseEntity<List<Workout_join>> getPlansByWorkout(@PathVariable Long workoutId) {
        List<Workout_join> joins = workoutJoinService.getPlansByWorkout(workoutId);
        return ResponseEntity.ok(joins);
    }

}
