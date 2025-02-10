//package com.knowit.gymintellect.gym_member.gym_member.controller;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutRoutine;
//import com.knowit.gymintellect.gym_member.gym_member.service.WorkoutRoutineService;
//
//@RestController
//@RequestMapping("/workout-routine")
//public class WorkoutRoutineController {
//
//    @Autowired
//    private WorkoutRoutineService workoutRoutineService;
//
//    @PostMapping("/assign")
//    public ResponseEntity<?> assignWorkout(
//            @RequestParam Long memberId,
//            @RequestParam Long workoutId,
//            @RequestParam String assignedDate,
//            @RequestParam boolean isCustom
//    ) {
//        try {
//            LocalDate date = LocalDate.parse(assignedDate);
//            WorkoutRoutine routine = workoutRoutineService.assignWorkoutToMember(memberId, workoutId, date, isCustom);
//            return ResponseEntity.ok(routine);
//        } catch (DateTimeParseException e) {
//            return ResponseEntity.badRequest().body("Invalid date format. Use YYYY-MM-DD.");
//        }
//    }
//
//    @PutMapping("/complete/{routineId}")
//    public ResponseEntity<WorkoutRoutine> completeWorkout(@PathVariable Long routineId,
//                                                          @RequestParam int completedSets,
//                                                          @RequestParam int completedReps,
//                                                          @RequestParam float caloriesBurnt) {
//        return ResponseEntity.ok(workoutRoutineService.completeWorkout(routineId, completedSets, completedReps, caloriesBurnt));
//    }
//
//    @GetMapping("/pending/{memberId}")
//    public ResponseEntity<List<WorkoutRoutine>> getPendingWorkouts(@PathVariable Long memberId) {
//        return ResponseEntity.ok(workoutRoutineService.getPendingWorkouts(memberId));
//    }
//
//    @GetMapping("/completed/{memberId}")
//    public ResponseEntity<List<WorkoutRoutine>> getCompletedWorkouts(@PathVariable Long memberId) {
//        return ResponseEntity.ok(workoutRoutineService.getCompletedWorkouts(memberId));
//    }
//}
//
