package com.knowit.gymintellect.gym_member.gym_member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
import com.knowit.gymintellect.gym_member.gym_member.entity.Workout;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutJoin;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_member.gym_member.repository.MemberRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.WorkoutJoinRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.WorkoutRepository;

@Service
public class WorkoutService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired 
	private WorkoutJoinRepository workoutJoinRepository;
	
	public List<Workout> getWorkoutsByUserId(int userId){
		Member member = memberRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        WorkoutPlan workoutPlan = member.getWorkoutPlan();
        if (workoutPlan == null) {
            throw new RuntimeException("Workout plan not found for this member");
        }

        List<WorkoutJoin> workoutJoins = workoutJoinRepository.findByWorkoutPlan(workoutPlan);

        return workoutJoins.stream()
                .map(WorkoutJoin::getWorkout)
                .collect(Collectors.toList());
	}
	
}
