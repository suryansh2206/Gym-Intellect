package com.knowit.gymintellect.gym_member.gym_member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_member.gym_member.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{userId}/workout-plan")
    public ResponseEntity<?> getMemberWorkoutPlan(@PathVariable Long userId) {
        WorkoutPlan workoutPlan = memberService.getWorkoutPlanForMember(userId);
        if (workoutPlan != null) {
            return ResponseEntity.ok(workoutPlan);
        }
        return ResponseEntity.badRequest().body("Member not found or membership expired!");
    }
}
