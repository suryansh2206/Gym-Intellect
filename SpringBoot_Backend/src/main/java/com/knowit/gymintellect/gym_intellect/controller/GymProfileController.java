package com.knowit.gymintellect.gym_intellect.controller;

import com.knowit.gymintellect.gym_intellect.dto.GymProfileRequest;
import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.service.GymProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/gym-profile")
public class GymProfileController {

    @Autowired
    private GymProfileService gymProfileService;

    // Endpoint to create a gym profile
//    @PreAuthorize("hasRole('GYM_OWNER')")
    @PostMapping("/create")
    public ResponseEntity<String> createGymProfile(@RequestBody GymProfileRequest gymProfileRequest) {
        gymProfileService.createGymProfile(gymProfileRequest);
        return ResponseEntity.ok("Gym profile submitted for admin approval.");
    }

    // Endpoint to get a gym profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<GymProfile> getGymProfileById(@PathVariable Long id) {
        GymProfile gymProfile = gymProfileService.getGymProfileById(id);
        return ResponseEntity.ok(gymProfile);
    }

    // Endpoint to approve a gym profile (called by the main admin)
    @PutMapping("/{id}/approve")
    public ResponseEntity<GymProfile> approveGymProfile(@PathVariable Long id) {
        GymProfile gymProfile = gymProfileService.approveGymProfile(id);
        return ResponseEntity.ok(gymProfile);
    }

    // Endpoint to reject a gym profile (called by the main admin)
    @PutMapping("/{id}/reject")
    public ResponseEntity<GymProfile> rejectGymProfile(@PathVariable Long id) {
        GymProfile gymProfile = gymProfileService.rejectGymProfile(id);
        return ResponseEntity.ok(gymProfile);
    }

    // Endpoint to delete a gym profile (called by the main admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGymProfile(@PathVariable Long id) {
        gymProfileService.deleteGymProfile(id);
        return ResponseEntity.noContent().build();
    }
}
