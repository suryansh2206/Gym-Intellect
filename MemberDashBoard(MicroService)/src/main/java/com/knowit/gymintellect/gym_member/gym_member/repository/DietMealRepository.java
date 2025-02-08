package com.knowit.gymintellect.gym_member.gym_member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.gymintellect.gym_member.gym_member.entity.DietMeal;

public interface DietMealRepository extends JpaRepository<DietMeal, Long> {
	
	List<DietMeal> findByDietPlanDietPlanId(Long dietPlanId);
	
}
