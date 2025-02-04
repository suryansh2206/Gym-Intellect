package com.KnowIt.Gym_intellect_Crud.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.MembershipPlan;
import com.KnowIt.Gym_intellect_Crud.Repository.MembershipPlanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipPlanService {

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    // Get all membership plans
    public List<MembershipPlan> getAllMembershipPlans() {
        return membershipPlanRepository.findAll();
    }

    // Save a membership plan
    public MembershipPlan saveMembershipPlan(MembershipPlan membershipPlan) {
        return membershipPlanRepository.save(membershipPlan);
    }

    // Update a membership plan
    public MembershipPlan updateMembershipPlan(Long id, MembershipPlan membershipPlan) {
        membershipPlan.setMemberPlanId(id); // Set the ID to ensure the correct plan is updated
        return membershipPlanRepository.save(membershipPlan);
    }

    // Delete a membership plan
    public void deleteMembershipPlan(int id) {
        membershipPlanRepository.deleteById(id);
    }

    // Get a membership plan by ID
    public Optional<MembershipPlan> getMembershipPlanById(int id) {
        return membershipPlanRepository.findById(id);
    }
}