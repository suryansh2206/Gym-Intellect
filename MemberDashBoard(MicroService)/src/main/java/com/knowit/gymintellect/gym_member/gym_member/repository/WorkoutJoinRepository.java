package com.knowit.gymintellect.gym_member.gym_member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutJoin;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutPlan;

@Repository
public interface WorkoutJoinRepository extends JpaRepository<WorkoutJoin, Integer>{
	List<WorkoutJoin> findByWorkoutPlan(WorkoutPlan workoutPlan);
}
