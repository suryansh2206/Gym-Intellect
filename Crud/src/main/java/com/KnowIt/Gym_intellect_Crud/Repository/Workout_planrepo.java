package com.KnowIt.Gym_intellect_Crud.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.KnowIt.Gym_intellect_Crud.Entity.Workout_plan;

public interface Workout_planrepo extends JpaRepository<Workout_plan,Integer>{
	List<Workout_plan> findByPlanName(String planName);
}
