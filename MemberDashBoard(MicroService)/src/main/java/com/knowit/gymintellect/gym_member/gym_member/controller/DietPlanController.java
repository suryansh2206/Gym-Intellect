package com.knowit.gymintellect.gym_member.gym_member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_member.gym_member.entity.DietPlan;
import com.knowit.gymintellect.gym_member.gym_member.service.DietPlanService;

@RestController
@RequestMapping("/api/member")
public class DietPlanController {

    @Autowired
    private DietPlanService dietPlanService;

    @GetMapping("/{userId}/diet-plan")
    public ResponseEntity<?> getMemberDietPlan(@PathVariable Long userId) {
        DietPlan dietPlan = dietPlanService.getDietPlanForMember(userId);
        if (dietPlan != null) {
            return ResponseEntity.ok(dietPlan);
        }
        return ResponseEntity.badRequest().body("Member not found or membership expired!");
    }
}

