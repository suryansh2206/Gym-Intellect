package com.KnowIt.Gym_intellect_Crud.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.KnowIt.Gym_intellect_Crud.Entity.MembershipPlan;
import com.KnowIt.Gym_intellect_Crud.Services.MembershipPlanService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/membershipPlans")
public class MembershipPlanController {

    @Autowired
    private MembershipPlanService membershipPlanService;

    // Get all membership plans
    @GetMapping
    public ResponseEntity<List<MembershipPlan>> getAllMembershipPlans() {
        List<MembershipPlan> plans = membershipPlanService.getAllMembershipPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    // Save a membership plan
    @PostMapping
    public ResponseEntity<MembershipPlan> saveMembershipPlan(@RequestBody MembershipPlan membershipPlan) {
        MembershipPlan savedPlan = membershipPlanService.saveMembershipPlan(membershipPlan);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    // Update a membership plan
    @PutMapping("/{id}")
    public ResponseEntity<MembershipPlan> updateMembershipPlan(@PathVariable Long id, @RequestBody MembershipPlan membershipPlan) {
        MembershipPlan updatedPlan = membershipPlanService.updateMembershipPlan(id, membershipPlan);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    // Delete a membership plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembershipPlan(@PathVariable int id) {
        membershipPlanService.deleteMembershipPlan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get a membership plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<MembershipPlan> getMembershipPlanById(@PathVariable int id) {
        Optional<MembershipPlan> plan = membershipPlanService.getMembershipPlanById(id);
        return plan.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}