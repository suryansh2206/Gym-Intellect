package com.knowit.gymintellect.gym_member.gym_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.MembershipPlanJoin;
import com.knowit.gymintellect.gym_member.gym_member.repository.MembershipPlanJoinRepository;

import java.util.Date;
import java.util.List;

@Service
public class MembershipStatusUpdater {

    @Autowired
    private MembershipPlanJoinRepository memberMembershipRepository;

    // Run this method every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateExpiredMemberships() {
        Date today = new Date();
        List<MembershipPlanJoin> memberships = memberMembershipRepository.findAll();

        for (MembershipPlanJoin membership : memberships) {
            if (membership.getEndDate().before(today) && membership.getStatus().equalsIgnoreCase("ACTIVE")) {
                membership.setStatus("EXPIRED");
                memberMembershipRepository.save(membership);
            }
        }

        System.out.println("Membership statuses updated.");
    }
}
