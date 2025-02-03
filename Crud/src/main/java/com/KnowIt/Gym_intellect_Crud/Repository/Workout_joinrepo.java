package com.KnowIt.Gym_intellect_Crud.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.KnowIt.Gym_intellect_Crud.Entity.Workout;
import com.KnowIt.Gym_intellect_Crud.Entity.Workout_join;

@Repository
public interface Workout_joinrepo extends JpaRepository<Workout_join, Integer> {

    // Find all workout joins by plan ID
    List<Workout_join> findByPlan_PlanId(int planId);

    // Find all workout joins by workout ID
    List<Workout_join> findByWorkout_WorkoutId(int workoutId);

    // Corrected JPQL query to fetch Workout_join by workoutId and planId
    @Query("SELECT wj FROM Workout_join wj WHERE wj.workout.workoutId = :workoutId AND wj.plan.planId = :planId")
    Optional<Workout_join> findByWorkoutIdAndPlanId(@Param("workoutId") int workoutId, @Param("planId") int planId);

	

}
