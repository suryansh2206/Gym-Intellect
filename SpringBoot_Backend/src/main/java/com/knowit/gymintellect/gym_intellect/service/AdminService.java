package com.knowit.gymintellect.gym_intellect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.entity.User;
import com.knowit.gymintellect.gym_intellect.exception.AccessDeniedException;
import com.knowit.gymintellect.gym_intellect.exception.ResourceNotFoundException;
import com.knowit.gymintellect.gym_intellect.repository.GymProfileRepository;
import com.knowit.gymintellect.gym_intellect.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
    private GymProfileRepository gymProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GymProfileService gymProfileService;

    public List<GymProfile> getAllGymProfiles(String username) {
        validateAdminAccess(username);
        return gymProfileRepository.findAll();
    }
    
    public List<GymProfile> getAllPendingGymProfiles(String username) {
        validateAdminAccess(username);
        return gymProfileService.getPendingGymProfiles();
    }

    public void approveGymProfile(Long gymProfileId, String username) {
        validateGymProfileId(gymProfileId); // Validate gymProfileId
        validateAdminAccess(username);
        GymProfile approvedProfile = gymProfileService.approveGymProfile(gymProfileId);
        System.out.println("Gym profile approved: " + approvedProfile.getGymName());
    }
    
    public void rejectGymProfile(Long gymProfileId, String username) {
        validateAdminAccess(username);
        GymProfile rejectedProfile = gymProfileService.rejectGymProfile(gymProfileId);
        System.out.println("Gym profile rejected: " + rejectedProfile.getGymName());
    }

    private void validateAdminAccess(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        if (!"ADMIN".equalsIgnoreCase(user.getRole().getName())) {
            throw new AccessDeniedException("Access denied: User is not an admin");
        }
    }
    
    private void validateGymProfileId(Long gymProfileId) {
        if (gymProfileId == null) {
            throw new IllegalArgumentException("Gym profile ID cannot be null");
        }
    }

}

