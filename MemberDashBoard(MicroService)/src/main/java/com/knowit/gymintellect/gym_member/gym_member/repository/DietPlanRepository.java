package com.knowit.gymintellect.gym_member.gym_member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_member.gym_member.entity.DietPlan;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutJoin;

@Repository
public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {
	
	@Query("SELECT d FROM DietPlan d LEFT JOIN FETCH d.meals WHERE d.workoutPlan.planId = :planId")
    List<DietPlan> findByWorkoutPlanId(@Param("planId") Long planId);
}
