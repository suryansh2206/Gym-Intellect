package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AotInitializerNotFoundException;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout;
import com.KnowIt.Gym_intellect_Crud.Entity.Workout_join;
import com.KnowIt.Gym_intellect_Crud.Entity.Workout_plan;
import com.KnowIt.Gym_intellect_Crud.Repository.Workout_joinrepo;
import com.KnowIt.Gym_intellect_Crud.Repository.Workout_planrepo;
import com.KnowIt.Gym_intellect_Crud.Repository.Workoutrepo;

@Service
public class Workout_joinservices {
	 @Autowired
	    private Workout_joinrepo workoutJoinRepository;

	    @Autowired
	    private Workoutrepo workoutRepository;

	    @Autowired
	    private Workout_planrepo workoutPlanRepository;

	    // Link a workout to a plan
	    public Workout_join linkWorkoutToPlan(int workoutId, int planId) {
	        Workout workout = workoutRepository.findById(workoutId)
	                .orElseThrow(() -> new AotInitializerNotFoundException(null, "Workout not found with ID: " + workoutId));
	        Workout_plan plan = workoutPlanRepository.findById(planId)
	                .orElseThrow(() -> new AotInitializerNotFoundException(null, "Plan not found with ID: " + planId));

	        Workout_join join = new Workout_join();
	        join.setWorkout(workout);
	        join.setPlan(plan);
	        return workoutJoinRepository.save(join);
	    }
	    public void unlinkWorkoutFromPlan(int workoutId, int planId) {
	        Workout_join join = workoutJoinRepository.findByWorkoutIdAndPlanId(workoutId, planId)
	            .orElseThrow(() -> new AotInitializerNotFoundException(null, "Link not found for workoutId: " + workoutId + " and planId: " + planId));

	        workoutJoinRepository.delete(join);
	    }


	    

	    // Get all workouts in a plan
	    public List<Workout_join> getWorkoutsByPlan(long planId) {
	        return workoutJoinRepository.findByPlan_PlanId(planId);
	    }

	    // Get all plans containing a workout
	    public List<Workout_join> getPlansByWorkout(int workoutId) {
	        return workoutJoinRepository.findByWorkout_WorkoutId(workoutId);
	    }
	

}


