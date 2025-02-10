package com.knowit.gymintellect.gym_member.gym_member.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_member.gym_member.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
	@Autowired
    private MemberService memberService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getUserAndMember( @PathVariable int userId) {
        Optional<Map<String, Object>> memberDetails = memberService.getMemberDetails(userId);

        if (memberDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(memberDetails.get());
    }
}
