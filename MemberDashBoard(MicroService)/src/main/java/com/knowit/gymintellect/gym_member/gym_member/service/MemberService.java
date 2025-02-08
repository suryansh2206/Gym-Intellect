package com.knowit.gymintellect.gym_member.gym_member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
import com.knowit.gymintellect.gym_member.gym_member.entity.Workout;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_member.gym_member.repository.MemberRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.WorkoutRepository;

@Service
public class MemberService {
	 @Autowired
	    private MemberRepository memberRepository;
	 
	 @Autowired
	    private WorkoutRepository workoutRepository;

	 public WorkoutPlan getWorkoutPlanForMember(Long userId) {
	        Member member = memberRepository.findByUserUserId(userId);
	        if (member != null && member.getMembership().getStatus().equalsIgnoreCase("ACTIVE")) {
	            WorkoutPlan workoutPlan = member.getWorkoutPlan();
	            List<Workout> workouts = workoutRepository.findByWorkoutPlanPlanId(workoutPlan.getPlanId());
	            workoutPlan.setWorkouts(workouts);
	            return workoutPlan;
	        }
	        return null;
	 }

}
