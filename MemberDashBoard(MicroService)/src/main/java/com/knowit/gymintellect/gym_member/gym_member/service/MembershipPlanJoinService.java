package com.knowit.gymintellect.gym_member.gym_member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.MembershipPlanJoin;
import com.knowit.gymintellect.gym_member.gym_member.repository.MembershipPlanJoinRepository;

@Service
public class MembershipPlanJoinService {
	
	@Autowired
    private MembershipPlanJoinRepository memberMembershipRepository;

    // Method to check if a member has an active membership
    public boolean isMemberAllowed(Long memberId) {
        Optional<MembershipPlanJoin> membership = memberMembershipRepository.findByMemberMemberId(memberId);
        return membership.isPresent() && membership.get().isMembershipActive();
    }
	
}
