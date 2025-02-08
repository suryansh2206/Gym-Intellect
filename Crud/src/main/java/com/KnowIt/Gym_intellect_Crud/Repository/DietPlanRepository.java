package com.KnowIt.Gym_intellect_Crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KnowIt.Gym_intellect_Crud.Entity.DietPlan;

@Repository
public interface DietPlanRepository extends JpaRepository<DietPlan, Integer> {
}
