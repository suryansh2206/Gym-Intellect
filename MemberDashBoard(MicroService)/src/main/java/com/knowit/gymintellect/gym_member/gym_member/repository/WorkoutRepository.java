package com.knowit.gymintellect.gym_member.gym_member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_member.gym_member.entity.Workout;


@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
	
//	List<Workout> findByWorkoutPlanWorkoutPlanPlanId(Long planId);
	List<Workout> findByWorkoutIdIn(List<Integer> workoutIds);
	
}
