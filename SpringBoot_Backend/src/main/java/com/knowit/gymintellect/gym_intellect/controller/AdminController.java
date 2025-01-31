package com.knowit.gymintellect.gym_intellect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.exception.AccessDeniedException;
import com.knowit.gymintellect.gym_intellect.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/gym-profiles")
    public ResponseEntity<List<GymProfile>> getAllGymProfiles() {
        String username = getAuthenticatedUsername();
        List<GymProfile> gymProfiles = adminService.getAllGymProfiles(username);
        return ResponseEntity.ok(gymProfiles);
    }

    @PutMapping("/gym-profiles/{gymProfileId}/approve")
    public ResponseEntity<String> approveGymProfile(@PathVariable Long gymProfileId) {
        String username = getAuthenticatedUsername(); // Extract username from the token or security context
        try {
            adminService.approveGymProfile(gymProfileId, username);
            return ResponseEntity.ok("Gym profile approved");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("/reject-gym/{gymProfileId}")
    public ResponseEntity<String> rejectGymProfile(@PathVariable Long gymProfileId) {
        String username = getAuthenticatedUsername();
        adminService.rejectGymProfile(gymProfileId, username);
        return ResponseEntity.ok("Gym profile rejected successfully.");
    }

    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
