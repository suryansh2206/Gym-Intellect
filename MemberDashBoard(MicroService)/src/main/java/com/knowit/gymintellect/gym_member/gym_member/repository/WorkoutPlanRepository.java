package com.knowit.gymintellect.gym_member.gym_member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutPlan;


@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
	
	boolean existsByPlanIdAndWorkouts_WorkoutId(Long planId, Long workoutId);

}
