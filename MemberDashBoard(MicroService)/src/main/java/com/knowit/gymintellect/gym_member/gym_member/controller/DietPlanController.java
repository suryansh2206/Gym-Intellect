package com.knowit.gymintellect.gym_member.gym_member.controller;

import java.util.List;

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
    public ResponseEntity<?> getMemberDietPlan(@PathVariable int userId) {
    	try {
            List<DietPlan> dietPlans = dietPlanService.getDietPlanForMember(userId);
            return ResponseEntity.ok(dietPlans);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

