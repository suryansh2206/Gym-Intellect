package com.knowit.gymintellect.gym_intellect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_intellect.dto.MemberRegistrationDTO;
import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.entity.Member;
import com.knowit.gymintellect.gym_intellect.entity.MembershipPlan;
import com.knowit.gymintellect.gym_intellect.entity.User;
import com.knowit.gymintellect.gym_intellect.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_intellect.exception.ResourceNotFoundException;
import com.knowit.gymintellect.gym_intellect.repository.UserRepository;
import com.knowit.gymintellect.gym_intellect.service.UserService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final UserService userService;
    private final UserRepository userRepository;

    public MemberController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @PostMapping("/register")
//    @PreAuthorize("hasRole('GYM_OWNER')")
    public ResponseEntity<Member> registerMember(@RequestBody MemberRegistrationDTO registrationDTO) {
        User currentOwner = getAuthenticatedUser();
        System.out.println(currentOwner);
        return ResponseEntity.ok(userService.registerGymMember(registrationDTO, currentOwner));
    }

    @GetMapping("/gym-profiles/owner/{ownerId}")
    @PreAuthorize("hasRole('GYM_OWNER')")
    public ResponseEntity<List<GymProfile>> getGymProfilesByOwner(@PathVariable Long ownerId) {
        User currentOwner = getAuthenticatedUser();
        if (!currentOwner.getUserId().equals(ownerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<GymProfile> gymProfiles = userService.getGymProfilesForOwner(currentOwner);

        // If the list is empty, throw an exception
        if (gymProfiles.isEmpty()) {
            throw new ResourceNotFoundException("Gym profiles not found");
        }

        return ResponseEntity.ok(gymProfiles);
    }


    // Keep other endpoints unchanged
    @GetMapping("/plans")
    @PreAuthorize("hasRole('GYM_OWNER')")
    public ResponseEntity<List<MembershipPlan>> getMembershipPlans(@RequestParam("gymProfileId") Long gymProfileId) {
        return ResponseEntity.ok(userService.getMembershipPlansByGymProfile(gymProfileId));
    }


    @GetMapping("/workout-plans")
    @PreAuthorize("hasAuthority('GYM_OWNER')")
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlans() {
        return ResponseEntity.ok(userService.getAllWorkoutPlans());
    }
}