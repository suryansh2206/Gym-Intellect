package com.KnowIt.Gym_intellect_Crud.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.MemberPlanJoin;
import com.KnowIt.Gym_intellect_Crud.Repository.MemberPlanJoinRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberPlanJoinService {

    @Autowired
    private MemberPlanJoinRepository memberPlanJoinRepository;

    // Get all member plan joins
    public List<MemberPlanJoin> getAllMemberPlanJoins() {
        return memberPlanJoinRepository.findAll();
    }

    // Save a member plan join
    public MemberPlanJoin saveMemberPlanJoin(MemberPlanJoin memberPlanJoin) {
        return memberPlanJoinRepository.save(memberPlanJoin);
    }

    // Update a member plan join
    public MemberPlanJoin updateMemberPlanJoin(Long id, MemberPlanJoin memberPlanJoin) {
        memberPlanJoin.setMembershipId(id); // Set the ID to ensure the correct join is updated
        return memberPlanJoinRepository.save(memberPlanJoin);
    }

    // Delete a member plan join
    public void deleteMemberPlanJoin(int id) {
        memberPlanJoinRepository.deleteById(id);
    }

    // Get a member plan join by ID
    public Optional<MemberPlanJoin> getMemberPlanJoinById(int id) {
        return memberPlanJoinRepository.findById(id);
    }
}