package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.WorkoutRoutine;
import com.KnowIt.Gym_intellect_Crud.Repository.WorkoutRoutineRepository;

@Service
public class WorkoutRoutineService {
	    
	    @Autowired
	    private WorkoutRoutineRepository workoutRoutineRepository;

	    // Create a new workout routine
	    public WorkoutRoutine createWorkoutRoutine(WorkoutRoutine routine) {
	        return workoutRoutineRepository.save(routine);
	    }

	    // Get all workout routines
	    public List<WorkoutRoutine> getAllWorkoutRoutines() {
	        return workoutRoutineRepository.findAll();
	    }

	    // Get all workout routines for a specific member
	    public List<WorkoutRoutine> getWorkoutRoutinesByMember(Long memberId) {
	        return workoutRoutineRepository.findByMember_MemberId(memberId);
	    }

	    // Update a workout routine
	    public WorkoutRoutine updateWorkoutRoutine(Long routineId, WorkoutRoutine updatedRoutine) {
	        Optional<WorkoutRoutine> existingRoutine = workoutRoutineRepository.findById(routineId);
	        if (existingRoutine.isPresent()) {
	            WorkoutRoutine routine = existingRoutine.get();
	            routine.setPlannedSets(updatedRoutine.getPlannedSets());
	            routine.setPlannedReps(updatedRoutine.getPlannedReps());
	            routine.setCompletedSets(updatedRoutine.getCompletedSets());
	            routine.setCompletedReps(updatedRoutine.getCompletedReps());
	            routine.setCaloriesBurnt(updatedRoutine.getCaloriesBurnt());
	            routine.setCompletionTime(updatedRoutine.getCompletionTime());
	            routine.setCustomRoutine(updatedRoutine.isCustomRoutine());
	            routine.setCompleted(updatedRoutine.isCompleted());
	            return workoutRoutineRepository.save(routine);
	        }
	        return null;
	    }

	    // Delete a workout routine
	    public void deleteWorkoutRoutine(Long routineId) {
	        workoutRoutineRepository.deleteById(routineId);
	    }
	}
