package com.knowit.gymintellect.gym_member.gym_member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.knowit.gymintellect.gym_member.gym_member.service.MembershipPlanJoinService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MembershipController {

    @Autowired
    private MembershipPlanJoinService membershipService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");

        if (memberId == null || !membershipService.isMemberAllowed(memberId)) {
            return ResponseEntity.status(403).body("Access Denied. Membership Expired.");
        }

        return ResponseEntity.ok("Welcome to the Member Dashboard!");
    }
}
