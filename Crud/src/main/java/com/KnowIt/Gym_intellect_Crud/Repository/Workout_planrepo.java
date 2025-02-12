package com.KnowIt.Gym_intellect_Crud.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.KnowIt.Gym_intellect_Crud.Entity.WorkoutPlan;

public interface Workout_planrepo extends JpaRepository<WorkoutPlan,Long>{
	List<WorkoutPlan> findByPlanName(String planName);
	
//	List<WorkoutPlan> findByPlanName(String planName);
}
