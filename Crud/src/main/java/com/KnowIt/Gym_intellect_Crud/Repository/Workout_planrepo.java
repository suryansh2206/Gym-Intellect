package com.KnowIt.Gym_intellect_Crud.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout_plan;

@Repository
public interface Workout_planrepo extends JpaRepository<Workout_plan,Long>{
	List<Workout_plan> findByPlanName(String planName);
	
}
