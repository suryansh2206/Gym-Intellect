package com.knowit.gymintellect.gym_intellect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.entity.WorkoutPlan;

public interface WorkoutPlanRespository extends JpaRepository<WorkoutPlan, Long> {


}
