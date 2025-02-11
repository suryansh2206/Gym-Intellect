package com.knowit.gymintellect.gym_member.gym_member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.DietMeal;
import com.knowit.gymintellect.gym_member.gym_member.entity.DietPlan;
import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
import com.knowit.gymintellect.gym_member.gym_member.entity.Workout;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutJoin;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_member.gym_member.repository.DietMealRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.DietPlanRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.MemberRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.WorkoutJoinRepository;

@Service
public class DietPlanService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DietPlanRepository dietPlanRepository;

    @Autowired
    private DietMealRepository dietMealRepository;
    
    private WorkoutJoinRepository workoutJoinRepository;

    public List<DietPlan> getDietPlanForMember(int userId) {
    	Member member = memberRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        WorkoutPlan workoutPlan = member.getWorkoutPlan();
        if (workoutPlan == null) {
            throw new RuntimeException("Workout plan not found for this member");
        }
        int planId = workoutPlan.getPlanId().intValue();
        List<DietPlan> workoutJoins = dietPlanRepository.findByWorkoutPlan_PlanId(planId);
        
//        List<Long> workoutIds = workoutJoins.stream()
//                .map(wj -> wj.getWorkout().getWorkoutId())
//                .collect(Collectors.toList());
//        List<Workout> workout = workoutJoinRepository.findBy
//        Workout workout =  workoutjoin.getWorkout();
        return workoutJoins;
    }
}
