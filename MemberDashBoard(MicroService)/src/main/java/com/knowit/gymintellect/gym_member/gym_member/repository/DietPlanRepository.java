package com.knowit.gymintellect.gym_member.gym_member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_member.gym_member.entity.DietPlan;

@Repository
public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {
	
	DietPlan findByWorkoutPlanPlanId(Long workoutPlanId);
	
}
